package edu.um.cps2002.tile_game;

/**
 * The {@code Player} class contains a copy of the game map from the player's
 * perspective, the location of the player on the map, various methods
 * which allow the player to move around the map and a method to generate
 * the HTML code of the player's map.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class Player {

    /**
     * Player's current row on the game map.
     */
    private int x;


    /**
     * Player's current column on the game map.
     */
    private int y;


    /**
     * Player's initial row on the game map.
     */
    private int startX;


    /**
     * Player's initial column on the game map.
     */
    private int startY;


    /**
     * Player's copy of the game map, with some unknown tiles (set to {@code ?}).
     */
    private PlayerMap playerMapCopy;


    /**
     * The default constructor.
     */
    Player(){

    }


    /**
     * This method populates the {@link Player#playerMapCopy} with {@code ?}
     * characters, apart from the player's initial position which must be a
     * grass ({@code g}) tile.
     *
     * @param mapSize Size of the game map.
     * @param startX Player's initial row on the game map.
     * @param startY Payer's initial column on the game map.
     */
    void setPlayerMapStart(int mapSize, int startX, int startY){

        // Generate Player's Copy of Map
        this.playerMapCopy = new PlayerMap(mapSize);
        this.playerMapCopy.generate();

        // Place player in position
        this.x = startX;
        this.startX = startX;
        this.y = startY;
        this.startY = startY;
        this.playerMapCopy.setTile(startX, startY, 'g');
    }


    /**
     * Simple getter, returns player's current row in the map.
     *
     * @return Player's current row.
     */
    public int getX(){
        return x;
    }


    /**
     * Simple getter, returns player's current column in the map.
     *
     * @return Player's current column.
     */
    public int getY(){
        return y;
    }


    /**
     * Simple getter, returns player's current copy of the map.
     *
     * @return Player's copy of the map, {@link Player#playerMapCopy}.
     */
    PlayerMap getPlayerMapCopy(){
        return playerMapCopy;
    }

    /**
     * This method examines the player's current position and determines whether
     * or not it is possible to move in a certain direction, ensuring that the
     * player does not move out of the map.
     *
     * @param move A direction character, one of {@code u} (up), {@code d} (down),
     *             {@code l} (left) or {@code r} (right).
     *
     * @return {@code true} if the move is admissible, {@code false} otherwise.
     */
    public boolean moveAllowed(char move){

        // Check if player is on the edges of the map
        switch(move){
            case 'u':
                return x != 0;
            case 'd':
                return x != playerMapCopy.getSize() - 1;
            case 'l':
                return y != 0;
            case 'r':
                return y != playerMapCopy.getSize() - 1;
            default:
                return false;
        }
    }


    /**
     * This method moves the player (updates the attributes {@link Player#x} and
     * {@link Player#y}).
     *
     * @param move A direction character, one of {@code u} (up), {@code d} (down),
     *             {@code l} (left) or {@code r} (right).
     *
     * @throws IllegalArgumentException Should the argument {@code move} not equal
     * one of {@code u} (up), {@code d} (down), {@code l} (left) or {@code r}
     * (right).
     */
    public void move(char move) throws IllegalArgumentException{
        switch(move){
            case 'u':
                x--;
                break;
            case 'd':
                x++;
                break;
            case 'l':
                y--;
                break;
            case 'r':
                y++;
                break;
            default:
                throw new IllegalArgumentException("Invalid move encountered (not U/D/L/R)");
        }
    }


    /**
     * This method moves the player back to their starting position.
     */
    public void returnToStart(){
        this.x = startX;
        this.y = startY;
    }


    /**
     * This function updates the player's copy of the map, effectively
     * 'discovering' new tiles and replacing the {@code ?} entries in the
     * {@link Player#playerMapCopy} with one of {@code g} (grass), {@code w}
     * (water), or {@code t} (treasure).
     *
     * @param x The row of the discovered tile.
     * @param y The column of the discovered tile.
     * @param type The discovered tile type, one of {@code g} (grass),
     * {@code w} (water), or {@code t} (treasure).
     *
     * @throws IllegalStateException Should the discovered tile already have
     * been discovered and type conflicts with its previous value.
     */
    public void updateMap(int x, int y, char type) throws IllegalStateException{

        // If the tile was undiscovered, set it to type
        if(playerMapCopy.getTileType(x,y) == '?')
            playerMapCopy.setTile(x, y, type);

        // Otherwise make sure it agrees with what we have
        else if (playerMapCopy.getTileType(x,y) != type)
            throw new IllegalStateException("Player map copy conflicts with game map.");
    }


    /**
     * This method generates an HTML file for the current player, using the
     * {@link PlayerMap#htmlMapTable(int, int)} function to generate an HTML
     * table which represents {@link Player#playerMapCopy}.
     *
     * @param playerNo Which player this is (player 1, player 2, etc.).
     * @return The HTML file as a {@code String}.
     */
    String generateHTML(int playerNo) {

        // HTML Preamble
        String pre = "<!DOCTYPE HTML>\n" +
                "\n" +
                "<!-- Map HTML File for CPS2002: Software Engineering Assignment -->\n" +
                "\n" +
                "<head>\n" +
                "\n" +
                "   <title>CPS2002: Software Engineering &mdash; Tile Game &mdash; Player " + playerNo + "</title>\n" +
                "\n" +
                "   <link href=\"https://fonts.googleapis.com/css?family=Press+Start+2P|Roboto\" rel=\"stylesheet\">\n" +
                "\n" +
                "   <style type=\"text/css\">\n" +
                "      .map{\n" +
                "         position: fixed;\n" +
                "         top: 50%;\n" +
                "         left: 50%;\n" +
                "         margin-top: -37vh;\n" +
                "         margin-left: -37vh;\n" +
                "      }\n" +
                "\n" +
                "      .footer{\n" +
                "         text-align: center;\n" +
                "         width: 600px;\n" +
                "         position: fixed;\n" +
                "         bottom: 5pt;\n" +
                "         left: calc(50% - 300px);\n" +
                "      }\n" +
                "\n" +
                "      a{\n" +
                "         color: black;\n" +
                "         text-decoration: none;\n" +
                "      }\n" +
                "\n" +
                "      a:hover{\n" +
                "         color: #2567d1;\n" +
                "      }\n" +
                "\n" +
                "      body{\n" +
                "         background-color: #EEEEEE;\n" +
                "         font-family: 'Roboto', sans-serif;\n" +
                "      }\n" +
                "\n" +
                "      h1{\n" +
                "         font-size: 20pt;\n" +
                "         font-family: 'Press Start 2P', cursive;\n" +
                "      }\n" +
                "\n" +
                "      table {\n" +
                "         table-layout: fixed;\n" +
                "         border: 1px solid black;\n" +
                "         width: 74vh;\n" +
                "         height: 74vh;\n" +
                "      }\n" +
                "\n" +
                "      td{\n" +
                "         background-color: #BFBFBF;\n" +
                "         text-align: center;\n" +
                "         font-size: "+ (400/playerMapCopy.getSize()) + "px;\n" +
                "      }\n" +
                "\n" +
                "      td.water{\n" +
                "         background-color: #548DD4;\n" +
                "      }\n" +
                "\n" +
                "      td.grass{\n" +
                "         background-color: #21BF1D;\n" +
                "      }\n" +
                "\n" +
                "      td.treasure{\n" +
                "         background-color: #F5F500;\n" +
                "      }\n" +
                "   </style>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "   <h1><a href=\"https://www.um.edu.mt/courses/studyunit/CPS2002\" target=\"_blank\">CPS2002: Software Engineering</a> &mdash; Tile Game &mdash; Player " + playerNo +  "</h1>\n" +
                "\n" +
                "   <div class = \"map\">\n" +
                "      <table>\n";

        // Generate table for player's map

        String table = playerMapCopy.htmlMapTable(x, y);

        // HTML End Stuff
        String post = "</table>\n" +
                "   </div>\n" +
                "\n" +
                "   <div class = \"footer\">Luke Collins and Stefania Damato, B.Sc. Mathematics &amp; Computer Science, 2018</div>\n" +
                "   \n" +
                "</body>";

        return pre + table + post;
    }
}
