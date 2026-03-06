package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Main menu screen displayed when the game launches.
 * Contains Start, Options, and Exit buttons aligned to the right side,
 * with the game logo at the top of the button panel.
 */
public class MenuScreen extends ScreenAdapter {

    private final PathPuzzleGame game;

    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;

    private Texture backgroundTexture;
    private Texture logoTexture;
    private Music bgMusic;
    private Sound clickSfx;

    public MenuScreen(PathPuzzleGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        loadAssets();
        skin = buildSkin();
        buildUI();

        if (bgMusic != null) {
            bgMusic.setLooping(true);
            bgMusic.play();
        }
    }

    /** Load optional asset files; missing files are silently skipped. */
    private void loadAssets() {
        if (Gdx.files.internal("menu_bg.png").exists()) {
            backgroundTexture = new Texture(Gdx.files.internal("menu_bg.png"));
        }
        if (Gdx.files.internal("logo.png").exists()) {
            logoTexture = new Texture(Gdx.files.internal("logo.png"));
        }
        if (Gdx.files.internal("menu_music.wav").exists()) {
            bgMusic = Gdx.audio.newMusic(Gdx.files.internal("menu_music.wav"));
        }
        if (Gdx.files.internal("click.wav").exists()) {
            clickSfx = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
        }
    }

    /** Build a programmatic Skin using BitmapFont and Pixmap-generated textures. */
    private Skin buildSkin() {
        Skin s = new Skin();

        BitmapFont font = new BitmapFont();
        font.getData().setScale(1.5f);
        s.add("default-font", font);

        Pixmap pixUp = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixUp.setColor(0.2f, 0.2f, 0.2f, 0.85f);
        pixUp.fill();
        s.add("button-up", new Texture(pixUp));
        pixUp.dispose();

        Pixmap pixDown = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixDown.setColor(0.4f, 0.4f, 0.4f, 0.85f);
        pixDown.fill();
        s.add("button-down", new Texture(pixDown));
        pixDown.dispose();

        TextButton.TextButtonStyle btnStyle = new TextButton.TextButtonStyle();
        btnStyle.font = s.getFont("default-font");
        btnStyle.up   = new TextureRegionDrawable(new TextureRegion(s.get("button-up",   Texture.class)));
        btnStyle.down = new TextureRegionDrawable(new TextureRegion(s.get("button-down", Texture.class)));
        btnStyle.over = new TextureRegionDrawable(new TextureRegion(s.get("button-down", Texture.class)));
        btnStyle.fontColor = Color.WHITE;
        s.add("default", btnStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font      = s.getFont("default-font");
        labelStyle.fontColor = Color.WHITE;
        s.add("default", labelStyle);

        return s;
    }

    /** Assemble the Scene2D UI: a right-aligned panel with logo + buttons. */
    private void buildUI() {
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Table panel = new Table();

        // Logo at the top of the panel
        if (logoTexture != null) {
            panel.add(new Image(logoTexture)).pad(20).row();
        } else {
            panel.add(new Label("P'J Cafe", skin)).pad(20).row();
        }

        TextButton startBtn   = new TextButton("Start",   skin);
        TextButton optionsBtn = new TextButton("Options", skin);
        TextButton exitBtn    = new TextButton("Exit",    skin);

        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playClickSfx();
                game.setScreen(new GameScreen());
            }
        });

        optionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playClickSfx();
                // Placeholder: options menu not yet implemented
            }
        });

        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playClickSfx();
                Gdx.app.exit();
            }
        });

        panel.add(startBtn  ).width(200).pad(10).row();
        panel.add(optionsBtn).width(200).pad(10).row();
        panel.add(exitBtn   ).width(200).pad(10).row();

        root.right().add(panel).padRight(60);
    }

    private void playClickSfx() {
        if (clickSfx != null) {
            clickSfx.play();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (backgroundTexture != null) {
            batch.begin();
            batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        if (bgMusic != null) {
            bgMusic.stop();
        }
    }

    @Override
    public void dispose() {
        if (stage             != null) stage.dispose();
        if (batch             != null) batch.dispose();
        if (skin              != null) skin.dispose();
        if (backgroundTexture != null) backgroundTexture.dispose();
        if (logoTexture       != null) logoTexture.dispose();
        if (bgMusic           != null) bgMusic.dispose();
        if (clickSfx          != null) clickSfx.dispose();
    }
}
