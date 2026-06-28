package fr.fae.project.memoriaeback.account.authorities.application.services;

import fr.fae.project.memoriaeback.account.authorities.domain.models.Role;
import fr.fae.project.memoriaeback.account.authorities.domain.ports.IRoleRepository;
import fr.fae.project.memoriaeback.common.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService{
    
    private final IRoleRepository roleRepository;
    
    public RoleService(IRoleRepository roleRepository) {
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
