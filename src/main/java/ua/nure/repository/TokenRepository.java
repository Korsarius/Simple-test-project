package ua.nure.repository;

import ua.nure.entity.User;
import ua.nure.securityservice.Token;

/**
 * Interface for the repository pattern. Supplements the operations of the base repository with new specific
 * operations necessary for working with security token.
 */
public interface TokenRepository extends Repository<Token> {

    /**
     * Returns a token entity by {@link User} id contained in the repository.
     *
     * @param id the user entity's unique id
     * @return the token entity {@link Token}
     */
    Token findByUserId(long id);

    /**
     * Returns a token entity by id contained in the repository.
     *
     * @param id the token entity's unique id
     * @return the token entity {@link Token}
     */
    Token findById(long id);

    Token findBySecurityString(String securityString);

}
