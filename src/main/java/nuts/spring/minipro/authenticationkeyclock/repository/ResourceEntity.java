package nuts.spring.minipro.authenticationkeyclock.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class ResourceEntity {

    @Id
    private String id;

    private String role;

    private String resourceName;

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @PrePersist
    void onCreate(){
        this.id = UUID.randomUUID().toString();
    }

    public ResourceEntity(String resourceName, String role, ResourceType resourceType) {
        this.resourceName = resourceName;
        this.role = role;
        this.resourceType = resourceType;
    }
}
