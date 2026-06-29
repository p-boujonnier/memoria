package fr.fae.project.charona.account.security.refresh.application;

import fr.fae.project.charona.account.security.refresh.domain.models.RefreshToken;
import fr.fae.project.charona.account.user.domain.User;
import fr.fae.project.charona.common.ServiceResponse;

public interface IRefreshTokenService {

    ServiceResponse<String> createAndPersistRefreshToken(User user, String device, String ipAddress, String userAgent);

    ServiceResponse<String> rotateRefreshToken(String rawToken, String device, String ipAddress, String userAgent);

    ServiceResponse<Void> revokeRefreshToken(String rawToken);

    ServiceResponse<Void> revokeAllForUser(User user);

    ServiceResponse<RefreshToken> findValidByToken(String rawToken);
}
