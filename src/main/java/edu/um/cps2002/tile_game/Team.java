package edu.um.cps2002.tile_game;

public class Team extends Subject {

    int teamNo;

    Team(int teamNo){
        super();
        this.teamNo = teamNo;
    }

    public void notifyObservers(Map map, int x, int y){
        for(int i=0; i<observers.size(); i++)
            observers.get(i).update(map, x, y);
    }

}
