package com.mygdx.main.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.main.Game;
import com.mygdx.main.managers.ScreenEnum;
import com.mygdx.main.managers.ScreenManager;

public class MenuScreen extends AbstractScreen {

    // ATTRIBUTES
    private Texture background;
    private TextButton PlayButton;
    private TextButton ExitButton;


    // CONSTRUCTOR
    public MenuScreen() {
        super();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        PlayButton = new TextButton("JOUER", skin);
        ExitButton = new TextButton("QUITTER", skin);
        PlayButton.setWidth(100);
        ExitButton.setWidth(100);
        PlayButton.setPosition(Game.WIDTH / 2 - PlayButton.getWidth() / 2, Game.HEIGHT / 2 + 50);
        ExitButton.setPosition(Game.WIDTH / 2 - ExitButton.getWidth() / 2, Game.HEIGHT / 2);
        background = new Texture("Menu/splash.jpg");
    }

    // METHODS
    @Override
    public void buildStage() {

        /** Background **/
        Image bg = new Image(background);
        addActor(bg);


        /** Add buttons **/
        addActor(PlayButton);
        addActor(ExitButton);

        /** Add buttons functionnality **/
        PlayButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ScreenManager.getInstance().showScreen(ScreenEnum.GAME, 0);
            }
        });
        ExitButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
    }
}
