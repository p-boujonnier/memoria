package fr.fae.project.charona.features.auth.infrastructure.persistence;

import fr.fae.project.charona.features.auth.domain.models.RefreshToken;
import fr.fae.project.charona.features.auth.domain.repositories.IRefreshTokenRepository;
import fr.fae.project.charona.features.user.domain.models.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
@Profile({"jpa", "test"})
public class RefreshTokenRepositoryJpaAdapter implements IRefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    public RefreshTokenRepositoryJpaAdapter(RefreshTokenJpaRepository refreshTokenJpaRepository) {
        this.refreshTokenJpaRepository = refreshTokenJpaRepository;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenJpaRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByTokenHash(String tokenHash) {
        return refreshTokenJpaRepository.findByTokenHash(tokenHash);
    }

    @Override
    public int revokeAllByUser(User user) {
        return refreshTokenJpaRepository.revokeAllByUser(user);
    }

    @Override
    public int revokeByTokenHash(String tokenHash) {
        return refreshTokenJpaRepository.revokeByTokenHash(tokenHash);
    }

    @Override
    public void deleteAllByExpiresAtBefore(OffsetDateTime dateTime) {
        refreshTokenJpaRepository.deleteAllByExpiresAtBefore(dateTime);
    }
}
