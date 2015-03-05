package de.klickreform.dropauth.oauth2.token;

import de.klickreform.dropauth.oauth2.client.OAuthClient;
import de.klickreform.dropauth.oauth2.owner.ResourceOwner;
import de.klickreform.dropauth.oauth2.scope.ScopeSet;
import de.klickreform.dropkit.exception.UnauthorizedException;

/**
 * Interface for TokenService implementations.
 *
 * @author Benjamin Bestmann
 */
public interface TokenService {

    public AccessToken createAccessToken(OAuthClient client, ResourceOwner resourceOwner, ScopeSet scopeSet);

    public AccessToken resolveToken(String token);

    public AccessToken refreshToken(OAuthClient client, String refreshToken) throws UnauthorizedException;

    public void revokeToken(String token);

}
