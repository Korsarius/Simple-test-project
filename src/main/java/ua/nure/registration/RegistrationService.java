package ua.nure.registration;

import ua.nure.entity.User;
import ua.nure.repository.RoleRepository;
import ua.nure.repository.UserRepository;

/**
 * This class represents a service layer to handle all business logic related to user registration.
 */
public class RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegistrationService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Registers user using User repository if such user does not exist.
     *
     * @param credentials contains user's login, password and avatar file.
     * @return the User entity;
     * @throws RegistrationFailureException is thrown if user credentials are invalid.
     */
    public RegistrationCredentials register(RegistrationCredentials credentials) throws
            RegistrationFailureException {
        User userByLogin = userRepository.findUserByLogin(credentials.login());
        if (userByLogin != null) {
            throw new RegistrationFailureException("User exists!");
        }
        User newUser = new User();
        newUser.setLogin(credentials.login());
        newUser.setPassword(credentials.password());
        newUser.setAvatar(credentials.avatar());
        newUser.setRole(roleRepository.findRoleByName("User"));
        userRepository.add(newUser);
        return credentials;
    }

}
