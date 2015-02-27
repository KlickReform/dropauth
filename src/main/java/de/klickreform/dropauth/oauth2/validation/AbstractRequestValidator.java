package de.klickreform.dropauth.oauth2.validation;

import de.klickreform.dropauth.exceptions.InvalidGrantTypeException;
import de.klickreform.dropauth.exceptions.MissingRequiredArgumentException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstract implementation of the OAuthValidator interface. Supports basic validation methods
 * for requests.
 *
 * @author Benjamin Bestmann
 */
public abstract class AbstractRequestValidator<T extends HttpServletRequest> implements RequestValidator<T> {

    protected List<String> requiredParams = new ArrayList<String>();

    @Override
    public void validateRequiredParameters(T request) throws MissingRequiredArgumentException {
        final Set<String> missingParameters = new HashSet<String>();
        for(String requiredParam : requiredParams) {
            String value = request.getParameter(requiredParam);
            if(this.isEmpty(value)) {
                missingParameters.add(requiredParam);
            }
        }
        if(!missingParameters.isEmpty()) {
            // Throw exception containing name of the first missing required param
            throw new MissingRequiredArgumentException("Missing Parameter: " + missingParameters.iterator().next());
        }
    }

    @Override
    public void validateRequiredFormParameters(MultivaluedMap<String,String> formParams) throws MissingRequiredArgumentException {
        final Set<String> invalidParameters = new HashSet<String>();
        for(String requiredParam : requiredParams) {
            try {
                List<String> paramList = formParams.get(requiredParam);
                if(paramList.size() != 1) {
                    // If there is none or more then one entry for this param, mark as invalid
                    invalidParameters.add(requiredParam);
                } else {
                    if(this.isEmpty(paramList.iterator().next())) {
                        // If the provided param is empty, mark it as invalid
                        invalidParameters.add(requiredParam);
                    }
                }
            } catch(NullPointerException e) {
                throw new MissingRequiredArgumentException("Missing Parameter: " + requiredParam);
            }
        }
        if(!invalidParameters.isEmpty()) {
            // Throw exception containing name of the first missing required param
            throw new MissingRequiredArgumentException("Missing Parameter: " + invalidParameters.iterator().next());
        }
    }

    @Override
    public void validate(T request) throws MissingRequiredArgumentException, InvalidGrantTypeException {
        // Call all validation methods
        this.validateRequiredParameters(request);
    }

    @Override
    public void validate(MultivaluedMap<String,String> formParams) throws MissingRequiredArgumentException, InvalidGrantTypeException {
        // Call all validation methods when using form params from POST requests
        this.validateRequiredFormParameters(formParams);
    }

    public static boolean isEmpty(String value) {
        // If value is null or empty return true
        return value == null || "".equals(value);
    }

}
