package edu.um.cps2002.tile_game;

import java.util.Scanner;

public class Game {

    private int turns;
    private Player[] players;
    private Map map;

    Scanner sc = new Scanner(System.in);

    void main(String[] args){
        startGame();
    }

    void startGame(){
        System.out.println("---------------------------------------------");
        System.out.println(" CPS2002: Software Engineering - Tile Game");
        System.out.println("---------------------------------------------\n\n");

        int numPlayers = 0, mapSize = 0;
        boolean valid = false, firstLoop = true;

        while(!valid){
            if(!firstLoop)
                System.out.println("Incorrect number of players or map size entered. Please try again.");

            System.out.print("Enter number of players (2-8): ");
            numPlayers = sc.nextInt();

            System.out.print("Enter map size (" + ((numPlayers <= 4) ? 5 : 8) + "-50): ");
            mapSize = sc.nextInt();

            valid = setPlayersMap(numPlayers, mapSize);
            firstLoop = false;
        }

        // Initialise and Generate Map
        map = new Map(mapSize);
        map.generate();

        // Initialise Players
        players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++)
            players[i] = new Player();

    }

    boolean setPlayersMap(int numPlayers, int mapSize){
        return (((numPlayers >= 2) && (numPlayers <= 4) && (mapSize >= 5))  ||
                ((numPlayers >= 5) && (numPlayers <= 8) && (mapSize >= 8))) &&
                (mapSize <= 50);
    }

    void generateHTMLFiles(){

    }
}
