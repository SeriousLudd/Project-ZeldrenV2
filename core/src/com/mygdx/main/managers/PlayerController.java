package com.mygdx.main.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.main.Game;
import com.mygdx.main.actors.projectiles.PlayerBullet;
import com.mygdx.main.actors.ships.Entity;
import com.mygdx.main.actors.ships.Player;

import java.util.ArrayList;


public class PlayerController  {

    // ATTRIBUTES
    Player player;
    float elapsedTime = 0;


    // CONSTRUCTOR
    public PlayerController(Player player){
        this.player = player;
    }
    // METHODS

    public void updatePlayer(float delta, ArrayList<PlayerBullet> playerBulletsList) {
        Gdx.input.setCursorCatched(true);
        if (player.getState() != Entity.State.DEAD) {
            int mouseCoordinatesX = Gdx.input.getX();
            int mouseCoordinatesY = Gdx.input.getY();

            /** Bounding box **/

            if (mouseCoordinatesX < 20) {
                mouseCoordinatesX = 20;
                Gdx.input.setCursorPosition(mouseCoordinatesX, mouseCoordinatesY);
            }
            if (mouseCoordinatesX > Game.WIDTH - 20) {
                mouseCoordinatesX = Game.WIDTH - 20;
                Gdx.input.setCursorPosition(mouseCoordinatesX, mouseCoordinatesY);
            }
            if (mouseCoordinatesY < 0) {
                mouseCoordinatesY = 0;
                Gdx.input.setCursorPosition(mouseCoordinatesX, mouseCoordinatesY);
            }
            if (mouseCoordinatesY > Game.HEIGHT) {
                mouseCoordinatesY = Game.HEIGHT;
                Gdx.input.setCursorPosition(mouseCoordinatesX, mouseCoordinatesY);
            }

            /** Allow mouse to control the player **/
            player.idleSprite.setPosition
                    (mouseCoordinatesX - player.idleSprite.getWidth() / 2, Gdx.graphics.getHeight()
                            - mouseCoordinatesY - player.idleSprite.getHeight() / 2);

            /** Allow fire for player with the mouse **/
            elapsedTime += delta;
            if (elapsedTime >= 0.1f) {
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    playerBulletsList.add(new PlayerBullet(player.idleSprite.getX() + player.idleSprite.getWidth() / 2,
                            player.idleSprite.getY() + player.idleSprite.getHeight() / 2));
                }
                elapsedTime = 0;
            }
        }
    }
}
