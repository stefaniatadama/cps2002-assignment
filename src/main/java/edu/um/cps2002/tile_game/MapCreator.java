package edu.um.cps2002.tile_game;

public class MapCreator {

    public GameMap createMap(String type, int size){
        if(type.equals("H"))
            return new HazardousMap(size);
        if(type.equals("S"))
            return new SafeMap(size);
        return null;
    }
}