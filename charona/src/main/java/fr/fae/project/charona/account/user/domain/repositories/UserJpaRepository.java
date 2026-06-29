package fr.fae.project.charona.account.user.domain.repositories;

import fr.fae.project.charona.account.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID> {
    // CRUD operations
    Optional<User> findByEmail(String email);
    Optional<User> findByPseudo(String pseudo);

    // Utils
    boolean existsByEmail(String email);
    boolean existsByPseudo(String pseudo);
    boolean existsByPseudoAndIdNot(String pseudo, UUID id);
    boolean existsByEmailAndIdNot(String email, UUID id);
}
