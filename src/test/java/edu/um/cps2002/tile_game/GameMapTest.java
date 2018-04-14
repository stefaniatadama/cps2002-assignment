package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GameMapTest {

    int size = 20;

    GameMap gamemap;

    @Before
    public void setup(){
        gamemap = new GameMap(size);
    }

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

    @After
    public void teardown(){
        gamemap = null;
    }
}
