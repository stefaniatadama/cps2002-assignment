package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SafeMapTest {

    /**
     * Attribute {@code size} was used as a fixed size of
     * the map for the tests.
     */
    int size = 20;


    /**
     * {@link SafeMap} object used for testing.
     */
    SafeMap safemap;


    /**
     * This method creates a {@link SafeMap} instance for future
     * tests.
     */
    @Before
    public void setup(){
        safemap = new SafeMap(size);
    }


    /**
     * Tests {@link SafeMap#generate()} by checking that there
     * is only one treasure tile in the map.
     */
    @Test
    public void testOnlyOneTreasureTile(){
        safemap.generate();

        int no_of_treasure_tiles = 0;

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(safemap.getTileType(i,j) == 't')
                    no_of_treasure_tiles++;
            }
        }

        assertEquals(1, no_of_treasure_tiles);
    }


    /**
     * Tests {@link SafeMap#generate()} by checking that the
     * number of water tiles is at most 10%.
     */
    @Test
    public void testWaterTilesRatio(){
        safemap.generate();

        int no_of_water_tiles = 0;

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(safemap.getTileType(i,j) == 'w')
                    no_of_water_tiles++;
            }
        }

        double ratio = (no_of_water_tiles*100)/(size*size);
        assertTrue(ratio <= 10);
    }


    /**
     * This method frees memory after all tests are completed.
     */
    @After
    public void teardown(){
        safemap = null;
    }
}
