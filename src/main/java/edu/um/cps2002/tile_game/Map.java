package edu.um.cps2002.tile_game;

public abstract class Map {

    int size;
    char[][] tiles;

    public Map(int size){
        this.size = size;
        this.tiles = new char[size][size];
    }

    abstract void generate();

    final public int getSize(){
        return size;
    }

    final public char getTileType(int x, int y){
        return tiles[x][y];
    }

    final public void setTile(int x, int y, char type){
        switch(type){
            case '?':
            case 'g':
            case 'w':
            case 't':
                tiles[x][y] = type;
                break;

            default:
                //error
                break;
        }
    }
}
