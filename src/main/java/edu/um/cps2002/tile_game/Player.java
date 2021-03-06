package edu.um.cps2002.tile_game;

/**
 * The {@code Player} class contains methods that have to do with the
 * player's position in the map.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class Player extends Observer {

    /**
     * Player's current row on the game map.
     */
    private int x;


    /**
     * Player's current column on the game map.
     */
    private int y;

    /**
     * Player's team number.
     */
    private int playerTeamNo;

    /**
     * Player's initial row on the game map.
     */
    private int startX;


    /**
     * Player's initial column on the game map.
     */
    private int startY;


    /**
     * The constructor for {@link Player}, which takes the player's initial
     * position.
     * @param x The player's initial x-coordinate.
     * @param y The player's initial y-coordinate.
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
     * {@code l} (left) or {@code r} (right).
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

    /**
     * Simple setter, assigns player's team number
     * @param teamNo Team number
     */
     public void setTeamNo(int teamNo){
         this.playerTeamNo = teamNo;
     }

    /**
     * Simple getter, returns player's team number
     * @return The player's team number
     */
     public int getTeamNo(){
         return playerTeamNo;
     }

    /**
     * Updater function for observer design pattern.
     * This function updates the map by marking the tile at [x][y] as 'visited'
     * by this player.
     * @param map The {@link Map} instance in use in the game to be updated
     * @param x The players' x-coordinate
     * @param y The players' y-coordinate
     */
     public void update(Map map, int x, int y){
        map.playerVisitTile(this, x, y);
     }
}
