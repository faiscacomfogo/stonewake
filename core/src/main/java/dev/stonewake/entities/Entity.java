package dev.stonewake.entities;

import dev.stonewake.GameController;
import dev.stonewake.entities.listeners.EntitySpawnListener;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    private static int nextUniqueId = 0;

    private int uniqueEntityId;
    protected float entityX;
    protected float entityY;

    private List<EntitySpawnListener> entitySpawnListeners;

    public Entity(float entityX, float entityY) {
        entitySpawnListeners = new ArrayList<>();
        setDefaults();

        this.entityX = entityX;
        this.entityY = entityY;

        this.uniqueEntityId = nextUniqueId++;
    }

    public abstract void setDefaults();

    public abstract void start();

    public abstract void update(GameController game);

    public abstract void dispose();

    public float getEntityX() {
        return entityX;
    }

    public float getEntityY() {
        return entityY;
    }

    public int getUniqueEntityId() {
        return uniqueEntityId;
    }

    public List<EntitySpawnListener> getEntitySpawnListeners() {
        return entitySpawnListeners;
    }

    public abstract int getEntityTypeId();
}
