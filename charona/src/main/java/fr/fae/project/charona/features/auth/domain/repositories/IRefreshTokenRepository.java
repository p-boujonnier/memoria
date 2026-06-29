package fr.fae.project.charona.features.auth.domain.repositories;

import fr.fae.project.charona.features.auth.domain.models.RefreshToken;
import fr.fae.project.charona.features.user.domain.models.User;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface IRefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByTokenHash(String tokenHash);

    int revokeAllByUser(User user);

    int revokeByTokenHash(String tokenHash);

    void deleteAllByExpiresAtBefore(OffsetDateTime dateTime);
}

