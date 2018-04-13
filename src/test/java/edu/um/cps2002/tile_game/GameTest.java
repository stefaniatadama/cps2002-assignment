package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    Game game;

    @Before
    public void setup(){
        game = new Game();
    }

    // Test various combinations of players/map sizes
    @Test
    public void testTooManyPlayers(){
        boolean ans = game.setPlayersMap(9, 40);
        assertEquals(false, ans);
    }

    @Test
    public void testTooLittlePlayers(){
        boolean ans = game.setPlayersMap(1, 7);
        assertEquals(false, ans);
    }

    @Test
    public void testTooSmallMap(){
        boolean ans = game.setPlayersMap(3, 3);
        assertEquals(false, ans);
    }

    @Test
    public void testTooBigMap(){
        boolean ans = game.setPlayersMap(6, 51);
        assertEquals(false, ans);
    }

    @Test
    public void testGoodPlayersGoodMapSmall(){
        boolean ans = game.setPlayersMap(4, 6);
        assertEquals(true, ans);
    }

    @Test
    public void testGoodPlayersGoodMapBig(){
        boolean ans = game.setPlayersMap(7, 40);
        assertEquals(true, ans);
    }

    @After
    public void teardown(){
        game = null;
    }

}

