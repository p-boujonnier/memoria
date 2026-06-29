package fr.fae.project.charona.features.auth.application.services;

import fr.fae.project.charona.features.auth.infrastructure.api.dto.requests.LoginRequest;
import fr.fae.project.charona.features.auth.infrastructure.api.dto.requests.RegisterRequest;
import fr.fae.project.charona.features.auth.infrastructure.api.dto.responses.AuthResponse;
import fr.fae.project.charona.security.jwt.JwtService;
import fr.fae.project.charona.features.auth.domain.services.IRefreshTokenService;
import fr.fae.project.charona.features.auth.domain.models.RefreshToken;
import fr.fae.project.charona.features.auth.domain.services.IAuthService;
import fr.fae.project.charona.features.user.domain.services.IUserService;
import fr.fae.project.charona.features.user.domain.models.User;
import fr.fae.project.charona.features.user.domain.models.enums.Role;
import fr.fae.project.charona.shared.ServiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {


    @Value("${jwt.access-token.expiration}")
    private long accessTokenExpiration;

    // Dependencies
    private final IUserService userService;
    private final JwtService jwtService;
    private final IRefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    // Constructors
    public AuthServiceImpl(
            IUserService userService,
            JwtService jwtService,
            IRefreshTokenService refreshTokenService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    // Methods
    /**
     * Register a new user with the provided registration request.
     * @param request The registration request containing user details.
     * @return ServiceResponse<Void>
     */
    @Override
    public ServiceResponse<Void> register(RegisterRequest request) {
        ServiceResponse<User> created = userService.create(
                new User(request.getPseudo(), request.getEmail(), request.getPassword()));
        if (created.getData() == null) {
            return new ServiceResponse<>(created.getCode(), created.getMessage(), null);
        }
        return new ServiceResponse<>("1000", "Succès de l'inscription.", null);
    }

    /**
     * Log in a user and generate authentication tokens.
     * @param request Login request containing credentials.
     * @param device Device information.
     * @param ipAddress IP address of the login attempt.
     * @param userAgent User agent string.
     */
    @Override
    public ServiceResponse<AuthResponse> login(LoginRequest request, String device, String ipAddress, String userAgent) {
        User user;
        if (request.getIdentifier().contains("@")) {
            user = userService.findByEmail(request.getIdentifier()).getData();
        } else {
            user = userService.findByPseudo(request.getIdentifier()).getData();
        }
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ServiceResponse<>("1100", "Identifiants invalides", null);
        }
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail());
        ServiceResponse<String> refreshToken = refreshTokenService
                .createAndPersistRefreshToken(user, device, ipAddress, userAgent);
        String message = "Succès de la connection : bienvenue " + user.getPseudo() + " !";
        return new ServiceResponse<>("1000", message,
                new AuthResponse(
                        accessToken,
                        refreshToken.getData(),
                        user.getId(),
                        user.getPseudo(),
                        accessTokenExpiration,
                        mapRoles(user)));
    }

    /**
     * Log out a user by invalidating their refresh token.
     * @param rawRefreshToken Raw refresh token to be invalidated.
     */
    @Override
    public ServiceResponse<Void> logout(String rawRefreshToken) {
        return refreshTokenService.revokeRefreshToken(rawRefreshToken);
    }

    /**
     * Refresh authentication tokens using a valid refresh token.
     * @param rawRefreshToken Raw refresh token for token refresh.
     * @param device Device information.
     * @param ipAddress IP address of the refresh attempt.
     * @param userAgent User agent string.
     */
    @Override
    public ServiceResponse<AuthResponse> refresh(
            String rawRefreshToken,
            String device,
            String ipAddress,
            String userAgent) {

        ServiceResponse<String> rotatedToken = refreshTokenService.rotateRefreshToken(
                rawRefreshToken,
                device,
                ipAddress,
                userAgent);

        if (rotatedToken.getData() == null){
            return new ServiceResponse<>(rotatedToken.getCode(), rotatedToken.getMessage(), null);
        }

        ServiceResponse<?> foundToken = refreshTokenService.findValidByToken(rotatedToken.getData());
        if (foundToken.getData() == null){
            return new ServiceResponse<>(foundToken.getCode(), foundToken.getMessage(), null);
        }

        var rt = (RefreshToken) foundToken.getData();
        User user = rt.getUser();

        String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail());
        String newRawToken = rotatedToken.getData();

        return new ServiceResponse<>("1002", "Le token a été actualisé avec succès.",
                new AuthResponse(
                        accessToken,
                        newRawToken,
                        user.getId(),
                        user.getPseudo(),
                        accessTokenExpiration,
                        mapRoles(user))
        );
    }

    @Override
    public ServiceResponse<AuthResponse> me(String userId) {
        ServiceResponse<User> userResponse = userService.findById(UUID.fromString(userId));

        if (userResponse.getData() == null) {
            return new ServiceResponse<>("1101", "Compte introuvable", null);
        }

        User user = userResponse.getData();

        return new ServiceResponse<>("1000", "Authentification réussie",
                new AuthResponse(
                        null,
                        null,
                        user.getId(),
                        user.getPseudo(),
                        0,
                        user.getRoles().stream().map(Role::name).collect(Collectors.toList()))
        );
    }

    private List<String> mapRoles(User user) {
        return user.getRoles().stream().map(Role::name).collect(Collectors.toList());
    }
}
