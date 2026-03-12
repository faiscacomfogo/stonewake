package dev.stonewake.entities;

import com.badlogic.gdx.graphics.Texture;
import dev.stonewake.entities.events.EntityDisposeEvent;
import dev.stonewake.entities.listeners.EntityDisposeListener;
import dev.stonewake.entities.listeners.EntitySpawnListener;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityType {
    public final int entityId;
    public final String[] entitySprites;
    public final Class<? extends Entity> entityClass;
    public Texture[] cachedEntitySprites;

    protected List<EntitySpawnListener> entitySpawnListeners = new ArrayList<>();
    protected List<EntityDisposeListener> entityDisposeListeners = new ArrayList<>();

    public EntityType(int entityId, String[] entitySprites, Class<? extends Entity> entityClass) {
        this.entityId = entityId;
        this.entitySprites = entitySprites;
        this.entityClass = entityClass;

        cachedEntitySprites = new Texture[entitySprites.length];
        setDefaults();
    }

    public abstract void setDefaults();

    public List<EntitySpawnListener> getEntitySpawnListeners() {
        return entitySpawnListeners;
    }

    public List<EntityDisposeListener> getEntityDisposeListeners() {
        return entityDisposeListeners;
    }
}
