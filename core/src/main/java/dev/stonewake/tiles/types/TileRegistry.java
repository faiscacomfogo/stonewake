package dev.stonewake.tiles.types;

import dev.stonewake.utils.NamespacedKey;

import java.util.Collection;
import java.util.HashMap;

public class TileRegistry {
    private HashMap<NamespacedKey, TileType> tileTypes;

    public TileRegistry() {
        this.tileTypes = new HashMap<>();
    }

    public void registerTileType(TileType tileType) {
        tileTypes.put(tileType.getTileId(), tileType);
    }

    public TileType getRegisteredTileType(NamespacedKey tileId) {
        return tileTypes.get(tileId);
    }

    public Collection<TileType> getRegisteredTileTypes() {
        return tileTypes.values();
    }
}
