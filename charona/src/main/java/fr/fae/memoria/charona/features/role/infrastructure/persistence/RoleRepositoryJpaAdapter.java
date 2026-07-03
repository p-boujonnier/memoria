package fr.fae.memoria.charona.features.role.infrastructure.persistence;

import fr.fae.memoria.charona.features.role.domain.models.Role;
import fr.fae.memoria.charona.features.role.domain.repositories.IRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Profile({"jpa", "test"})
@Repository
public class RoleRepositoryJpaAdapter implements IRoleRepository {

    private static final Logger log = LoggerFactory.getLogger(RoleRepositoryJpaAdapter.class);

    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryJpaAdapter(RoleJpaRepository roleJpaRepository) {
        log.debug("Création du RoleRepositoryJpaAdapter");
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public List<Role> findAll() {
        log.debug("Recherche de tous les rôles");
        return roleJpaRepository.findAll();
    }

    @Override
    public List<Role> findAllAskable() {
        log.debug("Recherche de tous les rôles demandables");
        return roleJpaRepository.findAllByAskableTrue();
    }

    @Override
    public List<Role> findAllAssignable() {
        log.debug("Recherche de tous les rôles assignables");
        return roleJpaRepository.findAllByAssignableTrue();
    }

    @Override
    public Optional<Role> findByCode(String code) {
        log.debug("Recherche d'un rôle par code");
        return roleJpaRepository.findByCode(code);
    }

    @Override
    public Role save(Role role) {
        log.debug("Enregistrement d'un rôle");
        return roleJpaRepository.save(role);
    }

    @Override
    public void deleteByCode(String code) {
        log.debug("Suppression d'un rôle par code");
        roleJpaRepository.deleteByCode(code);
    }

    @Override
    public boolean existsByCode(String code) {
        log.debug("Vérification de l'existence d'un rôle par code");
        return roleJpaRepository.existsByCode(code);
    }
}
