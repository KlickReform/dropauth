package de.klickreform.dropauth.oauth2.validation;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import de.klickreform.dropauth.OAuthSettings;
import de.klickreform.dropauth.exceptions.InvalidGrantTypeException;
import de.klickreform.dropauth.exceptions.MissingRequiredArgumentException;
import junit.framework.TestCase;
import org.eclipse.jetty.server.Request;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;

@RunWith(MockitoJUnitRunner.class)
public class AccessTokenRequestValidatorTest extends TestCase {

    private static AccessTokenRequestValidator validator;

    @BeforeClass
    public static void runBeforeTests() {
        validator = new AccessTokenRequestValidator();
    }

    @Test
    public void validateRequiredParameters() throws MissingRequiredArgumentException {
        HttpServletRequest request = Mockito.mock(Request.class);
        Mockito.doReturn("password").when(request).getParameter(OAuthSettings.Params.GRANT_TYPE);
        Mockito.doReturn("scope1 scope2").when(request).getParameter(OAuthSettings.Params.SCOPE);
        validator.validateRequiredParameters(request);
    }

    @Test
    public void validateRequiredFormParams() throws MissingRequiredArgumentException {
        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
        params.add(OAuthSettings.Params.GRANT_TYPE,"password");
        params.add(OAuthSettings.Params.USERNAME,"user");
        params.add(OAuthSettings.Params.PASSWORD,"password");
        params.add(OAuthSettings.Params.CLIENT_ID,"client");
        params.add(OAuthSettings.Params.SCOPE,"scope1 scope2");
        validator.validateRequiredFormParameters(params);
    }

    @Test
    public void validate() throws MissingRequiredArgumentException, InvalidGrantTypeException {
        HttpServletRequest request = Mockito.mock(Request.class);
        Mockito.doReturn("password").when(request).getParameter(OAuthSettings.Params.GRANT_TYPE);
        Mockito.doReturn("user").when(request).getParameter(OAuthSettings.Params.USERNAME);
        Mockito.doReturn("client").when(request).getParameter(OAuthSettings.Params.CLIENT_ID);
        Mockito.doReturn("scope1 scope2").when(request).getParameter(OAuthSettings.Params.SCOPE);
        validator.validate(request);
    }

    @Test
    public void validateFormParams() throws MissingRequiredArgumentException, InvalidGrantTypeException {
        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
        params.add(OAuthSettings.Params.GRANT_TYPE,"password");
        params.add(OAuthSettings.Params.USERNAME,"user");
        params.add(OAuthSettings.Params.PASSWORD,"password");
        params.add(OAuthSettings.Params.CLIENT_ID,"client");
        params.add(OAuthSettings.Params.SCOPE,"scope1 scope2");
        validator.validate(params);
    }

}