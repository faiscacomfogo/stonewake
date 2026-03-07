package dev.stonewake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.stonewake.assets.AssetManager;
import dev.stonewake.assets.TextureManager;
import dev.stonewake.assets.TileAssetManager;
import dev.stonewake.rendering.Camera;
import dev.stonewake.rendering.TileMapRenderer;
import dev.stonewake.tiles.TileRegistry;
import dev.stonewake.tiles.TileMap;
import dev.stonewake.world.GameWorld;
import dev.stonewake.world.WorldConfig;

public class Game {
    private SpriteBatch spriteBatch;
    private float deltaTime = 0f;
    private float fixedDeltaTime = 0f;
    private float alpha = 0f;

    private Camera camera;
    private TileMapRenderer tileMapRenderer;
    private AssetManager assetManager;
    private TextureManager textureManager;
    private TileAssetManager tileAssetManager;

    private GameWorld world;

    public void start() {
        spriteBatch = new SpriteBatch();
        tileMapRenderer = new TileMapRenderer();
        assetManager = new AssetManager();
        textureManager = new TextureManager();
        TileMap tileMap = new TileMap(WorldConfig.NUM_LAYERS, WorldConfig.WORLD_WIDTH, WorldConfig.WORLD_HEIGHT, WorldConfig.TILE_SIZE, WorldConfig.TILE_TYPES);
        world = new GameWorld(tileMap);
        tileAssetManager = new TileAssetManager(tileMap, textureManager);
        camera = new Camera(tileMap, 0.1f, 0.5f);

        assetManager.loadAllAssets(this);

        tileMap.fillTiles(0, 0, WorldConfig.WORLD_WIDTH - 1, 0, 15, 0);
        tileMap.clearTile(0, 5, 5);
        tileMap.clearTile(0, 255, 5);

        camera.update();
    }

    public void input() {

        float cameraSpeed = 3f;

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.changeZoom(-0.1f);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.changeZoom(0.1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.changePosition(-cameraSpeed, 0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.changePosition(cameraSpeed, 0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.changePosition(0f, cameraSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.changePosition(0f, -cameraSpeed);
        }

        camera.update();
    }

    public void update() {
        camera.update();
    }

    public void render() {
        camera.renderCamera(spriteBatch);
        tileMapRenderer.renderOnce(this, world.getTileMap(), camera.getViewPortMinXTiles(), camera.getViewPortMaxXTiles(), camera.getViewPortMinYTiles(), camera.getViewPortMaxYTiles());
    }

    public void dispose() {
        textureManager.dispose();
    }

    public void resize(int width, int height) {
        camera.resizeCamera(width, height);
    }

    public void updateDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    public void updateAlpha(float alpha) {
        this.alpha = alpha;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public TextureManager getTextureManager() {
        return textureManager;
    }

    public TileAssetManager getTileAssetManager() {
        return tileAssetManager;
    }

    public TileRegistry getTileRegistry() {
        return world.getTileMap().getTileRegistry();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public Game(float fixedDeltaTime) {
        this.fixedDeltaTime = fixedDeltaTime;
    }
}
