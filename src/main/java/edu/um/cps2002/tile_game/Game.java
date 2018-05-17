package edu.um.cps2002.tile_game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * The {@code Game} class performs all the logic of the game, taking
 * care of the setting up of players, all interactions between the
 * players and the map, and the generation of HTML code.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class Game {


    /**
     * Stores the current round, incremented at the end of every
     * {@link Game#gameplayRound()}.
     */
    private int round = 1;


    /**
     * Array of {@link Player} objects, one for each player of the game.
     */
    private Player[] players;


    /**
     * This {@link Map} object contains a two-dimensional array of characters
     * storing the tiles of the game (representing one of water, grass or
     * treasure).
     */
    private Map map;


    /**
     * {@link java.util.Scanner} object for user input.
     */
    private Scanner sc;


    /**
     * The default constructor.
     */
    public Game(){}


    /**
     * A constructor for creating {@link Game} instances from a pre-existing map.
     * Used mainly for testing purposes.
     *
     * @param players The players to be used in the game.
     * @param map A pre-existing {@link Map} object for the game to be played in.
     * @see Player
     */
    Game(Player[] players, Map map){
        this.map = map;
        this.players = players;
    }


    /**
     * The {@code start()} method takes care of the initial set up of the game.
     *
     * <p>
     *     First the user is asked for the number of players (2-8) and the size
     *     of the map (5-50 or 8-50). The correctness of the user input is checked
     *     by the {@code boolean} method {@link Game#setPlayersMap(int, int)}.
     *     The user is also asked whether a safe or a hazardous map is to be used.
     *     Then a corresponding {@link Map} object is created (assigned to
     *     {@link Game#map}) and a {@link Player} object is generated for each
     *     player (stored in {@link Game#players}). Finally each player is assigned
     *     a starting position on the map (which is known only to the player,
     *     stored in {@link Player#x} and {@link Player#y}).
     * </p>
     *
     * @see Player
     * @see Map#generate()
     */
    public void start(){
        sc = new Scanner(System.in);
        int numPlayers = 0, mapSize = 0;
        boolean valid = false, firstLoop = true;
        String mapType = "";

        // While number of players / map size is invalid
        while(!valid){
                if(!firstLoop)
                System.out.println("Incorrect number of players or map size entered. Please try again.");

            System.out.print("Enter number of players (2-8): ");
            numPlayers = sc.nextInt();

            System.out.print("Enter map size (" + ((numPlayers <= 4) ? 5 : 8) + "-50): ");
            mapSize = sc.nextInt();

            // Check if the dimensions entered are valid
            valid = setPlayersMap(numPlayers, mapSize);
            firstLoop = false;
        }

        sc.nextLine();
        // Ask user for map type
        while(!mapType.equals("S") && !mapType.equals("H")){
            System.out.print("Enter map type (S/H): ");
            mapType = sc.next();
        }

        // Initialise map creator
        MapCreator mc = new MapCreator();

        // Initialise and Generate Map
        map = mc.createMap(mapType, mapSize);
        map.generate();

        // Initialise Players
        players = new Player[numPlayers];

        for(int i = 0; i < numPlayers; i++) {

            // Give player a start position, grass tile
            int x = (int) (Math.random() * (mapSize-1));
            int y = (int) (Math.random() * (mapSize-1));

            while (map.getTileType(x, y) != 'g') {
                x = (int) (Math.random() * (mapSize-1));
                y = (int) (Math.random() * (mapSize-1));
            }

            // Initialise the player at position (x,y)
            players[i] = new Player(x, y);

            // The player 'visits' that tile
            map.playerVisitTile(players[i], x, y);
        }
    }


    /**
     * A simple getter for the {@link Game#map} attribute.
     * @return map - The {@link Map} object storing the tiles on which the game
     * is played.
     */
    public Map getMap(){
        return map;
    }


    /**
     * This method carries out a single round of gameplay.
     *
     * <p>
     *     First each player is asked to enter their move (one of U/D/L/R). Then
     *     the corresponding {@link Player} object in {@link Game#players} has
     *     their current position updated (i.e. the player's {@link Player#x} and
     *     {@link Player#y} attributes via {@link Player#move(char)}), so long
     *     as the move is allowed (determined by the boolean
     *     {@link Game#playerMoveAllowed(Player, char)}). If any player lands on a
     *     water tile, then they are sent back to their initial position
     *     (via {@link Player#returnToStart()}), whereas if any player lands on the
     *     treasure tile, they are declared winners and {@code noWinners} is set to
     *     false.
     * </p>
     *
     * @return noWinner - A {@code boolean} variable which is {@code true} when none
     * of the players have found the treasure (i.e. won) and {@code false} if at least
     * one of the players has found the treasure.
     *
     * @throws IllegalStateException Should one of the tiles in the map be different
     * from grass, water or treasure.
     */
    public boolean gameplayRound() throws IllegalStateException{

        // Initialise Scanner, no one has won yet
        sc = new Scanner(System.in);
        boolean noWinners = true;

        // Round Header
        System.out.println("\n---- Round " + round + " ----");

        // Ask move from each player
        for (int i = 0; i < players.length; i++){
            System.out.print("Player " + (i+1) + "'s move. Enter (U)p, (D)own, (L)eft or (R)ight: ");
            char input = Character.toLowerCase(sc.next().trim().charAt(0));

            while(!playerMoveAllowed(players[i], input)) {
                System.out.print("Invalid move. ");
                System.out.print("Player " + (i+1) + "'s move. Please enter (U)p, (D)own, (L)eft or (R)ight: ");
                input = Character.toLowerCase(sc.next().trim().charAt(0));
            }

            // Move the player
            players[i].move(input);

            // Visit new position on map
            map.playerVisitTile(players[i], players[i].getX(), players[i].getY());
        }

        // For each player
        for(int i = 0; i < players.length; i++){

            int x = players[i].getX();
            int y = players[i].getY();
            char currentPosition = map.getTileType(x,y);

            // If water, move back to start, if treasure, declare winner
            switch(currentPosition){
                case 'w':
                    players[i].returnToStart();
                    break;
                case 't':
                    System.out.println("Player " + (i+1) + " is a winner!");
                    noWinners = false;
                    break;
                case 'g':
                    break;
                default:
                    throw new IllegalStateException("Game map contains invalid tile type.");
            }
        }

        // Increment Round
        round++;

        // Return whether someone won
        return noWinners;
    }

    /**
     * A simple getter, returning a specified {@link Player} object from the
     * {@link Game#players} array.
     *
     * @param playerNum Player number (where numbering starts from zero).
     * @return The {@link Player} object in the specified position in the
     * {@link Game#players} array.
     */
    Player getPlayer(int playerNum){
        return players[playerNum];
    }

    /**
     * A boolean function which determines whether a choice of number of players
     * and map size are admissible.
     *
     * <p>
     *     By admissible, it is meant that the number of players can be between
     *     2 and 8, and if there are less than 4 players then the map size can
     *     range from 5&times;5 to 50&times;50, whereas if there are more than
     *     4 players, then the map size can range from 8&times;8 to 50&times;50.
     * </p>
     *
     * @param numPlayers The number of players.
     * @param mapSize The size of the map.
     * @return {@code true} if the choice is admissible, {@code false} otherwise.
     */
    boolean setPlayersMap(int numPlayers, int mapSize){

        return (((numPlayers >= 2) && (numPlayers <= 4) && (mapSize >= 5))  ||
                ((numPlayers >= 5) && (numPlayers <= 8) && (mapSize >= 8))) &&
                (mapSize <= 50);
    }


    /**
     * Generates an HTML file for each player using the {@link Player#generateHTML(int)}
     * method.
     * @throws IOException Should the writing of HTML files fail.
     */
    void generateHTMLFiles() throws IOException {

        // For File Writing
        FileWriter fw;
        BufferedWriter bw;

        // Generate HTML for each player, label player i as i+1
        for (int i = 0; i < players.length; i++) {
            fw = new FileWriter("map_player_" + (i+1) + ".html");
            bw = new BufferedWriter(fw);

            bw.write(map.generateHTML(players[i],i + 1));
            bw.close();
        }
    }

    /**
     * This method examines the player's current position and determines whether
     * or not it is possible to move in a certain direction, ensuring that the
     * player does not move out of the map.
     *
     * @param p The player to make the move.
     * @param move A direction character, one of {@code u} (up), {@code d} (down),
     *             {@code l} (left) or {@code r} (right).
     *
     * @return {@code true} if the move is admissible, {@code false} otherwise.
     */
    boolean playerMoveAllowed(Player p, char move){
         // Check if player is on the edges of the map
        switch(move){
            case 'u':
                return p.getX() != 0;
            case 'd':
                return p.getX() != map.getSize() - 1;
            case 'l':
                return p.getY() != 0;
            case 'r':
                return p.getY() != map.getSize() - 1;
            default:
                return false;
        }
    }
}
