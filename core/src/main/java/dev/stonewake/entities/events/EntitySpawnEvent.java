package dev.stonewake.entities.events;

import dev.stonewake.entities.Entity;

public class EntitySpawnEvent {
    public final Entity spawnedEntity;
    public final float entitySpawnX;
    public final float entitySpawnY;

    public EntitySpawnEvent(Entity spawnedEntity, float entitySpawnX, float entitySpawnY) {
        this.spawnedEntity = spawnedEntity;
        this.entitySpawnX = entitySpawnX;
        this.entitySpawnY = entitySpawnY;
    }
}
