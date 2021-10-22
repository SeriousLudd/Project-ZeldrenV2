package com.mygdx.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.main.managers.ScreenEnum;
import com.mygdx.main.managers.ScreenManager;

public class Game extends com.badlogic.gdx.Game {
	/** V2.045
	 * By Serious_Ludd (Maxime MARTIN)
	 * On 12/09/2021
	 */


   /** Setup game window & world size **/
	public static final int WIDTH = 480;
	public static final int HEIGHT = 640;

	private static SpriteBatch batch;


	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen( ScreenEnum.MENU);
		batch = new SpriteBatch();

	}

	@Override
	public void render() {
		super.render();
	}

	public static SpriteBatch getBatch(){
		return batch;
	}

}
