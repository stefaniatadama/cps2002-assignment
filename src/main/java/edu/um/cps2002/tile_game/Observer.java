package edu.um.cps2002.tile_game;

/**
 * The {@code Observer} class is an abstract class used to implement the
 * observer design pattern.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public abstract class Observer {

    /**
     * The {@code update} function, when implemented, updates the observer
     * entity's information.
     */
    public abstract void update(Map map, int x, int y);
}
