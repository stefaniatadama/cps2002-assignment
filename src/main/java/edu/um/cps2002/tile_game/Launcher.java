package edu.um.cps2002.tile_game;

import java.io.IOException;

/**
 * The {@code Launcher} class contains the main method, which creates an
 * instance of {@link Game} and loops through gameplay rounds until someone
 * wins.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class Launcher {

    /**
     * The default constructor.
     */
    Launcher(){

    }


    /**
     * The main method creates a {@link Game} instance, runs {@link Game#start()}
     * and loops through {@link Game#gameplayRound()}, each time executing
     * {@link Game#generateHTMLFiles()} to update the maps for each player.
     *
     * @param args Not used
     * @throws IOException Should writing of HTML files fail.
     */
    public static void main(String[] args) throws IOException {

        // Game Instance
        Game g = new Game();

        System.out.println("---------------------------------------------");
        System.out.println(" CPS2002: Software Engineering - Tile Game");
        System.out.println("---------------------------------------------\n");

        // Start game (ask user for details)
        g.start();

        // Generate first map
        g.generateHTMLFiles();

        // Gameplay loop
        while(g.gameplayRound()){
            g.generateHTMLFiles();
        }

        // Generate final map
        g.generateHTMLFiles();
    }
}
