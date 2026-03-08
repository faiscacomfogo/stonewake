package dev.stonewake.assets;

import dev.stonewake.tiles.TileRegistry;

public class WorldAssetManager {
    public void loadAllAssets(TileAssetManager tileAssetManager, TileRegistry tileRegistry, TextureManager textureManager) {
        tileAssetManager.loadTileTextures(tileRegistry, textureManager);
    }
}
