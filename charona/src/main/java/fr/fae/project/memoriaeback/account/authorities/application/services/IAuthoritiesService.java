package fr.fae.project.memoriaeback.account.authorities.application.services;

import fr.fae.project.memoriaeback.account.authorities.domain.models.Role;
import fr.fae.project.memoriaeback.common.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface IAuthoritiesService {
    ServiceResponse<Void> assignRole(UUID  userId, String roleCode);
    ServiceResponse<Void> revokeRole(UUID userId, String roleCode);
}
