package ua.nure.repository;

import ua.nure.entity.Role;

/**
 * Interface for the repository pattern. Supplements the operations of the base repository with new specific
 * operations necessary for working with role user.
 */
public interface RoleRepository extends Repository<Role> {

    /**
     * Returns a role entity by id contained in the repository.
     *
     * @param id the role entity's unique id
     * @return the role entity {@link Role}
     */
    Role findRoleById(long id);

    /**
     * Returns an entity by name contained in the repository.
     *
     * @param name the role name
     * @return the role entity {@link Role}
     */
    Role findRoleByName(String name);

}
