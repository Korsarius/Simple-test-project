package ua.nure.authentication;


import ua.nure.entity.User;
import ua.nure.repository.TokenRepository;
import ua.nure.repository.UserRepository;
import ua.nure.securityservice.SecurityService;
import ua.nure.securityservice.SecurityToken;

/**
 * This class represents a service layer to handle all business logic related to user log in.
 * If user exists, returns its security token.
 */
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final SecurityService securityService;

    public AuthService(UserRepository userRepository, TokenRepository tokenRepository,
                       SecurityService securityService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.securityService = securityService;
    }

    /**
     * Returns security token which is an authorization marker. This way allows not to use a session.
     * Authorizes user using the User repository if such user exists. Otherwise the
     * {@link AuthenticationException} will be thrown.
     *
     * @param credentials contains user's login and password.
     * @return the security token {@link SecurityToken}.
     * @throws AuthenticationException is thrown if user credentials are invalid.
     */
    public SecurityToken login(LoginCredentials credentials) throws AuthenticationException {
        User user = userRepository.findUserByLogin(credentials.login());
        if (user == null) {
            throw new AuthenticationException("User does not exist!");
        }
        if (!credentials.password().equals(user.password())) {
            throw new AuthenticationException("Incorrect password!");
        }
        SecurityToken securityToken = securityService.createSecurityToken(user);

        return securityToken;
    }

}
