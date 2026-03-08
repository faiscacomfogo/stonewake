package dev.stonewake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import dev.stonewake.content.screens.LoadingScreen;
import dev.stonewake.content.screens.WorldScreen;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Game game;
    private float accumulator = 0f;
    private final float FIXED_DELTA_TIME = 1f/60f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        game = new Game(FIXED_DELTA_TIME);

        game.setInitialGameScreen(new LoadingScreen(game));

        game.start();
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        game.updateDeltaTime(deltaTime);
        accumulator += deltaTime;

        game.input();
        while (accumulator >= FIXED_DELTA_TIME) {
            game.update();
            accumulator -= FIXED_DELTA_TIME;
        }
        game.updateAlpha(accumulator / FIXED_DELTA_TIME);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getSpriteBatch().begin();
        game.render();
        game.getSpriteBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        game.dispose();
    }
}
