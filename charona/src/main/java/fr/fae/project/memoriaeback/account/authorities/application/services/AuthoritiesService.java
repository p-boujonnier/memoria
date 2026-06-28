package fr.fae.project.memoriaeback.account.authorities.application.services;

import fr.fae.project.memoriaeback.common.ServiceResponse;
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
