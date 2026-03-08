package dev.stonewake.content.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import dev.stonewake.Game;
import dev.stonewake.assets.TileAssetManager;
import dev.stonewake.assets.WorldAssetManager;
import dev.stonewake.content.tiles.CobblestoneTile;
import dev.stonewake.content.tiles.GrassTile;
import dev.stonewake.rendering.Camera;
import dev.stonewake.rendering.TileMapRenderer;
import dev.stonewake.screens.GameScreen;
import dev.stonewake.tiles.TileChunk;
import dev.stonewake.tiles.TileMap;

public class WorldScreen extends GameScreen {
    private TileMap tileMap;
    private TileMapRenderer tileMapRenderer;
    private TileAssetManager tileAssetManager;
    private WorldAssetManager worldAssetManager;

    private static final float CAMERA_SPEED = 3f;
    private Camera camera;

    public WorldScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        tileMap = new TileMap(3, 32, 16, 16, 16, 8, new Class[]{ GrassTile.class, CobblestoneTile.class});
        tileMapRenderer = new TileMapRenderer();
        tileAssetManager = new TileAssetManager(tileMap, game.getTextureManager());
        worldAssetManager = new WorldAssetManager();

        worldAssetManager.loadAllAssets(tileAssetManager, tileMap.getTileRegistry(), game.getTextureManager());

        for (int chunkX = 0; chunkX < 5; chunkX++) {
            for (int chunkY = 0; chunkY < 5; chunkY++) {
                TileChunk chunk = tileMap.getChunk(chunkX, chunkY);
                for (int x = 0; x < tileMap.getTileMapChunkWidth(); x++) {
                    if (x == 0 || x >= 15) continue;

                    for (int y = 0; y < tileMap.getTileMapChunkHeight(); y++) {
                        if (y == 0 || y >= 15) continue;

                        int id = (x % 2 == 0 && y % 2 == 0) ? 0 : 1;
                        chunk.setTile(tileMap, 0, x, y, id);
                    }
                }
            }
        }

        camera = new Camera(tileMap, 0.1f, 1.5f);
        camera.update();
    }

    @Override
    public void render(float v) {
        camera.renderCamera(game.getSpriteBatch());

        tileMapRenderer.renderOnce(tileAssetManager, game.getTextureManager(), game.getSpriteBatch(), tileMap, 0, 0, 5, 5);
    }

    @Override
    public void resize(int i, int i1) {
        camera.resizeCamera(i, i1);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.changePosition(-CAMERA_SPEED, 0f);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.changePosition(CAMERA_SPEED, 0f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.changePosition(0f, CAMERA_SPEED);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.changePosition(0f, -CAMERA_SPEED);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.changeZoom(-0.1f);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.changeZoom(0.1f);
        }

        camera.update();
    }

    @Override
    public void update() {
        camera.update();
    }

    @Override
    public boolean isTransparent() {
        return false;
    }
}
