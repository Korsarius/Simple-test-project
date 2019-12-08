package ua.nure.authentication;

/**
 * Stands for login user DTO, that contains user login and password.
 * It its obtained during user authorization and comes as a parameter to
 * {@link LoginService#login(LoginCredentials)}.
 */
public class LoginCredentials {

    private final String login;
    private final String password;

    private LoginCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String login() {
        return login;
    }

    public String password() {
        return password;
    }

    /**
     * It is used for comfortable creation immutable LoginCredentials object.
     */
    public static class Builder {

        private String login;
        private String password;

        public Builder withLogin(String login) {
            if (login == null) {
                throw new IllegalArgumentException("Login is null!");
            }
            this.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            if (password == null) {
                throw new IllegalArgumentException("Password is null!");
            }
            this.password = password;
            return this;
        }

        public LoginCredentials build() {
            return new LoginCredentials(login, password);
        }

    }

}
