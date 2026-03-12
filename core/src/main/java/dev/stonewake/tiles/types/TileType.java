package dev.stonewake.tiles;

import com.badlogic.gdx.graphics.Texture;
import dev.stonewake.tiles.listeners.TileBreakListener;
import dev.stonewake.tiles.listeners.TileChangeListener;
import dev.stonewake.tiles.listeners.TilePlaceListener;
import dev.stonewake.tiles.tiling.AutoTiler;
import dev.stonewake.tiles.tiling.BitMask;

import java.util.ArrayList;
import java.util.List;

public abstract class TileType {
    private short tileId;
    private Texture cachedTileTexture;
    protected AutoTiler autoTiler;
    protected String tileSprite;

    protected List<TileChangeListener> tileChangeListeners = new ArrayList<>();
    protected List<TilePlaceListener> tilePlaceListeners = new ArrayList<>();
    protected List<TileBreakListener> tileBreakListeners = new ArrayList<>();

    public TileType(short tileId) {
        this.tileId = tileId;

        setDefaults();
    }

    public abstract void setDefaults();

    public short getTileId() {
        return tileId;
    }

    public String getTileSprite() {
        return tileSprite;
    }

    public int getTileSpriteIndex(TileMap tileMap, Tile occupiedTile, int tileSize) {
        if (!occupiedTile.isTileSpriteIndexDirty()) {
            return occupiedTile.getCachedSpriteIndex();
        }
        int spriteIndex = autoTiler.getTileSpriteIndex(tileMap, occupiedTile, tileSize);
        occupiedTile.clearDirtyTileSpriteIndex();
        occupiedTile.cacheSpriteIndex(spriteIndex);

        return spriteIndex;
    }

    public void loadTileTexture(Texture texture) {
        cachedTileTexture = texture;
    }

    public Texture getCachedTileTexture() {
        return cachedTileTexture;
    }

    public List<TileChangeListener> getTileChangeListeners() {
        return tileChangeListeners;
    }

    public List<TilePlaceListener> getTilePlaceListeners() {
        return tilePlaceListeners;
    }

    public List<TileBreakListener> getTileBreakListeners() {
        return tileBreakListeners;
    }
}
