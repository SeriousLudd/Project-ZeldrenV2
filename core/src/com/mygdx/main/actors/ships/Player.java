package com.mygdx.main.actors.ships;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

public class Player extends Entity {

    // ATTRIBUTES
    private AnimatedSprite originalSprite;

    // CONSTRUCTOR
    public Player() {
        super();
    }

    // METHODS
    @Override
    public void initEntity() {
        width = 30;
        height = 70;
        health = 3;
        currentState = State.IDLE;


        /** Call the refsheet for the Player **/
        atlas = new TextureAtlas("Spritesheets/Player_Spritesheet.txt");

        /** Setup the player idle sprite **/
        idleAnimation = new Animation(0.20f, atlas.getRegions());
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
        idleSprite = new AnimatedSprite(idleAnimation);
        originalSprite = idleSprite;

        /** Call the refsheet for the death animation **/
        deadAtlas = new TextureAtlas("Spritesheets/Explosion_SpriteSheet.txt");

        /** Setup player death sprite **/
        deadAnimation = new Animation<TextureRegion>(0.05f, deadAtlas.getRegions());
        deadAnimation.setPlayMode(Animation.PlayMode.LOOP);
        deadSprite = new AnimatedSprite(deadAnimation);
        deadSprite.rotate(180);

    }

    /** COLLISION BOX **/
    @Override
    public boolean isHit(Rectangle otherrectangle) {
        hitbox = new Rectangle(idleSprite.getX(), idleSprite.getY()+20, width, height - 40);
        return hitbox.overlaps(otherrectangle);
    }

    /** Configure the hitbox at instant T **/
    public Rectangle getBoundingBox() {
        return new Rectangle(idleSprite.getX(), idleSprite.getY(), width, height - 20);
    }

    public void playerTakeDamage() {
        final float currentPosX = idleSprite.getX();
        final float currentPosY = idleSprite.getY();
        idleSprite = deadSprite;
        idleSprite.setPosition(currentPosX, currentPosY);
        currentState = State.DEAD;
        health--;
        width = 0;
        height = 0;
    }

    public void playerRespawn(){
        /** Respawning process **/
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        /** Add a frame where the player is invincible after death **/
                        if (health >= 0) {
                            idleSprite = originalSprite;
                             currentState = State.IDLE;
                            idleSprite.setAlpha(0.2f);
                            new java.util.Timer().schedule(
                                    new java.util.TimerTask() {
                                        @Override
                                        public void run() {
                                            idleSprite.setAlpha(1f);
                                            width = 30;
                                            height = 70;
                                        }
                                    }, 2500
                            );

                        }
                    }
                }, 1500
        );
    }

    public State getState() {
        return currentState;
    }

    public int getHealth() {
        return health;
    }
}
