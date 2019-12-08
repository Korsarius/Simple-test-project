package ua.nure.securityservice;

/**
 * It is a string containing a hash that matches the current user.
 * This hash must be encoded to prevent cracking the user's password.
 */
public class SecurityToken {

    private final String securityToken;

    public SecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String securityToken() {
        return securityToken;
    }

    /**
     * It is used for comfortable creation immutable AuthorizationToken object.
     */
    public static class Builder {

        private String securityToken;

        public Builder withToken(String token) throws IllegalArgumentException {
            if (token == null) {
                throw new IllegalArgumentException("Token is null");
            }
            this.securityToken = token;
            return this;
        }

        public SecurityToken build() {
            return new SecurityToken(securityToken);
        }

    }

}
