package edu.um.cps2002.tile_game;

import java.util.Arrays;

public class PlayerMap extends Map {

    PlayerMap(int size){
        super(size);
    }

    void generate(){
        tiles = new char[size][size];       // Initialise array

        for(char[] row : tiles)             // All tiles are grass tiles by default
            Arrays.fill(row, '?');
    }

    void setTile(int x, int y, char type){
        switch(type){
            case '?':
            case 'g':
            case 'w':
            case 't':
                tiles[x][y] = type;
                break;

            default:
                //error
                break;
        }
    }

    char getPlayerTileType(int x, int y){
        return tiles[x][y];
    }

    String htmlMapTable(int currentPlayerX, int currentPlayerY){
        String table = "";

        for (int i = 0; i < size; i++){
            table += "<tr>\n";

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
                        //error
                }

                if (i == currentPlayerX && j == currentPlayerY)
                    table += "&#x1F468;";
                else if (tiles[i][j] == 't')
                    table += "&#x1F3C1;";
                else
                    table += "&ensp;";

                table += "</td>\n";
            }

            table += "</tr>\n";
        }

        return table;
    }
}