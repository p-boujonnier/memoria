package fr.fae.project.charona.features.role.domain.services;

import fr.fae.project.charona.features.role.domain.models.Role;
import fr.fae.project.charona.shared.ServiceResponse;

import java.util.List;

public interface IRoleService {
    ServiceResponse<List<Role>> findAll();
    ServiceResponse<List<Role>> findAllAskable();
    ServiceResponse<List<Role>> findAllAssignable();
    ServiceResponse<Role> findByCode(String code);
}
