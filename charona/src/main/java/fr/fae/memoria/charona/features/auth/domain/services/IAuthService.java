package fr.fae.memoria.charona.features.auth.domain.services;

import fr.fae.memoria.charona.features.auth.infrastructure.api.dtos.requests.LoginRequest;
import fr.fae.memoria.charona.features.auth.infrastructure.api.dtos.requests.RegisterRequest;
import fr.fae.memoria.charona.features.auth.infrastructure.api.dtos.responses.AuthResponse;
import fr.fae.memoria.charona.shared.ServiceResponse;

public interface IAuthService {

    ServiceResponse<Void> register(RegisterRequest request);

    ServiceResponse<AuthResponse> login(LoginRequest request, String device, String ipAddress, String userAgent);

    ServiceResponse<Void> logout(String rawRefreshToken);

    ServiceResponse<AuthResponse> refresh(String rawRefreshToken, String device, String ipAddress, String userAgent);
}