package edu.um.cps2002.tile_game;

import java.util.ArrayList;

/**
 * The {@code Subject} class is an abstract class used to implement the
 * observer design pattern.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public abstract class Subject {

    /**
     * {@link ArrayList} of 'observers' which the subject will inform of any
     * changes which concern them.
     */
    protected ArrayList<Observer> observers;

    /**
     * Simple constructor, initialises the {@code observers} ArrayList.
     */
    Subject(){
        this.observers = new ArrayList<Observer>();
    }

    /**
     * Adds a new observer to the {@code observers} ArrayList.
     * @param o The new observer
     */
    public void attachObserver(Observer o){
        observers.add(o);
    }

    /**
     * When implemented, this method will notify all the respective observers
     * of any changes the subject wishes to announce.
     */
    public abstract void notifyObservers(Map map, int x, int y);
}
