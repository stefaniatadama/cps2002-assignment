package edu.um.cps2002.tile_game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {

    private int turns;
    private Player[] players;
    private Map map;

    Scanner sc;

    public void start(){

        sc = new Scanner(System.in);
        int numPlayers = 0, mapSize = 0;
        boolean valid = false, firstLoop = true;

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

        // Initialise and Generate Map
        map = new GameMap(mapSize);
        map.generate();

        // Initialise Players
        players = new Player[numPlayers];

        for(int i = 0; i < numPlayers; i++) {
            players[i] = new Player();

            // Give player a start position, grass tile
            int x = (int) (Math.random() * mapSize);
            int y = (int) (Math.random() * mapSize);

            while (map.getTileType(x, y) != 'g') {
                x = (int) (Math.random() * mapSize);
                y = (int) (Math.random() * mapSize);
            }

            players[i].setPlayerMapStart(mapSize, x, y);
        }
    }

    public GameMap getMap(){
        return (GameMap) map;
    }

    public void gameplay(){

    }

    public Player getPlayer(int playerNum){
        return players[playerNum];
    }


    boolean setPlayersMap(int numPlayers, int mapSize){

        return (((numPlayers >= 2) && (numPlayers <= 4) && (mapSize >= 5))  ||
                ((numPlayers >= 5) && (numPlayers <= 8) && (mapSize >= 8))) &&
                (mapSize <= 50);
    }


    void generateHTMLFiles() throws IOException {

        FileWriter fw;
        BufferedWriter bw;

        for (int i = 0; i < players.length; i++) {
            fw = new FileWriter("map_player_" + (i+1) + ".html");
            bw = new BufferedWriter(fw);

            bw.write(players[i].generateHTML());
            bw.close();
        }
    }
}
