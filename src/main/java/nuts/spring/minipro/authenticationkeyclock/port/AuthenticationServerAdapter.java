package nuts.spring.minipro.authenticationkeyclock.port;

import jakarta.annotation.PostConstruct;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.authorization.client.representation.TokenIntrospectionResponse;
import org.keycloak.authorization.client.resource.ProtectedResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.keycloak.representations.idm.authorization.Permission;

import java.util.List;
import java.util.Map;

public class AuthenticationServerAdapter implements AuthenticationServerPort {

    private AuthzClient authzClient;

    @Override
    public AccessTokenResponse requestToken(String username, String password) {
        return authzClient.obtainAccessToken(username, password);
    }

    @Override
    public AuthorizationResponse validationToken(String token) {
        try {

            List<Permission> permissions1 = authzClient.authorization(token).getPermissions(new AuthorizationRequest());



            ProtectedResource resource = authzClient.protection().resource();


            TokenIntrospectionResponse tokenIntrospectionResponse = authzClient.protection().introspectRequestingPartyToken(token);

            List<Permission> permissions = tokenIntrospectionResponse.getPermissions();
            for (Permission permission : permissions) {
                System.out.println(permission);
            }

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