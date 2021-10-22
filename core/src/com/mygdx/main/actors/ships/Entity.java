package com.mygdx.main.actors.ships;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

public class Entity {

    /**
     * This class handle NPC and Player sprites
     **/

    // ATTRIBUTES
    protected int health;
    protected TextureAtlas atlas;
    public AnimatedSprite idleSprite;
    protected Animation<TextureRegion> idleAnimation;
    protected TextureAtlas hurtAtlas;
    protected Animation<TextureRegion> hurtAnimation;
    protected AnimatedSprite hurtSprite;
    protected TextureAtlas deadAtlas;
    protected AnimatedSprite deadSprite;
    protected Animation<TextureRegion> deadAnimation;
    protected State currentState = State.IDLE;
    protected Rectangle hitbox;

    protected int width;
    protected int height;



    // CONSTRUCTOR
    public Entity() {
        initEntity();
    }

    // METHODS

    /** Possible states of the entity **/
    public enum State {
        IDLE, HURT, DEAD;
    }

    public void initEntity() {
    }

    /** IMPORTANT : check if the hitbox of the entity encounters another one **/
    /** Each entity have it's own unique hitbox, so we won't configure it here **/
    public boolean isHit(Rectangle otherrectangle) {
        return hitbox.overlaps(otherrectangle);
    }

    public void drawEntity(Batch gamebatch) {
        idleSprite.draw(gamebatch);
    }

    public void dispose() {
        atlas.dispose();
        deadAtlas.dispose();
    }
}
