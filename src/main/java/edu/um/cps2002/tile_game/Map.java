package edu.um.cps2002.tile_game;

/**
 * The {@code Map} class contains a two-dimensional array of {@link Tile}s
 * and some simple methods that operate on the map.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public abstract class Map {

    /**
     * The dimension of the tiles array, that is, the array {@link Map#tiles}
     * has size {@code size} &times; {@code size}.
     */
    int size;


    /**
     * The array of {@link Tile}s.
     */
    Tile[][] tiles;


    /**
     * Constructor which initialises the array and sets the attribute {@link Map#size}
     * to the parameter {@code size}.
     * @param size The desired size of the map ({@code size} &times; {@code size}).
     */
    public Map(int size){
        this.size = size;
        this.tiles = new Tile[size][size];
    }


    /**
     * This method is to be implemented by any subclass of {@link Map} and in each case,
     * should populate the {@link Map#tiles} array in some fashion.
     */
    abstract void generate();

    /**
     * Simple getter for the {@link Map#size} attribute.
     * @return size - The dimension of the {@link Map#tiles} {@code char} array.
     */
    final public int getSize(){
        return size;
    }


    /**
     * Simple getter for individual tiles in the {@link Map#tiles} array.
     * @param x Row in the {@link Map#tiles} array.
     * @param y Column in the {@link Map#tiles} array.
     * @return The character corresponding to the {@link Tile} in the
     * {@code x}th row and {@code y}th column of the {@link Map#tiles} array.
     */
    final public char getTileType(int x, int y){
        return tiles[x][y].getType();
    }


    /**
     * Simple setter for individual tiles in the {@link Map#tiles} array.
     *
     * <p>
     *     The character in the {@code x}th row and {@code y}th column of the
     *     {@link Map#tiles} array is set to the parameter {@code type}, as long as
     *     it is equal to one of {@code g} (grass), {@code w} (water), {@code t}
     *     (treasure) or {@code ?} (undiscovered).
     * </p>
     * @param x Row in the {@link Map#tiles} array.
     * @param y Column in the {@link Map#tiles} array.
     * @param type One of {@code g}, {@code w}, {@code t}, or {@code ?}.
     * @throws IllegalStateException Should the parameter {@code type} not be one of
     * {@code g}, {@code w}, {@code t}, or {@code ?}.
     */
    final public void setTile(int x, int y, char type) throws IllegalStateException{
        switch(type){
            case 'g':
            case 'w':
            case 't':
                tiles[x][y] = new Tile(type);
                break;

            default:
                throw new IllegalStateException("Invalid tile type encountered.");
        }
    }


    /**
     * The tile with coordinates {@code x}, {@code y} is visited by the given player.
     * @param p The player to visit the tile.
     * @param x x-coordinate of the tile to be visited.
     * @param y y-coordinate of the tile to be visited.
     */
    final public void playerVisitTile(Player p, int x, int y){
        tiles[x][y].visit(p);
    }

    /**
     * This method generates an HTML file for the current player, using the
     * {@link Map#htmlMapTable(Player)} function.
     *
     * @param p The {@link Player} for whom we generate the HTML.
     * @param playerNo Which player this is (player 1, player 2, etc.).
     * @return The HTML file as a {@code String}.
     */
    String generateHTML(Player p, int playerNo) {

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
                "         font-size: "+ (400/size) + "px;\n" +
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
        String table = htmlMapTable(p);

        // HTML closing tags
        String post = "</table>\n" +
                "   </div>\n" +
                "\n" +
                "   <div class = \"footer\">Luke Collins and Stefania Damato, B.Sc. Mathematics &amp; Computer Science, 2018</div>\n" +
                "   \n" +
                "</body>";

        return pre + table + post;
    }



    /**
     * This generates an HTML table representation of the map, placing the unicode character
     * &#x1F468; where the player is, unless they are on the treasure in which case
     * the character &#x1F3C1; is placed instead.
     *
     * @param currentPlayer The player whose map we will be displaying.
     * @return A {@code String} containing an HTML table representation of the map.
     */
    String htmlMapTable(Player currentPlayer){
        String table = "";

        // Table Row
        for (int i = 0; i < size; i++){
            table += "<tr>\n";

            //Table Entry
            for (int j = 0; j < size; j++) {
                table += "<td";

                if(!tiles[i][j].hasVisited(currentPlayer))
                    table += ">";

                else {

                    switch (tiles[i][j].getType()) {
                        case 'g':
                            table += " class = grass>";
                            break;
                        case 'w':
                            table += " class = water>";
                            break;
                        case 't':
                            table += " class = treasure>";
                            break;
                        default:
                            throw new IllegalStateException("Invalid tile type encountered.");
                    }
                }

                if (tiles[i][j].getType() == 't' && tiles[i][j].hasVisited(currentPlayer))
                    table += "&#x1F3C1;";
                else if (i == currentPlayer.getX() && j == currentPlayer.getY())
                    table += "&#x1F468;";
                else
                    table += "&ensp;";


                table += "</td>\n";
            }

            table += "</tr>\n";
        }

        return table;
    }
}
