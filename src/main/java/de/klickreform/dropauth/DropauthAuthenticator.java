package de.klickreform.dropauth;

import com.google.common.base.Optional;
import de.klickreform.dropauth.oauth2.owner.ResourceOwner;
import de.klickreform.dropauth.oauth2.owner.ResourceOwnerAuthProvider;
import de.klickreform.dropauth.oauth2.token.AccessToken;
import de.klickreform.dropauth.oauth2.token.TokenService;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Date;

/**
 * Implements Dropwizards Authenticator<C,P> interface to work with Dropauth's token-based authentication.
 * Accepts String Tokens and authenticates Principals using an ResourceOwnerAuthProvider instance.
 *
 * @author Benjamin Bestmann
 */
public class DropauthAuthenticator implements Authenticator<String,ResourceOwner> {

    private ResourceOwnerAuthProvider authProvider;
    private TokenService tokenService;

    public DropauthAuthenticator(ResourceOwnerAuthProvider authProvider, TokenService tokenService) {
        this.authProvider = authProvider;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<ResourceOwner> authenticate(String tokenValue) throws AuthenticationException {
        try {
            // Resolve the provided Token using the TokenService
            AccessToken token = tokenService.resolveToken(tokenValue);
            // Check if the token is still valid (not expired)
            Date now = new Date();
            if(token.getExpiryDate().compareTo(now) > 0) {
                ResourceOwner resourceOwner = authProvider.identify(token.getOwner());
                return Optional.of(resourceOwner);
            }
            // If this is reached, AuthenticationException is caused
            return Optional.absent();
        } catch (Exception e) {
            return Optional.absent();
        }
    }

}
