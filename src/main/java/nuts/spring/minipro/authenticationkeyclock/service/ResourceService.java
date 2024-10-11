package nuts.spring.minipro.authenticationkeyclock.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nuts.spring.minipro.authenticationkeyclock.repository.ResourceEntity;
import nuts.spring.minipro.authenticationkeyclock.repository.ResourceRepository;
import nuts.spring.minipro.authenticationkeyclock.repository.ResourceType;
import nuts.spring.minipro.authenticationkeyclock.usecase.create.CreateResourceUseCase;
import nuts.spring.minipro.authenticationkeyclock.usecase.delete.DeleteResourceUseCase;
import nuts.spring.minipro.authenticationkeyclock.usecase.get.GetResourceUseCase;
import nuts.spring.minipro.authenticationkeyclock.usecase.token.RequestTokenUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResourceService {


    private final ResourceRepository resourceRepository;

    private final RequestTokenUseCase requestTokenUseCase;
    private final GetResourceUseCase getResourceUseCase;
    private final CreateResourceUseCase createResourceUseCase;
    private final DeleteResourceUseCase deleteResourceUseCase;

    public List<ResourceEntity> getAllResources() {
        return resourceRepository.findAll();
    }

    public List<Map<String, String>> getResources(String role) {
        return getResourceUseCase.getResource(role);
    }

    @PostConstruct
    void init() {
        resourceRepository.save(new ResourceEntity("testResourceInteger", "RoleA", ResourceType.integer));
        resourceRepository.save(new ResourceEntity("testResourceString", "RoleB", ResourceType.string));
    }
}
