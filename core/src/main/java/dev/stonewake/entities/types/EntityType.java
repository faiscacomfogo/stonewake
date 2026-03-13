package dev.stonewake.entities.types;

import com.badlogic.gdx.graphics.Texture;
import dev.stonewake.entities.Entity;
import dev.stonewake.utils.NamespacedKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class EntityType {
    protected final NamespacedKey entityTypeId;
    protected String[] entitySprites;
    public Texture[] cachedEntitySprites;
    private List<String> entityTags = new ArrayList<>();

    public EntityType(NamespacedKey entityTypeId) {
        this.entityTypeId = entityTypeId;
        cachedEntitySprites = new Texture[entitySprites.length];
        setDefaults();
    }

    public abstract void setDefaults();

    public NamespacedKey getEntityTypeId() {
        return entityTypeId;
    }

    public String[] getEntitySprites() {
        return entitySprites;
    }

    public Texture[] getCachedEntitySprites() {
        return cachedEntitySprites;
    }

    public void addEntityTag(String tag) {
        entityTags.add(tag.toUpperCase(Locale.ROOT));
    }

    public abstract Entity summonEntity();

    public boolean hasEntityTag(String tag) {
        return entityTags.contains(tag);
    }

    public boolean hasEntityTags(String[] tags) {
        for (String tag : tags) {
            if (!hasEntityTag(tag)) return false;
        }

        return true;
    }
}
