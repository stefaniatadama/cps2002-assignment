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
     * Another {@link Game} object used for testing.
     */
    Game game2;

    /**
     * {@link Map} object used for testing.
     */
    Map map;


    /**
     * Size of map used for testing.
     */
    int size = 25;


    /**
     * This method creates a {@link Map} using {@link MapCreator}
     * and a new {@link Game}. The map is shared among all
     * instances of the map creator.
     */
    @Before
    public void setup(){
        map = new HazardousMap(size);

        // Start by setting all tiles on the map to green, otherwise we
        // might get a null pointer exception.
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                map.setTile(i, j, 'g');

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
     * Tests {@link Game#start()} with invalid user input (number of players),
     * then enters valid input.
     */
    @Test
    public void testInvalidNumberofPlayers(){
        /* 9 players, map size 25, then 8 players, map size 25, safe map
         * Note that in these tests, the map is already set to size 25 and
         * hazardous, so these inputs are only affecting the player number.
         * We cannot change the map size or type since these were already
         * set in the setup stage and the map is static. */
        String input = "9\n 25\n 8\n 25\n5\nH\n";

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
     * Tests {@link Game#start()} with invalid user input (number of teams),
     * then enters valid input.
     */
    @Test
    public void testInvalidNumberofTeams(){
        /* 8 players, 10 teams */
        String input = "8\n25\n10\n8\nH\n";

        // Change input stream
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Set output stream to array output stream
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        game.start();

        // Check that error message appears
        assertThat(output.toString(),
                CoreMatchers.containsString("Invalid number of teams. Please try again."));

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
        // 4 players, map size 25, hazardous map
        String input = "4\n 25\n2\nH\n";

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
        // Create teams
        Team team1 = new Team(1);
        Team team2 = new Team(2);

        // Set players starting position manually to make sure move is valid
        Player player1 = new Player(size/2, size/2);
        Player player2 = new Player(size/3, size/3);

        //Set players' initial positions to a grass tiles
        map.setTile(size/2,size/2, 'g');
        map.setTile(size/3,size/3, 'g');

        // Add players to team
        player1.setTeamNo(1);
        player2.setTeamNo(2);
        Player[] players = {player1, player2};
        Team[] teams = {team1, team2};

        // Start a game with the 2 players and the created map
        game2 = new Game(players, teams, map);

        // Both players moving up
        String input = "u\n u\n";

        // Change input stream
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game2.gameplayRound();

        assertEquals('g', map.getTileType(player1.getX(), player1.getY()));
        assertEquals('g', map.getTileType(player2.getX(), player2.getY()));

        // Reset buffer
        System.setIn(System.in);

        game2 = null;
    }


    /**
     * Tests {@link Game#gameplayRound()} by inputting a move that is
     * invalid, since it would result in the player going outside the
     * map.
     */
    @Test
    public void testGamePlayRoundInvalidMove(){
        // Create teams
        Team team1 = new Team(1);
        Team team2 = new Team(2);


        // Set players starting position manually to make sure move is invalid
        Player player1 = new Player(0, 0);
        Player player2 = new Player(size-1, 0);

        //Set players' initial positions to a grass tiles
        map.setTile(0,0, 'g');
        map.setTile(size-1,0, 'g');

        // Add players to team
        player1.setTeamNo(1);
        player2.setTeamNo(2);
        Player[] players = {player1, player2};
        Team[] teams = {team1, team2};

        // Start a game with the 2 players and the created map
        game2 = new Game(players, teams, map);

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

        game2 = null;
    }


    /**
     * Tests {@link Game#gameplayRound()} by checking that a player
     * returns to the initial position after stepping on a water tile.
     */
    @Test
    public void testGamePlayRoundWater(){

        // Create team
        Team team1 = new Team(1);

        // Set player's starting position manually
        Player player1 = new Player(3, 3);
        player1.setTeamNo(1);

        //Set player's initial position to a grass tile
        map.setTile(3,3, 'g');

        Player[] players = {player1};
        Team[] teams = {team1};

        // Start a game with the 1 player and the created map
        game2 = new Game(players, teams, map);

        // Set tile to the left of the player to water
        map.setTile(3,2, 'w');

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

        // Create team
        Team team1 = new Team(1);

        // Set player's starting position manually
        Player player1 = new Player(3, 3);
        player1.setTeamNo(1);

        //Set player's initial position to a grass tile
        map.setTile(3,3, 'g');

        Player[] players = {player1};
        Team[] teams = {team1};

        // Start a game with the 1 player and the created map
        game2 = new Game(players, teams, map);

        // Set tile downwards of the player to treasure
        map.setTile(4,3, 't');

        // Player moves left
        String input = "d\n";

        // Change input stream
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Set output stream to array output stream
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        game2.gameplayRound();

        assertThat(output.toString(), CoreMatchers.containsString("Team 1 wins!"));

        // Reset buffers
        System.setIn(System.in);
        System.setOut(out);
    }

    /**
     * Tests {@link Game#playerMoveAllowed(Player, char)} by entering both
     * valid and invalid moves and checking the return value in each case.
     */
    @Test
    public void testMoveAllowed(){
        //Player starts game at upper left corner
        Player player1 = new Player(0,0);
        Team team1 = new Team(1);
        player1.setTeamNo(1);
        Player[] players = {player1};
        Team[] teams = {team1};

        game2 = new Game(players, teams, map);

        boolean allowed_down = game2.playerMoveAllowed(player1, 'd');
        boolean allowed_left = game2.playerMoveAllowed(player1, 'l');
        boolean allowed_right = game2.playerMoveAllowed(player1, 'r');
        boolean allowed_up = game2.playerMoveAllowed(player1, 'u');

        assertEquals(true, allowed_down);
        assertEquals(false, allowed_left);
        assertEquals(true, allowed_right);
        assertEquals(false, allowed_up);
    }

    /**
     * This method frees memory after all tests are completed.
     */
    @After
    public void teardown(){
        game = null;
    }

}

