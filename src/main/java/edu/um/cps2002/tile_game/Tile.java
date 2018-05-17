package edu.um.cps2002.tile_game;

import java.util.ArrayList;

/**
 * The {@code Tile} class represents a tile on the map.
 *
 * @author Luke Collins &amp; Stefania Damato
 */
public class Tile {

    /**
     * A {@code char} which could be one of 'g' for grass, 'w' for water
     * or 't' for treasure.
     */
    private char type;

    /**
     * An array of {@link Player}s which have visited the current tile.
     */
    private ArrayList<Player> visitors;

    /**
     * Constructor which sets the {@link Tile} type and initialises the {@link ArrayList}
     * to empty.
     * @param type The type to be set to the tile.
     */
    Tile(char type) {
        this.type = type;
        this.visitors = new ArrayList<Player>();
    }


    /**
     * Simple getter of the type of the tile.
     * @return The type of the tile.
     */
    char getType(){
        return type;
    }

    /**
     * Adds the given player to the arraylist of visitors of the {@link Tile}.
     * @param p The {@link Player} to be added to the visitors of the {@link Tile}.
     */
    void visit(Player p){
        if(!hasVisited(p))
            visitors.add(p);
    }


    /**
     * Checks if the given player is in the arraylist of visitors of the tile.
     * @param p The {@link Player} which has either visited the {@link Tile} or has not.
     * @return {@code true} is the player has visited this tile, {@code false} otherwise.
     */
    boolean hasVisited(Player p){
        return visitors.contains(p);
    }

}
