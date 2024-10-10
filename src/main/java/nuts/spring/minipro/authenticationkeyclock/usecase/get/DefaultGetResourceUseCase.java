package nuts.spring.minipro.authenticationkeyclock.usecase.get;

import java.util.List;
import java.util.Map;

public class DefaultGetResourceUseCase implements GetResourceUseCase {

    @Override
    public List<Map<String, String>> getResource(String role) {
        return List.of();
    }
}
