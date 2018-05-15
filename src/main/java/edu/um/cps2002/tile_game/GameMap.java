package edu.um.cps2002.tile_game;

/**
 * The {@code GameMap} class is a {@link Map} object, with
 * the additional method {@link GameMap#generate()} which randomly
 * creates a game map with grass and water tiles, and one treasure tile.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public abstract class GameMap extends Map {

    /**
     * Constructor, creates a map of desired size.
     *
     * @param size The desired size of the map ({@code size} &times; {@code size}).
     */
    GameMap(int size){
        super(size);
    }


    /**
     * This method, when implemented, generates the tiles of the map
     */
    abstract void generate();
}
