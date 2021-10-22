package com.mygdx.main.managers;

import com.mygdx.main.gamestates.*;

public enum ScreenEnum {

    /** Enumeration to easily swap from one screen to another **/

    MENU{
        public AbstractScreen getScreen(Object... params){
            return new MenuScreen();
        }
    },

    GAME{
        public AbstractScreen getScreen(Object... params){
            return new GameScreen((Integer) params[0]);
        }
    };

    public abstract AbstractScreen getScreen(Object...params);
}
