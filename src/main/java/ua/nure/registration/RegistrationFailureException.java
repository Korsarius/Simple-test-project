package ua.nure.registration;

/**
 * Exception that is thrown when registration was failed if user credentials are invalid,
 * such user exists or something went wrong with database.
 */
public class RegistrationFailureException extends Exception {

    private static final long serialVersionUID = 886420025373980374L;

    public RegistrationFailureException(String message) {
        super(message);
    }

}
