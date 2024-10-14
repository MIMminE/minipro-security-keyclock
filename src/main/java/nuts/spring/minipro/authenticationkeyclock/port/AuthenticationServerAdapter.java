package nuts.spring.minipro.authenticationkeyclock.port;

import jakarta.annotation.PostConstruct;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.authorization.client.representation.TokenIntrospectionResponse;
import org.keycloak.authorization.client.resource.AuthorizationResource;
import org.keycloak.authorization.client.resource.PermissionResource;
import org.keycloak.authorization.client.resource.ProtectedResource;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.authorization.*;

import java.util.List;
import java.util.Map;

public class AuthenticationServerAdapter implements AuthenticationServerPort {

    private AuthzClient authzClient;

    @Override
    public AccessTokenResponse requestToken(String username, String password) {

        AuthorizationRequest request = new AuthorizationRequest();
//        request.addPermission("Default Resource");
//        request.addPermission("All Resource");
        AuthorizationResponse authorize = authzClient.authorization(username, password).authorize(request);

        return authzClient.authorization(username, password).authorize(request);
    }

    @Override
    public AuthorizationResponse validationToken(String token) {

        AuthorizationResource authorizationResource = authzClient.authorization(token);

        AuthorizationRequest authorizationRequest = new AuthorizationRequest();
        authorizationRequest.addPermission("ResourceA");
        AuthorizationResponse authResponse = authorizationResource.authorize(authorizationRequest);
        String authToken = authResponse.getToken();

        System.out.println(authToken);

        TokenIntrospectionResponse tokenIntrospectionResponse = authzClient.protection().introspectRequestingPartyToken(authToken);
        String[] all = authzClient.protection().resource().findAll();
        List<Permission> permissions = tokenIntrospectionResponse.getPermissions();
        for (Permission per : permissions) {
            per.getClaims();
        }

        try {
            authorizationRequest.addPermission("ResourceA");
            AuthorizationResponse authorize = authzClient.authorization(token).authorize(authorizationRequest);
            return authzClient.authorization(token).authorize();
        } catch (RuntimeException e) {
            System.out.println("인증 실패 ! ");
            throw e;
        }
    }


    @PostConstruct
    void clientInit() {

        this.authzClient = AuthzClient.create(new Configuration("http://localhost:8888", "mini-keycloak", "mini-project-cli",
                Map.of("secret", "wmyzhpvb6DYZWTiFcNDbhMQcevL090n3"), null));
    }
}