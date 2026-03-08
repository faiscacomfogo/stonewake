package dev.stonewake.assets;

import dev.stonewake.Game;
import dev.stonewake.tiles.TileRegistry;

public class AssetManager {
    public void loadAllAssets(TileAssetManager tileAssetManager, TileRegistry tileRegistry, TextureManager textureManager) {
        tileAssetManager.loadTileTextures(tileRegistry, textureManager);
    }
}
