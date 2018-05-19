package edu.um.cps2002.tile_game;

/**
 * The {@code Team} class is an subclass of the {@link Subject} class and
 * represents teams of players. It is used to implement the observer design
 * pattern, acting as the concrete subject.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class Team extends Subject {

    /**
     * Team number attribute, identifies the team
     */
    int teamNo;

    /**
     * Constructor, invokes the super constructor {@link Subject#Subject()}
     * and assigns a value to {@link Team#teamNo}.
     * @param teamNo The team number.
     */
    Team(int teamNo){
        super();
        this.teamNo = teamNo;
    }

    /**
     * Implements {@link Subject#notifyObservers(Map, int, int)}. Updates the
     * tile on the {@link Map} object in use during the game by calling update
     * on all the observers (the players).
     * @param map Reference to the {@link Map} object in use during the game
     * @param x x-coordinate of tile to mark as visited
     * @param y y-coordinate of tile to mark as visited
     */
    public void notifyObservers(Map map, int x, int y){
        for(int i=0; i < observers.size(); i++)
            observers.get(i).update(map, x, y);
    }

}
