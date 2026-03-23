package core.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import core.mechanics.PathPuzzleGame;

public class SettingScreen implements Screen{
    private final AssetManager assetManager;
    private final PathPuzzleGame game;
    private Stage stage;
    private Table table;
    private Viewport viewport;
    private Skin skin;
    private Music music;
    private Sound clickSound;

    public SettingScreen(PathPuzzleGame game) {
        this.game = game;
        this.assetManager = game.assetManager;
    }
    

    //สร้างหน้าจอการตั้งค่า โดยมีปุ่ม Save และ Exit และ สร้างการเพิ่มลดเสียงเพลงและเอฟเฟคเสียง

    @Override
    public void show(){
        viewport = new FitViewport(1920, 1080); // Use ScreenViewport to prevent stretching
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage); // Let the stage handle input events (click events)
        setupUI();
        // Play background music if loaded
        if (assetManager.isLoaded("menu_bgm.mp3", Music.class)) {
            music = assetManager.get("menu_bgm.mp3", Music.class);
            music.setLooping(true);
            music.play();
        }
    }

    private void setupUI(){
        if (assetManager.isLoaded("click.mp3", Sound.class)) {
            clickSound = assetManager.get("click.mp3", Sound.class);
        }

        //create buttons
        TextButton Save = new TextButton("Save", skin);
        TextButton Exit = new TextButton("Exit", skin);


    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); // Clear the screen with black color
        
        viewport.apply();
        
        // Draw background if we have one
        stage.getBatch().begin();
        if (assetManager.isLoaded("background.png", Texture.class)) {
            stage.getBatch().draw(assetManager.get("background.png", Texture.class), 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        }
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        if (music != null) {
            music.stop();
        }
    }

    @Override
    public void dispose() {
        if (stage != null) stage.dispose();
        if (skin != null) skin.dispose();
    }
    

}
