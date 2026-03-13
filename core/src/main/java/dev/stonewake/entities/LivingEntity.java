package dev.stonewake.entities;

import dev.stonewake.entities.types.EntityRegistry;
import dev.stonewake.entities.types.LivingEntityType;
import dev.stonewake.world.GameWorld;

public abstract class LivingEntity extends Entity {
    protected int entityHealth;
    protected short entityInvulnerabilityTime;

    public LivingEntity(GameWorld world, short entityTypeId, float entityX, float entityY) {
        super(world, entityTypeId, entityX, entityY);
    }

    public int getEntityHealth() {
        return entityHealth;
    }

    public short getEntityInvulnerabilityTime() {
        return entityInvulnerabilityTime;
    }

    public void damage(EntityRegistry entityRegistry, int amount) {
        LivingEntityType entityType = (LivingEntityType)entityRegistry.getRegisteredEntityType(this.getEntityId());

        if (entityInvulnerabilityTime > 0) return;

        amount -= entityType.getEntityDefense();
        amount = Math.max(amount, 0);

        entityHealth -= amount;
        entityHealth = Math.max(0, entityHealth);

        entityInvulnerabilityTime = entityType.getEntityMaxInvulnerabilityTime();

        if (entityHealth < 1) {
            die();
        }
    }

    public void heal(EntityRegistry entityRegistry, int amount) {
        LivingEntityType entityType = (LivingEntityType)entityRegistry.getRegisteredEntityType(this.getEntityId());

        entityHealth += amount;
        entityHealth = Math.min(entityType.getEntityMaxHealth(), entityHealth);
    }

    public void die() {
        dispose();
    }

    public boolean isAlive() {
        return entityHealth > 0;
    }
}
