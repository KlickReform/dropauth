package de.klickreform.dropauth.exceptions;

import de.klickreform.dropkit.exception.ApiException;

/**
 * Exception to be thrown if a required parameter on a request could not be found.
 *
 * @author Benjamin Bestmann
 */
public class MissingRequiredArgumentException extends ApiException {

    public MissingRequiredArgumentException(String message) {
        super(message);
    }

    public MissingRequiredArgumentException(String errorCode, String message) {
        super(errorCode, message);
    }

    @Override
    public int getDefaultStatusCode() {
        return 400;
    }

}
