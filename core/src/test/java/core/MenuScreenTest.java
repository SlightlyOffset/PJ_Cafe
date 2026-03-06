package core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuScreenTest {

    @Test
    public void testMenuScreenInstantiation() {
        PathPuzzleGame game = new PathPuzzleGame();
        MenuScreen screen = new MenuScreen(game);
        assertNotNull(screen);
    }

    @Test
    public void testPathPuzzleGameScreenIsNullBeforeCreate() {
        PathPuzzleGame game = new PathPuzzleGame();
        // Before create() is called (i.e. before libGDX initialises), getScreen() should be null
        assertNull(game.getScreen());
    }
}
