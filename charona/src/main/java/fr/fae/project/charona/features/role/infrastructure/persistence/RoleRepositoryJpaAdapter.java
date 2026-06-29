package fr.fae.project.charona.features.role.infrastructure.persistence;

import fr.fae.project.charona.features.role.domain.models.Role;
import fr.fae.project.charona.features.role.domain.repositories.IRoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Profile({"jpa", "test"})
@Repository
public class RoleRepositoryJpaAdapter implements IRoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryJpaAdapter(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleJpaRepository.findAll();
    }

    @Override
    public List<Role> findAllAskable() {
        return roleJpaRepository.findAllByAskableTrue();
    }

    @Override
    public List<Role> findAllAssignable() {
        return roleJpaRepository.findAllByAssignableTrue();
    }

    @Override
    public Optional<Role> findByCode(String code) {
        return roleJpaRepository.findByCode(code);
    }

    @Override
    public Role save(Role role) {
        return roleJpaRepository.save(role);
    }

    @Override
    public void deleteByCode(String code) {
        roleJpaRepository.deleteByCode(code);
    }

    @Override
    public boolean existsByCode(String code) {
        return roleJpaRepository.existsByCode(code);
    }
}
