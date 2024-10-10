package nuts.spring.minipro.authenticationkeyclock.port;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationServerAdapter implements AuthenticationServerPort {

    String requestUrl = String.format("http://localhost:8888/realms/auth-service/protocol/openid-connect/certs");
    JWKSet jwkSet;

    @Override
    public Map<String, Object> validationResult(String token) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            System.out.println("검증 시도");
            SignedJWT signedJWT = SignedJWT.parse(token);
            String keyID = signedJWT.getHeader().getKeyID();
            JWK jwk = jwkSet.getKeyByKeyId(keyID);

            RSAKey rsaKey = (RSAKey) jwk;
            RSAPublicKey rsaPublicKey = rsaKey.toRSAPublicKey();
            boolean verify = signedJWT.verify(new RSASSAVerifier(rsaPublicKey));

            resultMap.put("result", verify);

            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            Map<String, Object> claims = signedJWT.getJWTClaimsSet().getClaims();
            Map o = (Map) claims.get("resource_access");
            System.out.println(o);
            ObjectMapper objectMapper = new ObjectMapper();

            ResponseResourceAccess responseResourceAccess = objectMapper.convertValue(o, ResponseResourceAccess.class);
            System.out.println(responseResourceAccess);
            ResponseResourceAccess.Account account = responseResourceAccess.getAccount();

            List<String> roles = account.getRoles();
            for (String role : roles) {
                System.out.println(role);
            }

            System.out.println(expirationTime);
            System.out.println(claims);


            return resultMap;

        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }

    }

    @PostConstruct
    @Override
    public void loadJWKSet() {
        try {
            this.jwkSet = JWKSet.load(new URL(requestUrl));
            System.out.println("loadJWKSet!");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
