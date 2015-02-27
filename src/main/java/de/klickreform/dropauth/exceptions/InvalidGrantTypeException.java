package de.klickreform.dropauth.exceptions;

import de.klickreform.dropkit.exception.ApiException;

/**
 * Exception to be thrown if a request contains an empty or invalid grant type parameter.
 *
 * @author Benjamin Bestmann
 */
public class InvalidGrantTypeException extends ApiException {

    public InvalidGrantTypeException(String message) {
        super(message);
    }

    public InvalidGrantTypeException(String errorCode, String message) {
        super(errorCode, message);
    }

    @Override
    public int getDefaultStatusCode() {
        return 400;
    }

}
