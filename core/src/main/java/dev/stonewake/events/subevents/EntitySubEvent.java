package dev.stonewake.events.subevents;

import dev.stonewake.entities.Entity;
import dev.stonewake.entities.types.LivingEntityType;
import dev.stonewake.utils.NamespacedKey;
import dev.stonewake.world.GameWorld;

public abstract class EntitySubEvent<T extends Entity, U extends LivingEntityType> extends WorldSubEvent {
    public T targettedEntity;
    public final U targettedEntityType;
    public NamespacedKey targettedEntityId;

    public EntitySubEvent(Object eventSource, T targettedEntity, String[] eventTags, GameWorld world) {
        super(eventSource, world, eventTags);

        this.targettedEntity = targettedEntity;
        this.targettedEntityType = (U)world.getEntityRegistry().getRegisteredEntityType(targettedEntity.getEntityId());
        this.targettedEntityId = targettedEntity.getEntityId();
    }

    public EntitySubEvent(Object eventSource, NamespacedKey targettedEntityId, String[] eventTags, GameWorld world) {
        super(eventSource, world, eventTags);

        this.targettedEntity = null;
        this.targettedEntityType = (U)world.getEntityRegistry().getRegisteredEntityType(targettedEntityId);
        this.targettedEntityId = targettedEntityId;
    }
}
