package fr.fae.project.charona.account.security.refresh.domain.repositories;

import fr.fae.project.charona.account.security.refresh.domain.models.RefreshToken;
import fr.fae.project.charona.account.user.domain.User;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface IRefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByTokenHash(String tokenHash);

    int revokeAllByUser(User user);

    int revokeByTokenHash(String tokenHash);

    void deleteAllByExpiresAtBefore(OffsetDateTime dateTime);
}

