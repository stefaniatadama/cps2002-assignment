package edu.um.cps2002.tile_game;

import java.util.Arrays;

public class GameMap extends Map {

    GameMap(int size){
        super(size);
    }

    void generate(){

        int numberOfWaterTiles = (size * size * 2)/25;     // To keep the 25 : 2 ratio from figure 1

        for(char[] row : tiles)             // All tiles are grass tiles by default
            Arrays.fill(row, 'g');

        // Generate Water tiles
        for(int i = 0; i < numberOfWaterTiles; i++) {

            // The 'alreadyWaterTile' boolean avoids duplicate randomly generated water tiles
            boolean alreadyWaterTile = true;

            while (alreadyWaterTile) {
                int x = (int) (Math.random()*size);
                int y = (int) (Math.random()*size);

                if (tiles[x][y] == 'g') {                           // If grass tile
                    tiles[x][y] = 'w';                              // Set to water tile
                    alreadyWaterTile = false;
                }
            }
        }

        // Treasure tile
        int x = 1 + (int) (Math.random() * (size - 2));     // Random points, not on border
        int y = 1 + (int) (Math.random() * (size - 2));
        tiles[x][y] = 't';

    }
}
