package edu.um.cps2002.tile_game;

import java.util.Arrays;

/**
 * The {@code SafeMap} class is a {@link Map} object, with
 * the implementation of the method {@link SafeMap#generate()} which randomly
 * creates a game map with grass and at most 10% water tiles, and one treasure tile.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class SafeMap extends Map {

    /**
     * Constructor calls {@code super()} in {@link Map}, to create a map of desired size.
     *
     * @param size The desired size of the map ({@code size} &times; {@code size}).
     */
    SafeMap(int size){
        super(size);
    }

    /**
     * This method, generates a 'safe' map.
     * A safe map is a map with at most 10% of the tiles having type 'water'.
     */
    public void generate(){
        int randomNum = 1 + (int)(Math.random() * ((10 - 1) + 1));
        int numberOfWaterTiles = (size * size * randomNum) / 100;

        for(Tile[] row : tiles)             // All tiles are grass tiles by default
            Arrays.fill(row, new Tile('g'));

        // Generate Water tiles
        for(int i = 0; i < numberOfWaterTiles; i++) {

            // The 'alreadyWaterTile' boolean avoids duplicate randomly generated water tiles
            boolean alreadyWaterTile = true;

            while (alreadyWaterTile) {
                int x = (int) (Math.random()*size);
                int y = (int) (Math.random()*size);

                if (tiles[x][y].getType() == 'g') {                 // If grass tile
                    setTile(x, y, 'w');                        // Set to water tile
                    alreadyWaterTile = false;
                }
            }
        }

        // Treasure tile
        int x = 1 + (int) (Math.random() * (size - 2));     // Random points, not on border
        int y = 1 + (int) (Math.random() * (size - 2));
        setTile(x, y, 't');
    }
}
