package com.mygdx.main.actors.ships;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.main.actors.projectiles.HarrierBullet;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

import java.util.ArrayList;

public class TrogonHarrier extends Entity {

    // ATTRIBUTES
    int speed = 100;
    float posX;
    float posY;
    public boolean remove = false;

    // CONSTRUCTOR
    public TrogonHarrier(float posX, float posY) {
        super();
        this.posX = posX;
        this.posY = posY;
    }

    // METHOD
    @Override
    public void initEntity() {
        width = 30;
        height = 20;
        health = 3;
        currentState = State.IDLE;

        /** Call refsheet for the Trogon Harrier **/
        atlas = new TextureAtlas("Spritesheets/TrogonHarrier_Spritesheet.txt");

        /** Setup Trogon idle sprite **/
        idleAnimation = new Animation(0.33f, atlas.findRegions("TrogonHarrier"));
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
        idleSprite = new AnimatedSprite(idleAnimation);
        idleSprite.rotate(180);

        /** Call refsheet for the death animation **/
        deadAtlas = new TextureAtlas("Spritesheets/Explosion_SpriteSheet.txt");

        /** Setup Trogon death sprite **/
        deadAnimation = new Animation<TextureRegion>(0.05f, deadAtlas.getRegions());
        deadAnimation.setPlayMode(Animation.PlayMode.LOOP);
        deadSprite = new AnimatedSprite(deadAnimation);

        /** Call the refsheet for the hit animation **/
        hurtAtlas = new TextureAtlas("Spritesheets/TrogonHarrier_Spritesheet.txt");

        /** Setup enemy hit sprite **/
        hurtAnimation = new Animation(0.33f, atlas.findRegion("TrogonHarrierHurt"));
        hurtAnimation.setPlayMode(Animation.PlayMode.LOOP);
        hurtSprite = new AnimatedSprite(hurtAnimation);
        hurtSprite.rotate(180);
    }

    @Override
    public boolean isHit(Rectangle otherrectangle) {
        hitbox = new Rectangle(posX + 10, posY + 10, width, height);
        return hitbox.overlaps(otherrectangle);
    }

    /** Configure the hitbox at instant T **/
    public Rectangle getBoundingBox() {
        return new Rectangle(posX+10, posY+10, width, height);
    }

    /**
     * THIS ENEMY'S MOVEMENT
     **/
    public void updateTrogonHarrier(float delta, ArrayList<HarrierBullet> trogonBulletsList) {
        posY -= speed * delta;
        idleSprite.setPosition(posX, posY);
        hurtSprite.setPosition(posX, posY);
        deadSprite.setPosition(posX, posY);

        //Despawn the ship if at the border of the screen
        // or destroyed
        if (posY < -100) {
            remove = true;
        }
        if (currentState == State.DEAD) {
            remove = true;
        }

        /** Bullets and shots**/
        int range = 150 - 1 +1;
        int value = (int)(Math.random() * range)+1;
        if (value == 1){
            trogonBulletsList.add(new HarrierBullet(idleSprite.getX() + idleSprite.getWidth() /2,
                    idleSprite.getY() + idleSprite.getHeight() /2));
        }
    }

    /** Losing health and getting destroyed if health hits 0 **/
    public void takingDamage(int damage) {
        health -= damage;
        if (health > 0) {
            blink();
        } else {
            isDead();
            /** Remove the hitbox and delay to show the explosion**/
            width = 0;
            health = 0;
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            currentState = State.DEAD;
                        }
                    }, 450
            );
        }
    }

    public void blink() {
        /** temporarily stock the previous IdleAnimation and swap for the hit animation **/
        final AnimatedSprite originalIdleSprite = idleSprite;
        idleSprite = hurtSprite;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        idleSprite = originalIdleSprite;
                    }
                }, 50
        );
    }

    public boolean isDead() {
        /** temporarily stock the previous IdleAnimation and swap for the death animation **/;
        final AnimatedSprite originalIdleSprite = idleSprite;
        idleSprite = deadSprite;
        return true;
    }

    public State getState() {
        return currentState;
    }

    public int getHealth(){
        return health;
    }

    public void dispose() {
        super.dispose();
    }
}
