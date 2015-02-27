package de.klickreform.dropauth.oauth2.request;

import de.klickreform.dropauth.exceptions.InvalidGrantTypeException;
import de.klickreform.dropauth.exceptions.MissingRequiredArgumentException;
import de.klickreform.dropauth.oauth2.validation.RequestValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Abstract implementation of the OAuthRequest interface.
 *
 * @author Benjamin Bestmann
 */
public abstract class AbstractOAuthRequest implements OAuthRequest {

    protected HttpServletRequest request;
    protected RequestValidator<HttpServletRequest> requestValidator;

    public AbstractOAuthRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void validate() throws MissingRequiredArgumentException, InvalidGrantTypeException {
        // Call the validator in order to perform validation
        this.requestValidator.validate(this.request);
    }

}
