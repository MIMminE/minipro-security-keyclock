package nuts.spring.minipro.authenticationkeyclock.usecase.get;

import java.util.List;
import java.util.Map;

public interface GetResourceUseCase {

    List<Map<String, String>> getResource(String role);

}
