package de.klickreform.dropauth;

/**
 * Final class that contains all basic settings for the OAuth provider.
 *
 * @author Benjamin Bestmann
 */
public final class OAuthSettings {

    public static final String SCOPE_DELIMITER = "\\s";
    public static final String DEFAULT_TOKEN_TYPE = "Bearer";

    public static final class Params {
        // Authorization request params
        public static final String CLIENT_ID = "client_id";
        public static final String REDIRECT_URI = "redirect_uri";
        public static final String SCOPE = "scope";
        public static final String RESPONSE_TYPE = "response_type";
        public static final String GRANT_TYPE = "grant_type";
        public static final String STATE = "state";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
    }

    public static final class GrantTypes {
        public static final String AUTH_CODE = "authorization_code";
        public static final String PASSWORD = "password";
        public static final String CLIENT_CREDENTIALS = "client_credentials";
    }

}
