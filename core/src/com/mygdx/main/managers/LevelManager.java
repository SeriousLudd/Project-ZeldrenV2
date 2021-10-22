package com.mygdx.main.managers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class LevelManager {
    /** THIS CLASS IS USED TO HANDLE AND HOLD THE TmxMapLoader **/
    /** This way, we only just have to indicate in the GameScreen class
        the name of the map we should load **/

    // Attributes
    private static TmxMapLoader mapLoader;
    private static TiledMap map;


    // Constructor
    public LevelManager(String mapname){
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Levels/" + mapname);

    }

    // Methods
    public static TiledMap getMap(){
        return map;
    }

}
