package edu.um.cps2002.tile_game;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static java.lang.System.out;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.containsString;

public class GameTest {

    Game game, game2;

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
        System.setOut(out);
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

    @Test
    public void testGamePlayRound() throws IOException {
        int size = 10;

        GameMap map = new GameMap(size);

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

        game2.generateHTMLFiles();

        // Both players moving up
        String input = "u\n u\n";

        // Change input stream to 'input' above
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game2.gameplayRound();

        assertEquals('g', player1.getPlayerMapCopy().getTileType(player1.getX(), player1.getY()));
        assertEquals('g', player2.getPlayerMapCopy().getTileType(player2.getX(), player2.getY()));

        System.setIn(System.in);
    }

    @Test
    public void testGamePlayRoundInvalidMove() throws IOException {
        int size = 20;

        GameMap map = new GameMap(size);

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

        game2.generateHTMLFiles();

        // First player moving left is invalid so then he moves right
        // Second player moving right is invalid so then he moves left
        String input = "l\n r\n r\n l\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        game2.gameplayRound();

        assertThat(output.toString(), CoreMatchers.containsString("Invalid move."));

        System.setIn(System.in);
        System.setOut(out);
    }

    @Test
    // Testing that the player returns to initial position after stepping on a water tile
    public void testGamePlayRoundWater() throws IOException{
        int size = 10;

        GameMap map = new GameMap(size);

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

        // Set players starting position manually
        player1.setPlayerMapStart(size, 3, 3);

        game2.generateHTMLFiles();

        // Both players moving up
        String input = "l\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game2.gameplayRound();

        assertEquals(3, player1.getX());
        assertEquals(3, player1.getY());

        System.setIn(System.in);
    }

    @Test
    public void testGamePlayWinner() throws IOException{
        int size = 10;

        GameMap map = new GameMap(size);

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

        // Set players starting position manually
        player1.setPlayerMapStart(size, 3, 3);

        game2.generateHTMLFiles();

        // Both players moving up
        String input = "l\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        game2.gameplayRound();

        assertThat(output.toString(), CoreMatchers.containsString("Player 1 is a winner!"));

        System.setIn(System.in);
        System.setOut(out);
    }

    @After
    public void teardown(){
        game = null;
    }

}

