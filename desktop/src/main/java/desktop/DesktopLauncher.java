package desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import core.mechanics.PathPuzzleGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
        // Unify the path: Choose between AWT (standard Java) and LibGDX (high performance)
        // Satisfies OOP Requirement 1.2: Pluggable Rendering System.
        boolean useAwt = false;
        for (String s : arg) {
            if (s.equalsIgnoreCase("-awt")) {
                useAwt = true;
                break;
            }
        }

        if (useAwt) {
            java.awt.EventQueue.invokeLater(() -> {
                // Pass level path to match GameScreen behavior
                new core.windows.awt.AwtGameWindow("assets/levels/level_1.json").setVisible(true);
            });
        } else {
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setForegroundFPS(60);
            config.setTitle("P'J Cafe - Puzzle Game");
            config.setWindowedMode(800, 600);
            config.useVsync(true);
            new Lwjgl3Application(new PathPuzzleGame(), config);
        }
    }
}
