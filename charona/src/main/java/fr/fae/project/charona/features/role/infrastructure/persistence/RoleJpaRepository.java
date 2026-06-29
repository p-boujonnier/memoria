package fr.fae.project.charona.features.role.infrastructure.persistence;

import fr.fae.project.charona.features.role.domain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByAssignableTrue();
    List<Role> findAllByAskableTrue();
    Optional<Role> findByCode(String code);
    void deleteByCode(String code);
    boolean existsByCode(String code);
}
