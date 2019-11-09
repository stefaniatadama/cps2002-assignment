package edu.um.cps2002.tile_game;

/**
 * The {@code MapCreator} class creates an appropriate {@link Map}
 * using a given parameter (factory method design pattern).
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class MapCreator {

    private static Map theOnlyMap;

    /**
     * Generates an appropriate map object ({@link HazardousMap} or
     * {@link SafeMap}).
     *
     * @param type A string argument which determines the map type.
     * @param size The size of the map.
     * @return Appropriate map object.
     */
    public synchronized Map createMap(String type, int size){

        if(theOnlyMap != null)
            return theOnlyMap;
        if(type.equals("H"))
            theOnlyMap = new HazardousMap(size);
        if(type.equals("S"))
            theOnlyMap = new SafeMap(size);

        return theOnlyMap;
    }
}