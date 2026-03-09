package dev.stonewake;

public class Game extends GameController {
    public Game(float fixedDeltaTime) {
        super(fixedDeltaTime);
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
}