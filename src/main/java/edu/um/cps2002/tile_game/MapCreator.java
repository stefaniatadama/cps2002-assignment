package edu.um.cps2002.tile_game;

/**
 * The {@code MapCreator} class creates an appropriate {@link GameMap}
 * using a given parameter (factory method design pattern).
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class MapCreator {

    /**
     * Generates an appropriate map object ({@link HazardousMap} or
     * {@link SafeMap}).
     *
     * @param type A string argument which determines the map type
     * @param size The size of the map
     * @return Appropriate map object
     */
    public GameMap createMap(String type, int size){
        if(type.equals("H"))
            return new HazardousMap(size);
        if(type.equals("S"))
            return new SafeMap(size);
        return null;
    }
}