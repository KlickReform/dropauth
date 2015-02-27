package de.klickreform.dropauth.oauth2.validation;

import de.klickreform.dropauth.exceptions.InvalidGrantTypeException;
import de.klickreform.dropauth.exceptions.MissingRequiredArgumentException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Interface to perform validation of OAuth2 requests.
 *
 * @author Benjamin Bestmann
 */
public interface RequestValidator<T extends HttpServletRequest> {

    public void validateRequiredParameters(T request) throws MissingRequiredArgumentException;

    public void validateRequiredFormParameters(MultivaluedMap<String, String> formParams) throws MissingRequiredArgumentException;

    public void validate(T request) throws MissingRequiredArgumentException, InvalidGrantTypeException;

    public void validate(MultivaluedMap<String,String> formParams) throws MissingRequiredArgumentException, InvalidGrantTypeException;

}
