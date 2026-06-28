package fr.fae.project.memoriaeback.account.authorities.domain.ports;

import fr.fae.project.memoriaeback.account.authorities.domain.models.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository {

    /**
     * Returns all roles.
     * @return a list of roles.
     */
    List<Role> findAll();

    /**
     * Returns all roles that can be assigned to a user.
     * @return a list of roles.
     */
    List<Role> findAllAskable();

    /**
     * Returns all roles that can be assigned to a user.
     * @return a list of roles.
     */
    List<Role> findAllAssignable();

    /**
     * Returns a role by its code.
     * @param code the role code.
     * @return the role.
     */
    Optional<Role> findByCode(String code);

    /**
     * Saves a role.
     * @param role the role to save.
     * @return the saved role.
     */
    Role save(Role role);

    /**
     * Deletes a role by its code.
     * @param code the role code.
     */
    void deleteByCode(String code);

    /**
     * Checks if a role exists by its code.
     * @param code the role code.
     * @return true if the role exists, false otherwise.
     */
    boolean existsByCode(String code);
}
