package nuts.spring.minipro.authenticationkeyclock.controller;

import lombok.RequiredArgsConstructor;
import nuts.spring.minipro.authenticationkeyclock.port.AuthenticationServerPort;
import nuts.spring.minipro.authenticationkeyclock.repository.ResourceEntity;
import nuts.spring.minipro.authenticationkeyclock.service.ResourceService;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ResourceRestController {

    private final ResourceService resourceService;
    private final AuthenticationServerPort authenticationServerAdapter;

    @PostMapping("/token")
    ResponseEntity<Object> requestToken(@RequestBody RequestToken requestToken) {
        String username = requestToken.getId();
        String password = requestToken.getPassword();

        return ResponseEntity.ok(authenticationServerAdapter.requestToken(username, password));
    }


    @GetMapping("/resources")
    ResponseEntity<List<ResourceEntity>> getAllResources() {
        return ResponseEntity.ok(resourceService.getAllResources());
    }

    @GetMapping("/resource/{role}")
    ResponseEntity<AuthorizationResponse> getResource(@PathVariable String role,
                                                          @RequestHeader(value = "Authorization") String authorization) {
        System.out.println("sssss");
        // 토큰 검증
        AuthorizationResponse response = authenticationServerAdapter.validationToken(authorization.substring(6));


        return ResponseEntity.ok(response);
    }
}
