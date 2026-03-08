package dev.stonewake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.stonewake.assets.TextureManager;
import dev.stonewake.screens.GameScreen;
import dev.stonewake.screens.ScreenManager;
import dev.stonewake.screens.ScreenRenderer;

public class Game {
    private float fixedDeltaTime;
    private float deltaTime;
    private float alpha;

    private boolean hasSetInitialGameScreen;
    private ScreenManager screenManager;
    private ScreenRenderer screenRenderer;
    private GameScreen currentGameScreen;
    private SpriteBatch spriteBatch;

    private TextureManager textureManager;

    public Game(float fixedDeltaTime) {
        this.fixedDeltaTime = fixedDeltaTime;

        screenManager = new ScreenManager();
        screenRenderer = new ScreenRenderer(screenManager);
        spriteBatch = new SpriteBatch();

        textureManager = new TextureManager();
    }

    public void start() {

    }

    public void input() {
        currentGameScreen.input();
    }

    public void update() {
        currentGameScreen.update();
    }

    public void render() {
        screenRenderer.render(deltaTime);
    }

    public void dispose() {

    }

    public void resize(int width, int height) {
        screenRenderer.resize(width, height);
    }

    public void setInitialGameScreen(GameScreen screen) {
        if (hasSetInitialGameScreen)
            throw new RuntimeException("Initial game screen can be set just once.");

        hasSetInitialGameScreen = true;
        screenManager.set(this, screen);
    }

    public float getFixedDeltaTime() {
        return fixedDeltaTime;
    }

    public void updateDeltaTime(float dt) {
        deltaTime = dt;
    }

    public void updateAlpha(float alpha) {
        this.alpha = alpha;
    }

    public GameScreen getCurrentGameScreen() {
        return currentGameScreen;
    }

    public void setCurrentGameScreen(GameScreen currentGameScreen) {
        this.currentGameScreen = currentGameScreen;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public TextureManager getTextureManager() {
        return textureManager;
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }
}