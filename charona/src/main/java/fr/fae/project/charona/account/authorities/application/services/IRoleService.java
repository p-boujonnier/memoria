package fr.fae.project.charona.account.authorities.application.services;

import fr.fae.project.charona.account.authorities.domain.models.Role;
import fr.fae.project.charona.common.ServiceResponse;

import java.util.List;

public interface IRoleService {
    ServiceResponse<List<Role>> findAll();
    ServiceResponse<List<Role>> findAllAskable();
    ServiceResponse<List<Role>> findAllAssignable();
    ServiceResponse<Role> findByCode(String code);
}
