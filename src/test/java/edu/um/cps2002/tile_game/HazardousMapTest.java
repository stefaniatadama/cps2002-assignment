package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class HazardousMapTest {


    /**
     * Attribute {@code size} was used as a fixed size of
     * the map for the tests.
     */
    int size = 20;


    /**
     * {@link HazardousMap} object used for testing.
     */
    HazardousMap hazardousmap;


    /**
     * This method creates a {@link HazardousMap} instance for future
     * tests.
     */
    @Before
    public void setup(){
        hazardousmap = new HazardousMap(size);
    }


    /**
     * Tests {@link HazardousMap#generate()} by checking that there
     * is only one treasure tile in the map.
     */
    @Test
    public void testOnlyOneTreasureTile(){
        hazardousmap.generate();

        int no_of_treasure_tiles = 0;

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(hazardousmap.getTileType(i,j) == 't')
                    no_of_treasure_tiles++;
            }
        }

        assertEquals(1, no_of_treasure_tiles);
    }


    /**
     * Tests {@link HazardousMap#generate()} by checking that the
     * number of water tiles is between 25% and 35%.
     */
    @Test
    public void testWaterTileRatio(){
        hazardousmap.generate();

        int no_of_water_tiles = 0;

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(hazardousmap.getTileType(i,j) == 'w')
                    no_of_water_tiles++;
            }
        }

        double ratio = (no_of_water_tiles*100)/(size*size);
        System.out.println(ratio);
        // We allow 24 due to the int conversion when calculating the number of water tiles in HazardousMap
        assertTrue(24 <= ratio && ratio <= 35);
    }


    /**
     * This method frees memory after all tests are completed.
     */
    @After
    public void teardown(){
        hazardousmap = null;
    }
}
