package nuts.spring.minipro.authenticationkeyclock.controller;

import lombok.RequiredArgsConstructor;
import nuts.spring.minipro.authenticationkeyclock.port.AuthenticationServerPort;
import nuts.spring.minipro.authenticationkeyclock.repository.ResourceEntity;
import nuts.spring.minipro.authenticationkeyclock.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ResourceRestController {

    private final ResourceService resourceService;
    private final AuthenticationServerPort authenticationServerAdapter;

    @GetMapping("/resources")
    ResponseEntity<List<ResourceEntity>> getAllResources() {
        return ResponseEntity.ok(resourceService.getAllResources());
    }

    @GetMapping("/resource/{role}")
    ResponseEntity<List<Map<String, String>>> getResource(@PathVariable String role,
                                                          @RequestHeader(value = "Authorization") String authorization) {

        // 토큰 검증
        Map<String, Object> validationResult = authenticationServerAdapter.validationResult(authorization.substring(6));
        System.out.println(validationResult);
        List<Map<String, String>> resource = resourceService.getResources(role);


        return ResponseEntity.ok(resource);
    }


}
