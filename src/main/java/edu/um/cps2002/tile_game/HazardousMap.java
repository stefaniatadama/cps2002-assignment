package edu.um.cps2002.tile_game;

import java.util.Arrays;

/**
 * The {@code HazardousMap} class is a {@link GameMap} object, with
 * the implementation of the method {@link HazardousMap#generate()} which randomly
 * creates a game map with grass and 25%--35% water tiles, and one treasure tile.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class HazardousMap extends GameMap {

    /**
     * Constructor calls {@code super()} in {@link GameMap}, to create a map of desired size.
     *
     * @param size The desired size of the map ({@code size} &times; {@code size}).
     */
    HazardousMap(int size){
        super(size);
    }

    /**
     * This method, generates a 'hazardous' map.
     * A hazardous map is a map with 25%--35% of the tiles having type 'water'.
     */
    public void generate(){
        int randomNum = 25 + (int)(Math.random() * ((35 - 25) + 1));
        int numberOfWaterTiles = (size * size * randomNum) / 100;

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
