package dev.stonewake.entities.types;

import dev.stonewake.utils.NamespacedKey;

import java.util.Collection;
import java.util.HashMap;

public class EntityRegistry {
    private HashMap<NamespacedKey, EntityType> entityTypes;

    public EntityRegistry() {
        entityTypes = new HashMap<>();
    }

    public void registerEntityType(EntityType entityType) {
        entityTypes.put(entityType.getEntityTypeId(), entityType);
    }

    public EntityType getRegisteredEntityType(NamespacedKey entityTypeId) {
        return entityTypes.get(entityTypeId);
    }

    public Collection<EntityType> getEntityTypes() {
        return entityTypes.values();
    }
}
