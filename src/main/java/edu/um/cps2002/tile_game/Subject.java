package edu.um.cps2002.tile_game;

import java.util.ArrayList;

public abstract class Subject {

    protected ArrayList<Observer> observers;

    Subject(){
        this.observers = new ArrayList<Observer>();
    }

    public void attachObserver(Observer o){
        observers.add(o);
    }

    public abstract void notifyObservers(Map map, int x, int y);
}
