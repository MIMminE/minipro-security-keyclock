package nuts.spring.minipro.authenticationkeyclock.usecase.create;

import nuts.spring.minipro.authenticationkeyclock.repository.ResourceEntity;
import nuts.spring.minipro.authenticationkeyclock.repository.ResourceType;

public interface CreateResourceUseCase {

    ResourceEntity createResource(String resourceName, String role, ResourceType resourceType);

}
