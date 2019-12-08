package ua.nure;

/**
 * Exception that is thrown when an user was not found in datasource.
 */
public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = -4593654487526661068L;

    public UserNotFoundException(String message) {
        super(message);
    }

}



