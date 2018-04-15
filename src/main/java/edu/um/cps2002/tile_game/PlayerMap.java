package edu.um.cps2002.tile_game;

import java.util.Arrays;

/**
 * The {@code PlayerMap} class implements a {@link Map} object, with
 * the additional methods {@link PlayerMap#generate()} which populates
 * the map with undiscovered ({@code ?}) characters and
 * {@link PlayerMap#htmlMapTable(int, int)} which generates an HTML
 * table representation of the map.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class PlayerMap extends Map {

    /**
     * Constructor, creates a map of desired size.
     *
     * @param size The desired size of the map ({@code size} &times; {@code size}).
     */
    PlayerMap(int size){
        super(size);
    }

    /**
     * Sets each entry of the {@link PlayerMap#tiles} array as {@code ?} (unknown).
     */
    void generate(){
        for(char[] row : tiles)             // All tiles are undiscovered (?)
            Arrays.fill(row, '?');
    }

    /**
     * This generates an HTML table representation of the map, placing the unicode character
     * &#x1F468; where the player is, unless they are on the treasure in which case
     * the character &#x1F3C1; is placed instead.
     *
     * @param currentPlayerX Current row of the player in {@link PlayerMap#tiles} array.
     * @param currentPlayerY Current column of the player in {@link PlayerMap#tiles} array.
     * @return A {@code String} containing an HTML table representation of the map.
     */
    String htmlMapTable(int currentPlayerX, int currentPlayerY){
        String table = "";

        // Table Row
        for (int i = 0; i < size; i++){
            table += "<tr>\n";

            //Table Entry
            for (int j = 0; j < size; j++) {
                table += "<td";

                switch (tiles[i][j]) {
                    case '?':
                        table += ">";
                        break;
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
                if (tiles[i][j] == 't')
                    table += "&#x1F3C1;";
                else if (i == currentPlayerX && j == currentPlayerY)
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