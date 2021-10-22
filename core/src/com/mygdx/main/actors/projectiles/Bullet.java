package com.mygdx.main.actors.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {

    /** This class handles projectiles **/

    // ATTRIBUTES
    protected int speed;
    protected int damage;
    protected float posX;
    protected float posY;
    protected Texture texture;
    protected int width;
    protected int height;

    public Rectangle boundingBox;
    public boolean remove;

    // CONSTRUCTOR
    public Bullet(){
        initBullet();
    }

    // METHODS
    public void initBullet(){}

    public void updateBullet(float deltatime){}

    /** Return bullet's damage **/
    public int getBulletDamage(){
        return damage;
    }

    /** Draw the bullet **/
    public void drawBullet(Batch gamebatch){
    gamebatch.draw(texture,posX,posY);
    }

    public void dispose(){
        texture.dispose();
    }

}
