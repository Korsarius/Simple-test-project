package ua.nure.repository;

import ua.nure.entity.User;
import ua.nure.securityservice.SecurityToken;

/**
 * Interface for the repository pattern. Supplements the operations of the base repository with new specific
 * operations necessary for working with users.
 * Realizes CRUD-operations.
 */
public interface UserRepository extends Repository<User> {

    /**
     * Returns user from repository by obtained login.
     *
     * @param login user login.
     * @return user entity.
     */
    User findUserByLogin(String login);

    /**
     * Returns user from repository by obtained id.
     *
     * @param id user id.
     * @return user entity.
     */
    User findUserById(long id);

    /**
     * Returns user from repository by obtained authorization token.
     *
     * @param token user authorization token
     * @return user entity.
     */
    User findUserByToken(SecurityToken token);

    /**
     * Logs in user, generates authorization token for logged in user.
     *
     * @param user user entity.
     */
    SecurityToken authUser(User user);

    /**
     * Logs out user by authorization token.
     *
     * @param token
     */
    void logoutUser(SecurityToken token);

}
