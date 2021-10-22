package com.mygdx.main.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.main.Game;
import com.mygdx.main.actors.ships.Player;

public class PlayerHUD  {

    // ATTRIBUTES
    public Stage stage;
    private int timer;
    private Viewport viewport;
    private int score;
    private int lives;
    private float elapsedTime;

    Label countdownLabel;
    Label scoreLabel;
    Label livesLabel;
    Label countdownLabel2;
    Label scoreLabel2;
    Label livesLabel2;

    // CONSTRUCTOR
    public PlayerHUD(Player player, Batch gamebatch){
        viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, gamebatch);
        timer = 0;
        score = 0;
        lives = player.getHealth();
        elapsedTime = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        countdownLabel = new Label(String.format("TIMER"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("SCORE"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label(String.format("LIVES"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        countdownLabel2 = new Label(String.format("%03d", timer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel2 = new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel2 = new Label(String.format("%01d", lives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        /** Positionning the labels **/

        table.add(countdownLabel).expandX().padTop(5);
        table.add(scoreLabel).expandX().padTop(5);
        table.add(livesLabel).expandX().padTop(5);
        table.row();
        table.add(countdownLabel2).expandX().padTop(5);
        table.add(scoreLabel2).expandX().padTop(5);
        table.add(livesLabel2).expandX().padTop(5);
        stage.addActor(table);
    }
    // METHODS

    public void increaseTimer(float deltatime) {
        /** Use the 60FPS setting **/
        elapsedTime += deltatime;
        if (elapsedTime >= 1) {
            timer++;
            countdownLabel2.setText(String.format("%03d", timer));
            elapsedTime = 0;
        }
    }

    public void harrierDestroyed(){
        score = score + 10;
        scoreLabel2.setText(String.format("%04d", score));
    }

    public void playerDead(float deltatime){
        lives--;
        livesLabel2.setText(String.format("%01d", lives));
    }

    public int getTime(){
        return timer;
    }
}
