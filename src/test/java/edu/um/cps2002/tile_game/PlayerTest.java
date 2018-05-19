package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * The {@code PlayerTest} class tests the functionality of
 * methods in the {@link Player} class.
 *
 *  @author Luke Collins &amp; Stefania Damato
 *
 */
public class PlayerTest {


    /**
     * {@link Player} object used for testing.
     */
    Player player;


    /**
     * {@link Map} object used for testing.
     */
    Map map;


    /**
     * {@link Game} object used for testing.
     */
    Game game;


    /**
     * Size of the map used for testing.
     */
    int size = 25;


    /**
     * This method creates a {@link Player}, a {@link Map} and a
     * {@link Game} instance for future tests.
     */
    @Before
    public void setup() {
        player = new Player(0,0);
        map = new SafeMap(size);
        map.generate();
        game = new Game();
    }


    /**
     * Tests {@link Player#move(char)} with valid input.
     */
    @Test
    public void testMove(){
        player.move('d');
        assertEquals(1, player.getX());
        assertEquals(0, player.getY());
    }


    /**
     * Tests {@link Player#move(char)} with invalid input
     * which throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMoveInvalid(){
        player.move('a');
    }

    /**
     * Tests {@link Player#update(Map, int, int)} with a specific
     * map instance.
     */
    @Test
    public void testUpdate(){
        // Check whether visited before/after
        boolean hasVisitedBefore = map.tiles[2][2].hasVisited(player);
        player.update(map, 2, 2);
        boolean hasVisitedAfter = map.tiles[2][2].hasVisited(player);

        assertEquals(false, hasVisitedBefore);
        assertEquals(true, hasVisitedAfter);
    }

    /**
     * This method frees memory after all tests are completed.
     */
    @After
    public void teardown(){
        player = null;
    }
}
