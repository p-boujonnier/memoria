package fr.fae.project.charona.features.role.application.services;

import fr.fae.project.charona.features.role.domain.models.Role;
import fr.fae.project.charona.features.role.domain.repositories.IRoleRepository;
import fr.fae.project.charona.features.role.domain.services.IRoleService;
import fr.fae.project.charona.shared.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    
    private final IRoleRepository roleRepository;
    
    public RoleServiceImpl(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public ServiceResponse<List<Role>> findAll() {
        return new ServiceResponse<>("200", "List of roles retrieved successfully", roleRepository.findAll());
    }

    @Override
    public ServiceResponse<List<Role>> findAllAskable() {
        return new ServiceResponse<>("200", "List of askable roles retrieved successfully", roleRepository.findAllAskable());
    }

    @Override
    public ServiceResponse<List<Role>> findAllAssignable() {
        return new ServiceResponse<>("200", "List of assignable roles retrieved successfully", roleRepository.findAllAssignable());
    }

    @Override
    public ServiceResponse<Role> findByCode(String code) {
        return roleRepository.findByCode(code)
                .map(role -> new ServiceResponse<>("200", "Role retrieved successfully", role))
                .orElse(new ServiceResponse<>("404", "Role not found", null));
    }
}
