package nuts.spring.minipro.authenticationkeyclock.port;

import jakarta.annotation.PostConstruct;

import java.util.Map;

public interface AuthenticationServerPort {

    void loadJWKSet();

    Map<String, Object> validationResult(String token);
}
