package dev.stonewake.assets;

import com.badlogic.gdx.graphics.Texture;
import dev.stonewake.Game;
import dev.stonewake.tiles.Tile;
import dev.stonewake.tiles.TileMap;
import dev.stonewake.tiles.TileRegistry;
import dev.stonewake.tiles.TileType;

public class TileAssetManager {
    private TextureManager textureManager;

    public TileAssetManager(TileMap tileMap, TextureManager textureManager) {
        this.textureManager = textureManager;
    }

    public void loadTileTextures(TileRegistry tileRegistry, TextureManager textureManager) {
        String[] tileTextures = new String[tileRegistry.getTileTypes().length];

        for (TileType tileType : tileRegistry.getTileTypes()) {
            tileTextures[tileType.getTileId()] = tileType.getTileSprite();
        }

        textureManager.loadTextures(tileTextures);

        for (TileType tileType : tileRegistry.getTileTypes()) {
            tileType.loadTileTexture(textureManager.getTexture(tileType.getTileSprite()));
        }
    }
}
