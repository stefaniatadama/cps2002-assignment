package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player player;

    @Before
    public void setup() {
        player = new Player();
    }

    @Test
    public void testPlayerMapStart(){
        int size = 10;
        player.setPlayerMapStart(size, size/2, size/3);

        assertEquals('g', player.getPlayerMapCopy().getTileType(size/2,size/3));
        assertEquals(size/2, player.getX());
        assertEquals(size/3, player.getY());
    }

    @Test
    public void testMoveAllowed(){
        int size = 10;

        // Player located at bottom left corner
        player.setPlayerMapStart(size, size-1, 0);
        boolean allowed_down = player.moveAllowed('d');
        boolean allowed_left = player.moveAllowed('l');
        boolean allowed_right = player.moveAllowed('r');
        boolean allowed_up = player.moveAllowed('u');


        assertEquals(false, allowed_down);
        assertEquals(false, allowed_left);
        assertEquals(true, allowed_right);
        assertEquals(true, allowed_up);
    }

    @Test
    public void testMove(){
        int size = 15;
        player.setPlayerMapStart(size, 0, 0);

        player.move('d');
        assertEquals(1, player.getX());
        assertEquals(0, player.getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveInvalid(){
        int size = 15;
        player.setPlayerMapStart(size, 0, 0);

        player.move('a');
    }

    @Test
    public void testUpdateMap(){
        int size = 20;
        player.setPlayerMapStart(size, size/2, size/2);

        player.updateMap(0, 0, 'w');
        assertEquals('w', player.getPlayerMapCopy().getTileType(0,0));
    }

    @Test(expected = IllegalStateException.class)
    public void testUpdateMapInvalid(){
        int size = 20;
        player.setPlayerMapStart(size, size/2, size/2);

        player.updateMap(size/2, size/2, 't');
    }

    @After
    public void teardown(){
        player = null;
    }
}
