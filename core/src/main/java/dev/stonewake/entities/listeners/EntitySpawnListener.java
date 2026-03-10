package dev.stonewake.entities.listeners;

import dev.stonewake.entities.Entity;
import dev.stonewake.entities.events.EntitySpawnEvent;

public interface EntitySpawnListener {
    void entitySpawned(EntitySpawnEvent event);
}
