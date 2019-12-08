package ua.nure.authentication;

/**
 * Exception that is thrown when logging in was failed if user credentials are invalid,
 * such user does not exist or something went wrong with database.
 */
public class AuthenticationException extends Exception {

    private static final long serialVersionUID = -4593654487526661068L;

    public AuthenticationException(String message) {
        super(message);
    }

}
