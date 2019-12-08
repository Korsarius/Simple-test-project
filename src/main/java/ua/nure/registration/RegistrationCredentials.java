package ua.nure.registration;

/**
 * Stands for register user DTO, that contains user login, password and avatar.
 * It its obtained after registration and comes as a parameter to
 * {@link RegistrationService#register(RegistrationCredentials)}.
 */
public class RegistrationCredentials {

    private final String login;
    private final String password;
    private final String avatar;

    private RegistrationCredentials(String login, String password, String avatar) {
        this.login = login;
        this.password = password;
        this.avatar = avatar;
    }

    public String login() {
        return login;
    }

    public String password() {
        return password;
    }

    public String avatar() {
        return avatar;
    }

    /**
     * It is used for comfortable creation immutable RegisterCredentials object.
     */
    public static class Builder {

        private String login;
        private String password;
        private String avatar;

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

        public Builder withAvatar(String avatar) {
            if (avatar == null) {
                throw new IllegalArgumentException("Avatar is null!");
            }
            this.avatar = avatar;
            return this;
        }

        public RegistrationCredentials build() {
            return new RegistrationCredentials(login, password, avatar);
        }

    }

}
