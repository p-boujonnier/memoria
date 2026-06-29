package fr.fae.project.charona.features.role.domain.services;

import fr.fae.project.charona.shared.ServiceResponse;

import java.util.UUID;

public interface IAuthoritiesService {
    ServiceResponse<Void> assignRole(UUID  userId, String roleCode);
    ServiceResponse<Void> revokeRole(UUID userId, String roleCode);
}
