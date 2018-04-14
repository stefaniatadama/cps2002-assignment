package edu.um.cps2002.tile_game;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.containsString;

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

    @Test
    public void testIncorrectInput(){

        // 9 players, map size 30, then 8 players
        String input = "9\n 30\n 8\n 30\n";

        // Change input stream to 'input' above
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Set output stream to output array stream
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        game.start();

        // Check that error message appears
        assertThat(output.toString(),
                CoreMatchers.containsString("Incorrect number of players or map size entered. Please try again."));

        // Reset Buffers
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testStartPlayerOnGrassTile(){

        // 4 players, map size 10
        String input = "4\n 10\n";

        // Change input stream to 'input' above
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game.start();

        int x, y;

        // For each player, check that player position is grass
        for (int i = 0; i < 4; i++){
            x = game.getPlayer(i).getX();
            y = game.getPlayer(i).getY();
            assertEquals('g', game.getMap().getTileType(x,y));
        }

        // Reset Buffer
        System.setIn(System.in);
    }

    // Test
    @After
    public void teardown(){
        game = null;
    }

}

