package fr.fae.memoria.charona.features.user.infrastructure.persistence;

import fr.fae.memoria.charona.features.user.domain.models.User;
import fr.fae.memoria.charona.features.user.domain.repositories.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile({"jpa", "test", "dev"})
@Repository
public class UserRepositoryJpaAdapter implements IUserRepository {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryJpaAdapter.class);

    // Injected dependencies
    private final UserJpaRepository userJpaRepository;

    // Constructors
    public UserRepositoryJpaAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    // Methods
    @Override
    public Optional<User> findById(UUID id) {
        log.debug("Recherche de l'utilisateur par id: {}", id);
        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByPseudo(String pseudo) {
        log.debug("Recherche de l'utilisateur par pseudo: {}", pseudo);
        return userJpaRepository.findByPseudo(pseudo);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.debug("Recherche de l'utilisateur par email: {}", email);
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        log.debug("Recherche de tous les utilisateurs");
        return userJpaRepository.findAll();
    }

    @Override
    public User save(User user) {
        log.debug("Enregistrement de l'utilisateur: {}", user);
        return userJpaRepository.save(user);
    }
    @Override
    public void deleteById(UUID id) {
        log.debug("Suppression de l'utilisateur avec l'ID: {}", id);
        userJpaRepository.deleteById(id);
    }

    // Utils
    @Override
    public boolean existsByEmail(String email) {
        log.debug("Vérification de l'existence de l'utilisateur par email: {}", email);
        return userJpaRepository.existsByEmail(email);
    }
    @Override
    public boolean existsByPseudo(String pseudo) {
        log.debug("Vérification de l'existence de l'utilisateur par pseudo: {}", pseudo);
        return userJpaRepository.existsByPseudo(pseudo);
    }
    @Override
    public boolean existsByPseudoAndIdNot(String pseudo, UUID id) {
        log.debug("Vérification de l'existence de l'utilisateur par pseudo et ID: pseudo={}, id={}", pseudo, id);
        return userJpaRepository.existsByPseudoAndIdNot(pseudo, id);
    }
    @Override
    public boolean existsByEmailAndIdNot(String email, UUID id) {
        log.debug("Vérification de l'existence de l'utilisateur par email et ID: email={}, id={}", email, id);
        return userJpaRepository.existsByEmailAndIdNot(email, id);
    }
}
