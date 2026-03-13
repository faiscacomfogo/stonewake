package dev.stonewake.entities.types;

import dev.stonewake.entities.listeners.EntityDieListener;
import dev.stonewake.entities.listeners.EntityPostHealListener;
import dev.stonewake.entities.listeners.EntityPostDamageListener;
import dev.stonewake.entities.listeners.EntityPreDamageListener;

import java.util.ArrayList;
import java.util.List;

public abstract class LivingEntityType extends EntityType {
    protected List<EntityDieListener> entityDieListeners = new ArrayList<>();
    protected List<EntityPostHealListener> entityPostHealListeners = new ArrayList<>();
    protected List<EntityPreDamageListener> entityPreDamageListeners = new ArrayList<>();
    protected List<EntityPostDamageListener> entityPostDamageListeners = new ArrayList<>();
    protected int entityMaxHealth;
    protected int entityDefense;
    protected short entityMaxInvulnerabilityTime;

    public LivingEntityType(short entityTypeId) {
        super(entityTypeId);
    }

    public List<EntityDieListener> getEntityDieListeners() {
        return entityDieListeners;
    }

    public List<EntityPostHealListener> getEntityHealListeners() {
        return entityPostHealListeners;
    }
    
    public List<EntityPreDamageListener> getEntityPreDamageListeners() {
        return entityPreDamageListeners;
    }

    public List<EntityPostDamageListener> getEntityPostDamageListeners() {
        return entityPostDamageListeners;
    }

    public int getEntityMaxHealth() {
        return entityMaxHealth;
    }

    public int getEntityDefense() {
        return entityDefense;
    }

    public short getEntityMaxInvulnerabilityTime() {
        return entityMaxInvulnerabilityTime;
    }
}
