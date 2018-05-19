package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * The {@code MapCreatorTest} class tests the functionality of
 * methods in the {@link MapCreator} class.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class MapCreatorTest {

    /**
     * {@link Map} object used for testing.
     */
    Map map;

    /**
     * {@link MapCreator} object used for testing.
     */
    MapCreator creator;

    /**
     * This method creates a {@link Map} using {@link MapCreator}
     * for future testing.
     */
    @Before
    public void setup(){
        creator = new MapCreator();
        map = creator.createMap("S", 30);
    }


    /**
     * This method tests whether if a map was already created,
     * {@link MapCreator} returns the already created map when trying
     * to create a new map.
     */
    @Test
    public void testMapNotNull(){
        Map map2 = creator.createMap("H", 10);
        assertEquals(map, map2);
    }


    /**
     * This method frees memory after all tests are completed.
     */
    @After
    public void teardown(){
        creator = null;
    }
}
