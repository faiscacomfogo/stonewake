package dev.stonewake.tiles.types;

import com.badlogic.gdx.graphics.Texture;
import dev.stonewake.tiles.pieces.Tile;
import dev.stonewake.tiles.TileMap;
import dev.stonewake.tiles.tiling.AutoTiler;
import dev.stonewake.utils.NamespacedKey;

public abstract class TileType {
    private NamespacedKey tileId;
    private Texture cachedTileTexture;
    protected AutoTiler autoTiler;
    protected String tileSprite;

    public TileType(NamespacedKey tileId) {
        this.tileId = tileId;

        setDefaults();
    }

    public abstract void setDefaults();

    public NamespacedKey getTileId() {
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
}
