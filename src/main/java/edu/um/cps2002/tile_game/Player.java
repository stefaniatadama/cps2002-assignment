package edu.um.cps2002.tile_game;

/**
 * The {@code Player} class contains a copy of the game map from the player's
 * perspective, the location of the player on the map, various methods
 * which allow the player to move around the map and a method to generate
 * the HTML code of the player's map.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class Player {

    /**
     * Player's current row on the game map.
     */
    private int x;


    /**
     * Player's current column on the game map.
     */
    private int y;


    /**
     * Player's initial row on the game map.
     */
    private int startX;


    /**
     * Player's initial column on the game map.
     */
    private int startY;


    /**
     * The default constructor.
     */
    Player(int x, int y){
        this.startX = x;
        this.startY = y;
        this.x = x;
        this.y = y;
    }


    /**
     * Simple getter, returns player's current row in the map.
     *
     * @return Player's current row.
     */
    public int getX(){
        return x;
    }


    /**
     * Simple getter, returns player's current column in the map.
     *
     * @return Player's current column.
     */
    public int getY(){
        return y;
    }



    /**
     * This method moves the player (updates the attributes {@link Player#x} and
     * {@link Player#y}).
     *
     * @param move A direction character, one of {@code u} (up), {@code d} (down),
     *             {@code l} (left) or {@code r} (right).
     *
     * @throws IllegalArgumentException Should the argument {@code move} not equal
     * one of {@code u} (up), {@code d} (down), {@code l} (left) or {@code r}
     * (right).
     */
     public void move(char move) throws IllegalArgumentException{
         switch(move){
             case 'u':
                 x--;
                 break;
             case 'd':
                 x++;
                 break;
             case 'l':
                 y--;
                 break;
             case 'r':
                 y++;
                 break;
             default:
                 throw new IllegalArgumentException("Invalid move encountered (not U/D/L/R)");
         }
     }


     /**
      * This method moves the player back to their starting position.
      */
     public void returnToStart(){
         this.x = startX;
         this.y = startY;
     }




}
