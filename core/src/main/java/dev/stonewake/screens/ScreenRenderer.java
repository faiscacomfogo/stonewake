package dev.stonewake.screens;

import java.util.Stack;

public class ScreenRenderer {
    ScreenManager screenManager;

    public ScreenRenderer(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public void render(float dt) {

        int startIndex = 0;
        Stack<GameScreen> screens = screenManager.getScreens();

        for (int i = screens.size() - 1; i >= 0; i--) {
            if (!screens.get(i).isTransparent()) {
                startIndex = i;
                break;
            }
        }

        for (int i = startIndex; i < screens.size(); i++) {
            screens.get(i).render(dt);
        }
    }

    public void resize(int width, int height) {
        Stack<GameScreen> screens = screenManager.getScreens();

        for (GameScreen screen : screens) {
            screen.resize(width, height);
        }
    }
}
