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
     * This method creates a {@link Player} instance for future
     * tests.
     */
//    @Before
//    public void setup() {
//        player = new Player();
//    }


    /**
     * Tests {@link Player#setPlayerMapStart(int, int, int)} by
     * ensuring the player's initial tile is a grass tile and that
     * the player's initial coordinates are the ones specified.
     */
//    @Test
//    public void testSetPlayerMapStart(){
//        int size = 10;
//        player.setPlayerMapStart(size, size/2, size/3);
//
//        assertEquals('g', player.getPlayerMapCopy().getTileType(size/2,size/3));
//        assertEquals(size/2, player.getX());
//        assertEquals(size/3, player.getY());
//    }


    /**
     * Tests {@link Player#moveAllowed(char)} by entering both
     * valid and invalid moves and checking the return value in
     * each case.
     */
//    @Test
//    public void testMoveAllowed(){
//        int size = 10;
//
//        // Player located at bottom left corner
//        player.setPlayerMapStart(size, size-1, 0);
//        boolean allowed_down = player.moveAllowed('d');
//        boolean allowed_left = player.moveAllowed('l');
//        boolean allowed_right = player.moveAllowed('r');
//        boolean allowed_up = player.moveAllowed('u');
//
//        assertEquals(false, allowed_down);
//        assertEquals(false, allowed_left);
//        assertEquals(true, allowed_right);
//        assertEquals(true, allowed_up);
//    }


    /**
     * Tests {@link Player#move(char)} with valid input.
     */
//    @Test
//    public void testMove(){
//        int size = 15;
//        player.setPlayerMapStart(size, 0, 0);
//
//        player.move('d');
//        assertEquals(1, player.getX());
//        assertEquals(0, player.getY());
//    }


    /**
     * Tests {@link Player#move(char)} with invalid input
     * which throws an exception.
     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testMoveInvalid(){
//        int size = 15;
//        player.setPlayerMapStart(size, 0, 0);
//
//        player.move('a');
//    }


    /**
     * Tests {@link Player#updateMap(int, int, char)} with valid input,
     * by changing an undiscovered (?) tile to a water tile.
     */
//    @Test
//    public void testUpdateMap(){
//        int size = 20;
//        player.setPlayerMapStart(size, size/2, size/2);
//
//        player.updateMap(0, 0, 'w');
//        assertEquals('w', player.getPlayerMapCopy().getTileType(0,0));
//    }


    /**
     * Tests {@link Player#updateMap(int, int, char)} with invalid input,
     * by trying to change a tile of known type to a different type. This
     * throws an exception.
     */
//    @Test(expected = IllegalStateException.class)
//    public void testUpdateMapInvalid(){
//        int size = 20;
//        player.setPlayerMapStart(size, size/2, size/2);
//
//        player.updateMap(size/2, size/2, 't');
//    }


    /**
     * This method frees memory after all tests are completed.
     */
//    @After
//    public void teardown(){
//        player = null;
//    }
}
