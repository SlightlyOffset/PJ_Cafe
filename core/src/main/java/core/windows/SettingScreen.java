package core.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import core.mechanics.PathPuzzleGame;


public class SettingScreen implements Screen {
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

    @Override
    public void show() {
        viewport = new FitViewport(1920, 1080); // Use ScreenViewport to prevent stretching
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage); // Let the stage handle input events (click events)
        
        //change to set positon
        //table = new Table();
        //table.setFillParent(true); // Make the table the size of the viewport
        //table.right();  // Align the table to the right side per spec
        //stage.addActor(table);

        // Add Buttons to the stage
        setupUI();

        // Play background music if loaded
        if (assetManager.isLoaded("menu_bgm.mp3", Music.class)) {
            music = assetManager.get("menu_bgm.mp3", Music.class);
            music.setLooping(true);
            music.play();
        }
    }

    private void setupUI() {
        // Load click sound
        if (assetManager.isLoaded("click.mp3", Sound.class)) {
            clickSound = assetManager.get("click.mp3", Sound.class);
        }

        // Create Buttons
        Texture Save = assetManager.get("setting/Save_bttn.png", Texture.class);
        Texture Savepress = assetManager.get("setting/Savepress_bttn.png", Texture.class);
        Texture Exit = assetManager.get("setting/Exit_bttn.png", Texture.class);
        Texture Exitpress = assetManager.get("setting/Exitpress_bttn.png", Texture.class);

        // Create ImageButton styles
        ImageButton.ImageButtonStyle SaveStyle = new ImageButton.ImageButtonStyle();
        SaveStyle.up = new TextureRegionDrawable(new TextureRegion(Save));
        SaveStyle.over = new TextureRegionDrawable(new TextureRegion(Savepress));

        ImageButton.ImageButtonStyle ExitStyle = new ImageButton.ImageButtonStyle();
        ExitStyle.up = new TextureRegionDrawable(new TextureRegion(Exit));
        ExitStyle.over = new TextureRegionDrawable(new TextureRegion(Exitpress));


        ImageButton SaveButton = new ImageButton(SaveStyle);
        ImageButton ExitButton = new ImageButton(ExitStyle);

        //set position on button
        ExitButton.setPosition(1070,100);
        SaveButton.setPosition(350,100);

        //set size on button
        SaveButton.setSize(500, 238);
        ExitButton.setSize(500, 238);


        //add button to stage
        stage.addActor(SaveButton);
        stage.addActor(ExitButton);

        // Add listeners for interaction
        SaveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickSound != null) clickSound.play();
                game.setScreen(new MenuScreen(game));
            }
        });

        ExitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickSound != null) clickSound.play();
                game.setScreen(new MenuScreen(game));
            }
        });

        // change to set position on button
        //table.add(startButton).fillX().uniformX().pad(10).row();
        //table.add(optionsButton).fillX().uniformX().pad(10).row();
        //table.add(exitButton).fillX().uniformX().pad(10);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); // Clear the screen with black color
        
        viewport.apply();
        
        // Draw background if we have one
        stage.getBatch().begin();
        // load background texture for settings screen
        if (assetManager.isLoaded("setting/BackgroundSetting.png", Texture.class)) {
            stage.getBatch().draw(assetManager.get("setting/BackgroundSetting.png", Texture.class), 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
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
