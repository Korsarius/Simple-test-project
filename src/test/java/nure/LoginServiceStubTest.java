package nure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.nure.authentication.AuthenticationException;
import ua.nure.authentication.LoginCredentials;
import ua.nure.authentication.LoginService;
import ua.nure.entity.Role;
import ua.nure.entity.User;
import ua.nure.repository.TokenRepository;
import ua.nure.repository.UserRepository;
import ua.nure.repository.impl.TokenRepositoryImpl;
import ua.nure.repository.impl.UserRepositoryImpl;
import ua.nure.securityservice.SecurityService;
import ua.nure.securityservice.SecurityToken;
import ua.nure.securityservice.Token;

import java.util.ArrayList;
import java.util.Collection;

public class LoginServiceStubTest {

    private final LoginService loginService;
    private final SecurityService securityService;
    private final Collection<Token> tokens = new ArrayList<>();
    private final Collection<User> users = new ArrayList<>();
    private Token token;
    private User user;

    public LoginServiceStubTest() {
        TokenRepository tokenRepositoryStub = new TokenRepositoryStub();
        securityService = new SecurityService(tokenRepositoryStub);
        loginService = new LoginService(new UserRepositoryStub(), tokenRepositoryStub, new SecurityService(tokenRepositoryStub));
    }

    @BeforeEach
    void setUp() {
        users.clear();
        tokens.clear();
        token = new Token(1, 1, "testLogintestPassword");
        user = new User(1L, "testLogin",
                "testPassword",
                "/testPath/",
                new Role(1, "testRole"));
        users.add(user);
        tokens.add(token);
    }

    @Test
    void testLogin() throws AuthenticationException {
        LoginCredentials loginCredentials = new LoginCredentials.Builder()
                .withLogin("testLogin")
                .withPassword("testPassword")
                .build();
        SecurityToken securityToken = loginService.login(loginCredentials);
        token.setSecurityToken(securityService.MD5Generator(token.securityToken()));

        Assertions.assertEquals(token.securityToken(), securityToken.securityToken(),
                "The id of token does not match.");
    }

    @Test
    void testAuthorizationException() {
        LoginCredentials loginUserWithInvalidPassword = new LoginCredentials.Builder()
                .withLogin("testLogin")
                .withPassword("testInvalidPassword")
                .build();
        String failMessage = "Expected that the method `login` throws AuthorizationException, but it didn't.";

        Assertions.assertThrows(AuthenticationException.class,
                () -> {
                    loginService.login(loginUserWithInvalidPassword);
                }, failMessage);

        LoginCredentials loginUserWithNonExistentLogin = new LoginCredentials.Builder()
                .withLogin("testInvalidLogin")
                .withPassword("testPassword")
                .build();

        Assertions.assertThrows(AuthenticationException.class,
                () -> {
                    loginService.login(loginUserWithNonExistentLogin);
                }, failMessage);
    }

    /**
     * This class represents a simple stub to {@link TokenRepository}.
     */
    class TokenRepositoryStub extends TokenRepositoryImpl {

        @Override
        public Token findByUserId(long id) {
            return token;
        }

        @Override
        public void remove(Token entity) {
            tokens.remove(entity);
        }

    }

    /**
     * This class represents a simple stub to {@link UserRepository}.
     */
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

}
