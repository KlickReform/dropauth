package de.klickreform.dropauth.exceptions;

import de.klickreform.dropkit.exception.ApiException;

/**
 * Exception to be thrown if a request contains an unknown or empty scope.
 *
 * @author Benjamin Bestmann
 */
public class InvalidScopeException extends ApiException {

    public InvalidScopeException(String message) {
        super(message);
    }

    public InvalidScopeException(String errorCode, String message) {
        super(errorCode, message);
    }

    @Override
    public int getDefaultStatusCode() {
        return 400;
    }

}
