package dev.stonewake.tiles;

import com.badlogic.gdx.graphics.Texture;
import dev.stonewake.assets.TileAssetManager;
import dev.stonewake.tiles.listeners.TileChangeListener;
import dev.stonewake.tiles.tiling.AutoTiler;
import dev.stonewake.tiles.tiling.BitMask;

import java.util.List;

public abstract class TileType {
    private int tileId;
    private Texture tileTexture;
    protected AutoTiler autoTiler;
    protected String tileSprite;

    private List<TileChangeListener> tileChangeListeners;

    public TileType(int tileId) {
        this.tileId = tileId;

        setDefaults();
    }

    public abstract void setDefaults();

    public int getTileId() {
        return tileId;
    }

    public String getTileSprite() {
        return tileSprite;
    }

    public int getTileSpriteIndex(BitMask bitMask, Tile occupiedTile, int tileSize) {
        if (!occupiedTile.isTileDirty()) {
            return occupiedTile.getCachedSpriteIndex();
        }
        int spriteIndex = autoTiler.getTileSpriteIndex(bitMask, occupiedTile, tileSize);
        occupiedTile.clearDirtyTile();
        occupiedTile.cacheSpriteIndex(spriteIndex);

        return spriteIndex;
    }

    public void loadTileTexture(Texture texture) {
        tileTexture = texture;
    }

    public Texture getTileTexture() {
        return tileTexture;
    }

    protected void addTileChangeListener(TileChangeListener tileChangeListener) {
        tileChangeListeners.add(tileChangeListener);
    }

    public List<TileChangeListener> getTileChangeListeners() {
        return tileChangeListeners;
    }
}
