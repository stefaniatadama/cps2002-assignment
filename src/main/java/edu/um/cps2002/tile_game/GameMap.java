package edu.um.cps2002.tile_game;

import java.util.Arrays;

/**
 * The {@code GameMap} class implements a {@link Map} object, with
 * the additional method {@link GameMap#generate()} which randomly
 * creates a game map with grass and water tiles, and one treasure tile.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class GameMap extends Map {

    /**
     * Constructor, creates a map of desired size.
     *
     * @param size The desired size of the map ({@code size} &times; {@code size}).
     */
    GameMap(int size){
        super(size);
    }


    /**
     * This method randomly places grass and water tiles in the ratio 25:2, then selects a single tile
     * (not on the border of the map) to place the treasure.
     */
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
