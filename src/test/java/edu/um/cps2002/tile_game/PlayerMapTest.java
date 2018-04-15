package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PlayerMapTest {

    int size = 35;

    PlayerMap playermap;

    @Before
    public void setup(){
        playermap = new PlayerMap(size);
    }

    @Test
    public void testSetTileWater(){
        playermap.generate();

        playermap.setTile(0, 5, 'w');
        assertEquals('w', playermap.getTileType(0,5));
    }

    @Test
    public void testSetTileInvalid(){
        playermap.generate();

        playermap.setTile(1,6, 'a');
        assertEquals('?', playermap.getTileType(1,6));
    }



    @After
    public void teardown(){
        playermap = null;
    }
}
