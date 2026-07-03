package fr.fae.memoria.charona.features.auth.infrastructure.persistence;

import fr.fae.memoria.charona.features.auth.domain.models.RefreshToken;
import fr.fae.memoria.charona.features.auth.domain.repositories.IRefreshTokenRepository;
import fr.fae.memoria.charona.features.user.domain.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
@Profile({"jpa", "test", "dev"})
public class RefreshTokenRepositoryJpaAdapter implements IRefreshTokenRepository {

    private static final Logger log = LoggerFactory.getLogger(RefreshTokenRepositoryJpaAdapter.class);

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    public RefreshTokenRepositoryJpaAdapter(RefreshTokenJpaRepository refreshTokenJpaRepository) {
        this.refreshTokenJpaRepository = refreshTokenJpaRepository;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        log.debug("Sauvegarde du refresh token : {}", refreshToken);
        return refreshTokenJpaRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByTokenHash(String tokenHash) {
        log.debug("Recherche du refresh token par hash : {}", tokenHash);
        return refreshTokenJpaRepository.findByTokenHash(tokenHash);
    }

    @Override
    public int revokeAllByUser(User user) {
        log.debug("Révocation de tous les refresh tokens pour l'utilisateur : {}", user);
        return refreshTokenJpaRepository.revokeAllByUser(user);
    }

    @Override
    public int revokeByTokenHash(String tokenHash) {
        log.debug("Révocation du refresh token par hash : {}", tokenHash);
        return refreshTokenJpaRepository.revokeByTokenHash(tokenHash);
    }

    @Override
    public void deleteAllByExpiresAtBefore(OffsetDateTime dateTime) {
        log.debug("Suppression de tous les refresh tokens expirés avant : {}", dateTime);
        refreshTokenJpaRepository.deleteAllByExpiresAtBefore(dateTime);
    }
}
