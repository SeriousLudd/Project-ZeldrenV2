package com.mygdx.main.actors.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.main.Game;

public class PlayerBullet extends Bullet{

    // ATTRIBUTES

    // CONSTRUCTOR
    public PlayerBullet(float posX, float posY) {
        super();
        this.posY = posY;
        this.posX = posX;
    }

    // METHODS

    /** Initialize player's bullet with appropriate texture **/
    @Override
    public void initBullet() {
        speed = 800;
        damage = 1;
        width = 2;
        height = 5;

        if (texture == null){
            texture = new Texture("Projectiles/Bullet.png");
        }
    }

    /** Bullet's movement **/
    @Override
    public void updateBullet(float deltatime) {
        posY += speed * deltatime;
        if(posY > Game.HEIGHT){
            remove = true;
        }
    }

    /** Bullet's hitbox **/
    public Rectangle getBoundingBox(){
        return new Rectangle(posX, posY, width, height);
    }

}
