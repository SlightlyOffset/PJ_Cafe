package core.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class LevelLoader {
    private static final Json json = new Json();

    public static Grid loadLevel(String path) {
        FileHandle file;
        if (Gdx.files != null) {
            file = Gdx.files.internal(path);
        } else {
            // For unit testing outside of LibGDX context
            file = new FileHandle(path);
        }
        return json.fromJson(Grid.class, file);
    }

    public static String toJson(Grid grid) {
        return json.toJson(grid);
    }
}
