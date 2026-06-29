package fr.fae.project.charona.account.authorities.application.services;

import fr.fae.project.charona.common.ServiceResponse;

import java.util.UUID;

public interface IAuthoritiesService {
    ServiceResponse<Void> assignRole(UUID  userId, String roleCode);
    ServiceResponse<Void> revokeRole(UUID userId, String roleCode);
}
