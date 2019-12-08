package ua.nure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.nure.entity.Role;
import ua.nure.entity.User;
import ua.nure.registration.RegistrationCredentials;
import ua.nure.registration.RegistrationFailureException;
import ua.nure.registration.RegistrationService;
import ua.nure.repository.RoleRepository;
import ua.nure.repository.impl.RoleRepositoryImpl;
import ua.nure.repository.impl.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class RegistrationStubTest {

    private final RegistrationService registrationService;
    private final Collection<User> users = new ArrayList<>();
    private final Collection<Role> roles = new ArrayList<>();

    private User user;
    private Role role;

    public RegistrationStubTest() {
        registrationService = new RegistrationService(new UserRepositoryStub(), new RoleRepositoryStub());
    }

    @BeforeEach
    void setUp() {
        users.clear();
        roles.clear();
        user = new User(1L, "testLogin",
                "testPassword",
                "/testPath/",
                new Role(1, "testRole"));
        role = new Role(1, "Admin");
        users.add(user);
        roles.add(role);
    }

    @Test
    void testRegister() throws RegistrationFailureException, UserNotFoundException {
        RegistrationCredentials registrationCredentials = new RegistrationCredentials.Builder()
                .withLogin("testRegistrationLogin")
                .withPassword("testPassword")
                .withAvatar("/app/avatar-path")
                .build();
        RegistrationCredentials userDetails = registrationService.register(registrationCredentials);
        Assertions.assertEquals(userDetails.login(), userDetails.login(),
                "The process registration new user was failed.");
    }

    @Test
    void testRegistrationFailureException() {
        String failMessage = "Expected that the method `register` throws RegistrationFailureException, but it didn't.";
        RegistrationCredentials registrationCredentials = new RegistrationCredentials.Builder()
                /*user with the same login is exist */
                .withLogin("testLogin")
                .withPassword("testPassword")
                .withAvatar("/app/avatar-path")
                .build();

        Assertions.assertThrows(RegistrationFailureException.class,
                () -> {
                    registrationService.register(registrationCredentials);
                }, failMessage);
    }


    class UserRepositoryStub extends UserRepositoryImpl {

        @Override
        public User findUserByLogin(String login) {
            User foundUser = null;
            for (User user : users) {
                if (login.equals(user.login())) {
                    foundUser = user;
                }
            }
            return foundUser;
        }

        @Override
        public void add(User entity) {
            entity.setId(2L);
            users.add(entity);
        }

        @Override
        public User findUserById(long userId) {
            User foundUser = null;
            for (User user : users) {
                if (userId == user.id()) {
                    foundUser = user;
                }
            }
            return foundUser;
        }

        @Override
        public void update(User entity) {
            entity.setId(user.id());
            users.remove(user);
            users.add(entity);
        }

        @Override
        public void remove(User entity) {
            users.remove(entity);
        }

    }

    /**
     * This class represents a simple stub to {@link RoleRepository}.
     */
    class RoleRepositoryStub extends RoleRepositoryImpl {

        @Override
        public Role findRoleById(long id) {
            Role foundRole = null;
            for (Role role : roles) {
                if (role.id() == id) {
                    foundRole = role;
                }
            }
            return foundRole;
        }

    }

}
