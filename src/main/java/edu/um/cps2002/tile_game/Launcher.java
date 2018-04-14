package edu.um.cps2002.tile_game;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {

        // Game Instance
        Game g = new Game();

        System.out.println("---------------------------------------------");
        System.out.println(" CPS2002: Software Engineering - Tile Game");
        System.out.println("---------------------------------------------\n");

        // Start game (ask user for details)
        g.start();
        g.generateHTMLFiles();
    }
}
