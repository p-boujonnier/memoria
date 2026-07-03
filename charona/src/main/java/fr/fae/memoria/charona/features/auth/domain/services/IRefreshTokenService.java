package fr.fae.memoria.charona.features.auth.domain.services;

import fr.fae.memoria.charona.features.auth.domain.models.RefreshToken;
import fr.fae.memoria.charona.features.user.domain.models.User;
import fr.fae.memoria.charona.shared.ServiceResponse;

public interface IRefreshTokenService {

    ServiceResponse<String> createAndPersistRefreshToken(User user, String device, String ipAddress, String userAgent);

    ServiceResponse<String> rotateRefreshToken(String rawToken, String device, String ipAddress, String userAgent);

    ServiceResponse<Void> revokeRefreshToken(String rawToken);

    ServiceResponse<Void> revokeAllForUser(User user);

    ServiceResponse<RefreshToken> findValidByToken(String rawToken);
}
