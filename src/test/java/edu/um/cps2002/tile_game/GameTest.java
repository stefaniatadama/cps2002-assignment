package edu.um.cps2002.tile_game;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static java.lang.System.out;
import static org.junit.Assert.*;


/**
 * The {@code GameTest} class tests the functionality of
 * methods in the {@link Game} class.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class GameTest {


    /**
     * {@link Game} object used for testing.
     */
    Game game;


    /**
     * {@link Game} object used for testing.
     */
    Game game2;


    /**
     * This method creates a {@link Game} instance for future tests.
     */
    @Before
    public void setup(){
        game = new Game();
    }


    /**
     * Tests {@link Game#setPlayersMap(int, int)} with an invalid
     * number of players (too many).
     */
    @Test
    public void testTooManyPlayers(){
        boolean ans = game.setPlayersMap(9, 40);
        assertEquals(false, ans);
    }


    /**
     * Tests {@link Game#setPlayersMap(int, int)} with an invalid
     * number of players (too little).
     */
    @Test
    public void testTooLittlePlayers(){
        boolean ans = game.setPlayersMap(1, 7);
        assertEquals(false, ans);
    }


    /**
     * Tests {@link Game#setPlayersMap(int, int)} with an invalid
     * map size (too small).
     */
    @Test
    public void testTooSmallMap(){
        boolean ans = game.setPlayersMap(3, 3);
        assertEquals(false, ans);
    }


    /**
     * Tests {@link Game#setPlayersMap(int, int)} with an invalid
     * map size (too large).
     */
    @Test
    public void testTooBigMap(){
        boolean ans = game.setPlayersMap(6, 51);
        assertEquals(false, ans);
    }


    /**
     * Tests {@link Game#setPlayersMap(int, int)} with valid inputs.
     */
    @Test
    public void testGoodPlayersGoodMapSmall(){
        boolean ans = game.setPlayersMap(4, 6);
        assertEquals(true, ans);
    }


    /**
     * Tests {@link Game#setPlayersMap(int, int)} with valid inputs.
     */
    @Test
    public void testGoodPlayersGoodMapBig(){
        boolean ans = game.setPlayersMap(7, 40);
        assertEquals(true, ans);
    }


    /**
     * Tests {@link Game#start()} with invalid user input (player number),
     * then enters valid input.
     */
    @Test
    public void testIncorrectInput(){
        // 9 players, map size 30, then 8 players, map size 30, safe map
        String input = "9\n 30\n 8\n 30\nS\n";

        // Change input stream
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Set output stream to array output stream
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        game.start();

        // Check that error message appears
        assertThat(output.toString(),
                CoreMatchers.containsString("Incorrect number of players or map size entered. Please try again."));

        // Reset Buffers
        System.setIn(System.in);
        System.setOut(out);
    }


    /**
     * Tests {@link Game#start()} by checking that every player's
     * initial tile is a grass tile.
     */
    @Test
    public void testStartPlayerOnGrassTile(){
        // 4 players, map size 10, hazardous map
        String input = "4\n 10\nH\n";

        // Change input stream
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


    /**
     * Tests {@link Game#gameplayRound()} by making sure that the
     * players' moves are inputted correctly and that the map changes
     * accordingly, so that the round runs smoothly as expected.
     */
    @Test
    public void testGamePlayRound(){
        int size = 10;

        HazardousMap map = new HazardousMap(size);

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Create map made up entirely of grass tiles
                map.setTile(i, j, 'g');
            }
        }

        // Start a game with 2 players and the created map
        game2 = new Game(2, map);

        Player player1 = game2.getPlayer(0);
        Player player2 = game2.getPlayer(1);

        // Set players starting position manually to make sure move is valid
        player1.setPlayerMapStart(size, size/2, size/2);
        player2.setPlayerMapStart(size, size/3, size/3);

        // Both players moving up
        String input = "u\n u\n";

        // Change input stream
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game2.gameplayRound();

        assertEquals('g', player1.getPlayerMapCopy().getTileType(player1.getX(), player1.getY()));
        assertEquals('g', player2.getPlayerMapCopy().getTileType(player2.getX(), player2.getY()));

        // Reset buffer
        System.setIn(System.in);
    }


    /**
     * Tests {@link Game#gameplayRound()} by inputting a move that is
     * invalid, since it would result in the player going outside the
     * map.
     */
    @Test
    public void testGamePlayRoundInvalidMove(){
        int size = 20;

        SafeMap map = new SafeMap(size);

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Create map made up entirely of grass tiles
                map.setTile(i, j, 'g');
            }
        }

        // Start a game with 2 players and the created map
        game2 = new Game(2, map);

        Player player1 = game2.getPlayer(0);
        Player player2 = game2.getPlayer(1);

        // Set players starting position manually to make sure move is invalid
        player1.setPlayerMapStart(size, 0, 0);
        player2.setPlayerMapStart(size, size-1, 0);

        // First player moving left is invalid so then he moves right
        // Second player moving right is invalid so then he moves left
        String input = "l\n r\n r\n l\n";

        // Change input stream
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Set output stream to array output stream
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        game2.gameplayRound();

        assertThat(output.toString(), CoreMatchers.containsString("Invalid move."));

        // Reset buffers
        System.setIn(System.in);
        System.setOut(out);
    }


    /**
     * Tests {@link Game#gameplayRound()} by checking that a player
     * returns to the initial position after stepping on a water tile.
     */
    @Test
    public void testGamePlayRoundWater(){
        int size = 10;

        HazardousMap map = new HazardousMap(size);

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Create map made up entirely of grass tiles
                map.setTile(i, j, 'g');
            }
        }

        // Set tile to the left of the player to water
        map.setTile(3,2, 'w');

        // Start a game with 1 player and the created map
        game2 = new Game(1, map);

        Player player1 = game2.getPlayer(0);

        // Set player's starting position manually
        player1.setPlayerMapStart(size, 3, 3);

        // Player moving left
        String input = "l\n";

        // Change input stream
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game2.gameplayRound();

        assertEquals(3, player1.getX());
        assertEquals(3, player1.getY());

        // Reset buffer
        System.setIn(System.in);
    }


    /**
     * Tests {@link Game#gameplayRound()} by making sure that if a
     * player finds the treasure tile, the game stops and he/she is
     * declared a winner.
     */
    @Test
    public void testGamePlayRoundWinner(){
        int size = 10;

        SafeMap map = new SafeMap(size);

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Create map made up entirely of grass tiles
                map.setTile(i, j, 'g');
            }
        }

        // Set tile to the left of the player to treasure
        map.setTile(3,2, 't');

        // Start a game with 1 player and the created map
        game2 = new Game(1, map);

        Player player1 = game2.getPlayer(0);

        // Set player's starting position manually
        player1.setPlayerMapStart(size, 3, 3);

        // Player moves left
        String input = "l\n";

        // Change input stream
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Set output stream to array output stream
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        game2.gameplayRound();

        assertThat(output.toString(), CoreMatchers.containsString("Player 1 is a winner!"));

        // Reset buffers
        System.setIn(System.in);
        System.setOut(out);
    }


    /**
     * This method frees memory after all tests are completed.
     */
    @After
    public void teardown(){
        game = null;
    }

}

