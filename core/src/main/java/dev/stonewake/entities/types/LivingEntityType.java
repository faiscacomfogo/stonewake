package dev.stonewake.entities;

import dev.stonewake.entities.listeners.EntityDieListener;
import dev.stonewake.entities.listeners.EntityHealListener;
import dev.stonewake.entities.listeners.EntityTakeDamageListener;

import java.util.ArrayList;
import java.util.List;

public abstract class LivingEntityType extends EntityType {
    protected List<EntityDieListener> entityDieListeners = new ArrayList<>();
    protected List<EntityHealListener> entityHealListeners = new ArrayList<>();
    protected List<EntityTakeDamageListener> entityTakeDamageListeners = new ArrayList<>();

    public LivingEntityType(int entityId, String[] entitySprites, Class<? extends Entity> entityClass) {
        super(entityId, entitySprites, entityClass);
    }

    public List<EntityDieListener> getEntityDieListeners() {
        return entityDieListeners;
    }

    public List<EntityHealListener> getEntityHealListeners() {
        return entityHealListeners;
    }

    public List<EntityTakeDamageListener> getEntityTakeDamageListeners() {
        return entityTakeDamageListeners;
    }
}
