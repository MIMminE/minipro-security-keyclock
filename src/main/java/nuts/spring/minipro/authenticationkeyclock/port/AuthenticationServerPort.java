package nuts.spring.minipro.authenticationkeyclock.port;

import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;

public interface AuthenticationServerPort {

    AccessTokenResponse requestToken(String username, String password);

    AuthorizationResponse validationToken(String token);
}
