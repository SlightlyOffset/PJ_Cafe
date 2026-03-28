package core.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import core.mechanics.PathPuzzleGame;

public class StoryScreen implements Screen {
    private final AssetManager assetManager;
    private final PathPuzzleGame game;
    private Stage stage;
    private Viewport viewport;
    private Skin skin;
    private Music music;
    private Sound clickSound;

    public StoryScreen(PathPuzzleGame game) {
        this.game = game;
        this.assetManager = game.assetManager;
    }

    private void initSkin() {
        skin = new Skin();
        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));
        pixmap.dispose();
    }

    @Override
    public void show() {
        viewport = new FitViewport(1920, 1080);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        initSkin();
        setupUI();

        // Play background music if loaded
        if (assetManager.isLoaded("sounds/menu_bgm.mp3", Music.class)) {
            music = assetManager.get("sounds/menu_bgm.mp3", Music.class);
            music.setLooping(true);
            if (!music.isPlaying()) music.play();
        }

    }

    private void setupUI() {
        if (assetManager.isLoaded("sounds/click.mp3", Sound.class)) {
            clickSound = assetManager.get("sounds/click.mp3", Sound.class);
        }

        Texture backTex = assetManager.get("buttons/Arrow.png", Texture.class);
        Texture backPressTex = assetManager.get("buttons/Arrow_press.png", Texture.class);
        Texture nextTex = assetManager.get("buttons/Arrow_left.png", Texture.class);
        Texture nextPressTex = assetManager.get("buttons/Arrow_left_press.png", Texture.class);

        ImageButton backBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(backTex)), 
                                              new TextureRegionDrawable(new TextureRegion(backPressTex)));
        ImageButton nextBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextTex)), 
                                              new TextureRegionDrawable(new TextureRegion(nextPressTex)));

        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickSound != null) clickSound.play(game.sfxVolume);
                game.setScreen(new MenuScreen(game));
            }
        });

        nextBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickSound != null) clickSound.play(game.sfxVolume);
                game.setScreen(new LevelSelectionScreen(game));
            }
        });

        // Set button sizes and positions
        backBtn.setSize(143, 100);
        nextBtn.setSize(143, 100);
        backBtn.setPosition(50, 950);
        nextBtn.setPosition(1727, 50);

        stage.addActor(backBtn);
        stage.addActor(nextBtn);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        viewport.apply();

        stage.getBatch().begin();
        if (assetManager.isLoaded("Story/Story.mp4", Texture.class)) {
            stage.getBatch().draw(assetManager.get("Story/Story.mp4", Texture.class), 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        }
        stage.getBatch().end();

        // Draw background
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        if (stage != null) stage.dispose();
        if (skin != null) skin.dispose();
        
    }
}