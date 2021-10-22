package com.mygdx.main.actors.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.main.Game;

public class HarrierBullet extends Bullet {

    // ATTRIBUTES

    // CONSTRUCTOR
    public HarrierBullet(float posX, float posY) {
        super();
        this.posX = posX;
        this.posY = posY;
    }

    // METHODS
    @Override
    public void initBullet() {
        speed = 200;
        damage = 1;
        width = 6;
        height = 6;
        if(texture == null){
            texture = new Texture("Projectiles/EnemyBullet.png");
        }
    }

    @Override
    public void updateBullet(float deltatime) {
        posY -= speed * deltatime;
        if (posY > Game.HEIGHT){
            remove = true;
        }
    }


    /** Bullet's hitbox **/
    public Rectangle getBoundingBox(){
        return new Rectangle(posX, posY, width, height);
    }
}
