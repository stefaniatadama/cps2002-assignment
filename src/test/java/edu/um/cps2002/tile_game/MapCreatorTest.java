package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertSame;

public class MapCreatorTest {

    Map map;

    MapCreator creator;

    @Before
    public void setup(){
        creator = new MapCreator();
        map = creator.createMap("S", 30);
    }


    @Test
    public void testMapNotNull(){
        Map map2 = creator.createMap("H", 10);
        assertEquals(map, map2);
    }


    @After
    public void teardown(){
        creator = null;
    }
}
