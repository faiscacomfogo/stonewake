package dev.stonewake.tiles;

public class Tile {
    private final int tileLayer;
    private final int tileX;
    private final int tileY;
    private final int tileChunkX;
    private final int tileChunkY;
    private final int parentChunkX;
    private final int parentChunkY;
    private boolean dirtySpriteIndex;
    private int cachedSpriteIndex = 0;
    public TileType tileType;

    public Tile(int tileLayer, int tileX, int tileY, int tileChunkX, int tileChunkY, int parentChunkX, int parentChunkY) {
        this.tileLayer = tileLayer;
        this.tileX = tileX;
        this.tileY = tileY;
        this.tileChunkX = tileChunkX;
        this.tileChunkY = tileChunkY;
        this.parentChunkX = parentChunkX;
        this.parentChunkY = parentChunkY;
    }

    public int getTileLayer() {
        return tileLayer;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public int getTileChunkX() {
        return tileChunkX;
    }

    public int getTileChunkY() {
        return tileChunkY;
    }

    public int getParentChunkX() {
        return parentChunkX;
    }

    public int getParentChunkY() {
        return parentChunkY;
    }

    public boolean isTileOccupied() {
        return tileType != null;
    }

    public boolean isTileAir() {
        return tileType == null;
    }

    public boolean isTileDirty() {
        return dirtySpriteIndex;
    }

    public void markTileDirty() {
        dirtySpriteIndex = true;
    }

    public void clearDirtyTile() {
        dirtySpriteIndex = false;
    }

    public int getCachedSpriteIndex() {
        return cachedSpriteIndex;
    }

    public void cacheSpriteIndex(int spriteIndex) {
        cachedSpriteIndex = spriteIndex;
    }
}
