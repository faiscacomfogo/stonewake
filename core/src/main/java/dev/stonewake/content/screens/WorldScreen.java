package dev.stonewake.content.screens;

import dev.stonewake.Game;
import dev.stonewake.assets.TileAssetManager;
import dev.stonewake.assets.WorldAssetManager;
import dev.stonewake.content.tiles.GrassTile;
import dev.stonewake.rendering.TileMapRenderer;
import dev.stonewake.screens.GameScreen;
import dev.stonewake.tiles.TileChunk;
import dev.stonewake.tiles.TileMap;

public class WorldScreen extends GameScreen {
    private TileMap tileMap;
    private TileMapRenderer tileMapRenderer;
    private TileAssetManager tileAssetManager;
    private WorldAssetManager worldAssetManager;

    public WorldScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        tileMap = new TileMap(3, 32, 16, 16, 16, 8, new Class[]{ GrassTile.class });
        tileMapRenderer = new TileMapRenderer();
        tileAssetManager = new TileAssetManager(tileMap, game.getTextureManager());
        worldAssetManager = new WorldAssetManager();

        worldAssetManager.loadAllAssets(tileAssetManager, tileMap.getTileRegistry(), game.getTextureManager());

        for (int chunkX = 0; chunkX < 5; chunkX++) {
            for (int chunkY = 0; chunkY < 5; chunkY++) {
                TileChunk chunk = tileMap.getChunk(chunkX, chunkY);
                for (int x = 0; x < tileMap.getTileMapChunkWidth(); x++) {
                    for (int y = 0; y < tileMap.getTileMapChunkHeight(); y++) {
                        chunk.setTile(tileMap, 0, x, y, 0);
                    }
                }
            }
        }
    }

    @Override
    public void render(float v) {
        tileMapRenderer.renderOnce(tileAssetManager, game.getTextureManager(), game.getSpriteBatch(), tileMap, 0, 0, 5, 1);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void input() {

    }

    @Override
    public void update() {

    }

    @Override
    public boolean isTransparent() {
        return false;
    }
}
