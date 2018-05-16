package edu.um.cps2002.tile_game;

import java.util.ArrayList;

public class Tile {

    private char type;

    private ArrayList<Player> visitors;

    Tile(char type) {
        this.type = type;
        this.visitors = new ArrayList<Player>();
    }

    char getType(){
        return type;
    }

    void visit(Player p){
        if(!hasVisited(p))
            visitors.add(p);
    }

    boolean hasVisited(Player p){
        return visitors.contains(p);
    }

}
