package dev.stonewake.entities;

import com.badlogic.gdx.physics.box2d.Body;
import dev.stonewake.utils.NamespacedKey;
import dev.stonewake.world.GameWorld;

public abstract class Entity {
    private static int nextUniqueId = 0;

    private NamespacedKey entityId;
    private int uniqueEntityId;
    protected Body entityBody;
    protected float entityTime;

    public Entity(GameWorld world, NamespacedKey entityId, float entityX, float entityY) {
        setDefaults(world);

        entityBody.setTransform(entityX, entityY, 0);

        this.entityId = entityId;
        this.uniqueEntityId = nextUniqueId++;
    }

    public abstract void setDefaults(GameWorld world);

    public abstract void start(GameWorld world);

    public abstract void update(GameWorld world);

    public abstract void dispose();

    public void updateEntityTime(float deltaTime) {
        entityTime += deltaTime;
    }

    public void applyLinearVelocity(float vx, float vy) {
        entityBody.setLinearVelocity(vx, vy);
    }

    public void applyAngularVelocity(float omega) {
        entityBody.setAngularVelocity(omega);
    }

    public float getEntityX() {
        return entityBody.getPosition().x;
    }

    public float getEntityY() {
        return entityBody.getPosition().y;
    }

    public int getUniqueEntityId() {
        return uniqueEntityId;
    }

    public NamespacedKey getEntityId() {
        return entityId;
    }
}
