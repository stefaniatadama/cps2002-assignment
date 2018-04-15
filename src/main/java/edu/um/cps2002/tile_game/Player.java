package edu.um.cps2002.tile_game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Player {

    private int x, y, startX, startY;
    private PlayerMap playerMapCopy;


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

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    PlayerMap getPlayerMapCopy(){
        return playerMapCopy;
    }

    public boolean moveAllowed(char move){
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

    public void move(char move){
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
                //error
                break;
        }
    }

    public void returnToStart(){
        this.x = startX;
        this.y = startY;
    }

    public void updateMap(int x, int y, char type){
        if(playerMapCopy.getTileType(x,y) == '?')
            playerMapCopy.setTile(x, y, type);
        else if (playerMapCopy.getTileType(x,y) != type)
            System.out.println("hassle");
            //error
    }

    String generateHTML(int playerNo) throws IOException {

        FileReader fr;
        BufferedReader br;

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
