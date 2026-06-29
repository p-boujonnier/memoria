package fr.fae.project.charona.features.role.application.services;

import fr.fae.project.charona.features.role.domain.services.IAuthoritiesService;
import fr.fae.project.charona.shared.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthoritiesService implements IAuthoritiesService {
    @Override
    public ServiceResponse<Void> assignRole(UUID userId, String roleCode) {
        return null;
    }

    @Override
    public ServiceResponse<Void> revokeRole(UUID userId, String roleCode) {
        return null;
    }
}
