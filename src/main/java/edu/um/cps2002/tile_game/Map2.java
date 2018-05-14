package edu.um.cps2002.tile_game;

public interface Map2 {

    void generate();

    int getSize();

    char getTileType(int x, int y);

    void setTile(int x, int y, char type);
}