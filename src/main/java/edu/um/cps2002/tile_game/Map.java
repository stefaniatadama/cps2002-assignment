package edu.um.cps2002.tile_game;

public abstract class Map {

    int size;
    char[][] tiles;

    public Map(int size){
        this.size = size;
    }

    abstract void generate();

    final public int getSize(){
        return size;
    }

    final public char getTileType(int x, int y){
        return tiles[x][y];
    }
}
