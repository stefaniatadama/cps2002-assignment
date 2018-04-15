package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * The {@code GameMapTest} class tests the functionality of
 * methods in the {@link GameMap} class.
 */
public class GameMapTest {


    /**
     * Attribute {@code size} was used as a fixed size of
     * the map for the tests.
     */
    int size = 20;


    /**
     * {@link GameMap} object used for testing.
     */
    GameMap gamemap;


    /**
     * This method creates a {@link GameMap} instance for future
     * tests.
     */
    @Before
    public void setup(){
        gamemap = new GameMap(size);
    }


    /**
     * Tests {@link GameMap#generate()} by checking that there
     * is only one treasure tile in the map.
     */
    @Test
    public void testOnlyOneTreasureTile(){
        gamemap.generate();

        int no_of_treasure_tiles = 0;

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(gamemap.getTileType(i,j) == 't')
                    no_of_treasure_tiles++;
            }
        }

        assertEquals(1, no_of_treasure_tiles);
    }


    /**
     * Tests {@link GameMap#generate()} by checking that the
     * grass:water ratio of tiles in the map is 25:2.
     */
    @Test
    public void testWaterGrassTileRatio(){
        gamemap.generate();

        int no_of_grass_tiles = 0;
        int no_of_water_tiles = 0;

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(gamemap.getTileType(i,j) == 'g')
                    no_of_grass_tiles++;
                else if(gamemap.getTileType(i,j) == 'w')
                    no_of_water_tiles++;
            }
        }

        double ratio = no_of_grass_tiles/no_of_water_tiles;
        assertEquals(25/2, ratio, 1);
    }


    /**
     * This method frees memory after all tests are completed.
     */
    @After
    public void teardown(){
        gamemap = null;
    }
}
