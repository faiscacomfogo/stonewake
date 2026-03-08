package dev.stonewake.content.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import dev.stonewake.Game;
import dev.stonewake.screens.GameScreen;

public class LoadingScreen extends GameScreen {
    Texture splashLogo;

    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.getScreenManager().set(game, new WorldScreen(game));
            System.out.println(game.getScreenManager().getScreens().size());
        }
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public void show() {
        splashLogo = new Texture("libgdx.png");
    }

    @Override
    public void render(float v) {
        game.getSpriteBatch().draw(splashLogo, 0, 0);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        splashLogo.dispose();
    }
}
