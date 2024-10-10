package nuts.spring.minipro.authenticationkeyclock.usecase.create;

import nuts.spring.minipro.authenticationkeyclock.repository.ResourceEntity;
import nuts.spring.minipro.authenticationkeyclock.repository.ResourceType;

public class DefaultCreateResourceUseCase implements CreateResourceUseCase{
    @Override
    public ResourceEntity createResource(String resourceName, String role, ResourceType resourceType) {
        return null;
    }
}
