package com.mygdx.main.managers;

import com.badlogic.gdx.Screen;
import com.mygdx.main.Game;
import com.mygdx.main.gamestates.AbstractScreen;

public class ScreenManager {
    /** Singleton model so only one ScreenManager is launched **/
    // SINGLETON SETUP

    private static ScreenManager instance;
    private Game game;
    private ScreenManager(){
        super();
    }

    public static ScreenManager getInstance(){
        if (instance == null){
            instance = new ScreenManager();
        }

        return instance;
    }

    /**This class allow us to easily switch from screen to screen **/

    // ATTRIBUTES
    // CONSTRUCTOR
    // METHODS
    public void initialize (Game game){
        this.game = game;
    }

    public void showScreen(ScreenEnum screenEnum, Object... params){
        Screen currentScreen = game.getScreen();

        AbstractScreen newScreen = screenEnum.getScreen(params);
        newScreen.buildStage();
        game.setScreen(newScreen);

        if (currentScreen != null){
            currentScreen.dispose();
        }
    }
}
