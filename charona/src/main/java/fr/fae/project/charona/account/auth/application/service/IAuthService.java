package fr.fae.project.charona.account.auth.application.service;

import fr.fae.project.charona.account.auth.api.dto.requests.LoginRequest;
import fr.fae.project.charona.account.auth.api.dto.requests.RegisterRequest;
import fr.fae.project.charona.account.auth.api.dto.responses.AuthResponse;
import fr.fae.project.charona.common.ServiceResponse;

public interface IAuthService {

    ServiceResponse<Void> register(RegisterRequest request);

    ServiceResponse<AuthResponse> login(LoginRequest request, String device, String ipAddress, String userAgent);

    ServiceResponse<Void> logout(String rawRefreshToken);

    ServiceResponse<AuthResponse> refresh(String rawRefreshToken, String device, String ipAddress, String userAgent);

    ServiceResponse<AuthResponse> me(String userId);

}