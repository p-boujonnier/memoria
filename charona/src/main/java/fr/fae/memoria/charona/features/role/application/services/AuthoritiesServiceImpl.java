package fr.fae.memoria.charona.features.role.application.services;

import fr.fae.memoria.charona.features.role.domain.models.Role;
import fr.fae.memoria.charona.features.role.domain.services.IAuthoritiesService;
import fr.fae.memoria.charona.features.role.domain.services.IRoleService;
import fr.fae.memoria.charona.features.user.domain.models.User;
import fr.fae.memoria.charona.features.user.domain.services.IUserService;
import fr.fae.memoria.charona.shared.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthoritiesServiceImpl implements IAuthoritiesService {

    private final IRoleService roleService;
    private final IUserService userService;

    public AuthoritiesServiceImpl(IRoleService roleService, IUserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public ServiceResponse<Void> assignRole(UUID userId, String roleCode) {
        User user = userService.findById(userId).getData();
        if (user == null) {
            return new ServiceResponse<>("2100", "Utilisateur introuvable", null);
        }
        Role role = roleService.findByCode(roleCode).getData();
        if (role == null) {
            return new ServiceResponse<>("404", "Rôle introuvable : " + roleCode, null);
        }
        user.addRole(role);
        userService.update(user);
        return new ServiceResponse<>("2004", "Rôle assigné avec succès", null);
    }

    @Override
    public ServiceResponse<Void> revokeRole(UUID userId, String roleCode) {

        return null;
    }

    @Override
    public ServiceResponse<Void> initialize(UUID userId) {
        User user = userService.findById(userId).getData();
        if (user == null) {
            return new ServiceResponse<>("2100", "Utilisateur introuvable", null);
        }
        Role roleUser = roleService.findByCode("ROLE_USER").getData();
        if (roleUser == null) {
            return new ServiceResponse<>("404", "Rôle ROLE_USER introuvable", null);
        }
        Role fabulaUser = roleService.findByCode("ROLE_FABULA_USER").getData();
        if (fabulaUser == null) {
            return new ServiceResponse<>("404", "Rôle ROLE_FABULA_USER introuvable", null);
        }
        Role hephaUser = roleService.findByCode("ROLE_HEPHA_USER").getData();
        if (hephaUser == null) {
            return new ServiceResponse<>("404", "Rôle ROLE_HEPHA_USER introuvable", null);
        }

        user.addRole(roleUser);
        user.addRole(fabulaUser);
        user.addRole(hephaUser);

        userService.update(user);
        return new ServiceResponse<>("2004", "Rôles assignés avec succès", null);
    }
}
