package com.mygdx.main.gamestates;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.main.Game;
import com.mygdx.main.actors.projectiles.HarrierBullet;
import com.mygdx.main.actors.projectiles.PlayerBullet;
import com.mygdx.main.actors.ships.Player;
import com.mygdx.main.actors.ships.TrogonHarrier;
import com.mygdx.main.levels.Level01;
import com.mygdx.main.managers.*;

import java.util.ArrayList;

public class GameScreen extends AbstractScreen  {

    public enum State{
        OVER,
        RUN
    }

    // ATTRIBUTES
    private State state = State.RUN;
    private int level;
    private SpriteBatch batch;


    /** Camera attributes **/
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private int mapHeight;
    private int mapTop;
    private float cameraHalfHeight;
    private PlayerHUD hud;


    /** Maps attributes **/
    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMapTileLayer layer;
    private LevelManager levelmanager;
    private String levelname;

    /** Player attributes **/
    private Player player;
    private PlayerController playerController;
    private ArrayList<PlayerBullet> playerBulletsList;

    /** Enemeies attributes **/
    private TrogonHarrier trogonHarrier;
    private ArrayList<TrogonHarrier> harrierList;
    public ArrayList<HarrierBullet> trogonBulletsList;



    /** Level's enemy layout **/
    Level01 level01;

    // CONSTRUCTOR
    public GameScreen(Integer level) {
    /** RENDER CONFIG **/
    super();
    this.level = level.intValue();
    batch = Game.getBatch();


        /** SETUP LEVEL'S CAMERA **/
    gameCam = new OrthographicCamera();
    gamePort = new FitViewport(Game.WIDTH, Game.HEIGHT, gameCam);
    gamePort.apply();

    /** SETUP THE LEVEL'S MAP **/
        switch(level){
            default:
                levelname = "Level_01.tmx";
        }

    levelmanager = new LevelManager(levelname);
    mapRenderer = new OrthogonalTiledMapRenderer(levelmanager.getMap());
    gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    layer = (TiledMapTileLayer) levelmanager.getMap().getLayers().get(0);
    mapHeight = layer.getHeight() * layer.getTileHeight();
    mapTop = 0 + mapHeight;
    cameraHalfHeight = gameCam.viewportHeight * 0.5f;

    /** INSERT THE PLAYER & PLAYER DEPENDENCIES**/
    player = new Player();
    playerController = new PlayerController(player);
    playerBulletsList = new ArrayList<PlayerBullet>();

    /** INITIALIZE HUD **/
    hud = new PlayerHUD(player, batch);

    /** CREATE ENEMY NODES & DEPENDENCIES **/
    harrierList = new ArrayList<TrogonHarrier>();
    level01 = new Level01(harrierList);
    trogonBulletsList = new ArrayList<HarrierBullet>();

    }

    // ACCESSORS
    // METHODS

    public void scrolling(float dt) {
        /** INITIATE SCROLLING **/
        gameCam.position.y += 19 * dt;
        /** STOP THE SCROLLING AT THE TOP OF THE MAP **/
        if ((gameCam.position.y + cameraHalfHeight) >= mapTop) {
            gameCam.position.y = mapTop - cameraHalfHeight;
        }
    }

    @Override
    public void buildStage() {
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        switch (state){
            case RUN:
                /** RENDER THE SCROLLING AND BACKGROUND FOR THE CURRENT LEVEL **/
                mapRenderer.render();
                scrolling(delta);
                gameCam.update();
                mapRenderer.setView(gameCam);

                /** INSERT HUD **/
                batch.setProjectionMatrix(hud.stage.getCamera().combined);
                hud.stage.draw();
                hud.increaseTimer(delta);

                /** BATCH BEGING**/
                batch.begin();

                /** INSERT THE PLAYER **/
                player.drawEntity(batch);
                playerController.updatePlayer(delta, playerBulletsList);

                /** INSERT ENEMY FACTORY **/
                Level01.setupLevel01Spawn(delta, hud.getTime());

                /** RENDER ENEMIES **/
                for (TrogonHarrier harrier : harrierList) {
                    harrier.drawEntity(batch);
                }

                ArrayList<TrogonHarrier> harriersToRemove = new ArrayList<>();
                for (TrogonHarrier harrier : harrierList) {
                    harrier.updateTrogonHarrier(delta, trogonBulletsList);
                    if (harrier.remove) {
                        harriersToRemove.add(harrier);
                    }
                }

                /** RENDER PLAYER PROJECTILES **/
                ArrayList<PlayerBullet> bulletsToRemove = new ArrayList<PlayerBullet>();
                for (PlayerBullet bullet : playerBulletsList) {
                    bullet.updateBullet(delta);
                    if (bullet.remove)
                        bulletsToRemove.add(bullet);
                }

                for (PlayerBullet bullet : playerBulletsList) {
                    bullet.drawBullet(batch);
                }

                /** RENDER ENEMIES PROJECTILES**/
                ArrayList<HarrierBullet> trogonBulletsToRemove = new ArrayList<>();
                for(HarrierBullet bullet : trogonBulletsList){
                    bullet.updateBullet(delta);
                    if(bullet.remove)
                        trogonBulletsToRemove.add(bullet);
                }

                for (HarrierBullet bullet : trogonBulletsList) {
                    bullet.drawBullet(batch);
                }


                /** HANDLING COLLISIONS**/
                /** Player's collision with Harriers **/
                for (TrogonHarrier harrier : harrierList) {
                    if (player.isHit(harrier.getBoundingBox())) {
                        player.playerTakeDamage();
                        hud.playerDead(delta);
                        player.playerRespawn();
                    }
                }

                /** For each player projectiles, check if it hit an enemy **/
                for (PlayerBullet bullet : playerBulletsList) {
                    for (TrogonHarrier harrier : harrierList) {
                        if (harrier.isHit(bullet.getBoundingBox())) {
                            bulletsToRemove.add(bullet);
                            harrier.takingDamage(bullet.getBulletDamage());
                            if (harrier.getHealth() == 0) {
                                hud.harrierDestroyed();
                            }
                        }
                    }
                }

                /** For each enemy's projectiles, check if it hits the player **/
                for(HarrierBullet bullet : trogonBulletsList){
                    if(player.isHit(bullet.getBoundingBox())){
                        player.playerTakeDamage();
                        hud.playerDead(delta);
                        player.playerRespawn();
                    }
                }

                /** DELETING ENTITES **/
                playerBulletsList.removeAll(bulletsToRemove);
                harrierList.removeAll(harriersToRemove);
                trogonBulletsList.removeAll(trogonBulletsToRemove);

                /** GAME OVER **/

                if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
                    pause();
                }

                if (player.getHealth() < 0){
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    pause();
                                }
                            }, 4000
                    );
                }

                if (hud.getTime() == 110){
                    pause();
                }

                /** BATCH END **/
                batch.end();
                break;

            case OVER:
                dispose();
                Gdx.input.setCursorCatched(false);
                ScreenManager.getInstance().showScreen(ScreenEnum.MENU);
                break;
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gamePort.update(Game.WIDTH, Game.HEIGHT);
    }

    @Override
    public void pause() {
        state = State.OVER;
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        player.dispose();
    }
}
