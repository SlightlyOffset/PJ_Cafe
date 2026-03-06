package core;

import com.badlogic.gdx.assets.AssetManager;
import core.mechanics.PathPuzzleGame;
import core.windows.MenuScreen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MenuScreenTest {

    // Manual Stub for AssetManager
    static class StubAssetManager extends AssetManager {
        // No-op or provide dummy behavior as needed
    }

    // Manual Stub for PathPuzzleGame
    static class TestGame extends PathPuzzleGame {
        @Override
        public void create() {
            // No-op
        }
    }

    @Test
    public void testMenuScreenInitialization() {
        // Setup manual stubs
        PathPuzzleGame testGame = new TestGame();
        testGame.assetManager = new StubAssetManager();

        // Test MenuScreen creation
        MenuScreen menuScreen = new MenuScreen(testGame);
        assertNotNull(menuScreen, "MenuScreen should be initialized successfully");
    }
}
