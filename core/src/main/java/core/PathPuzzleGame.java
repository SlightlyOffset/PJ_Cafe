package core;

import com.badlogic.gdx.Game;

public class PathPuzzleGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if (getScreen() != null) {
            getScreen().dispose();
        }
    }
}
