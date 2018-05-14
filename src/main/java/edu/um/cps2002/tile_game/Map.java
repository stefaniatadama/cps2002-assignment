package edu.um.cps2002.tile_game;

/**
 * The {@code Map} class contains a two-dimensional array of {@code char}
 * entries referred to as <i>tiles</i> and some simple methods common to both
 * the map used by the {@link Game} class and the individual {@link Player}s.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public abstract class Map {

    /**
     * The dimension of the tiles array, that is, the array {@link Map#tiles}
     * has size {@code size} &times; {@code size}.
     */
    int size;

    /**
     * The {@code char} array of tiles.
     */
    char[][] tiles;

    /**
     * Constructor which initialises the array and sets the attribute {@link Map#size}
     * to the parameter {@code size}.
     * @param size The desired size of the map ({@code size} &times; {@code size}).
     */
    public Map(int size){
        this.size = size;
        this.tiles = new char[size][size];
    }

    /**
     * This method is to be implemented by any subclass of {@link Map} and in each case,
     * should populate the {@link Map#tiles} array in some fashion.
     */
    abstract void generate();

    /**
     * Simple getter for the {@link Map#size} attribute.
     * @return size - The dimension of the {@link Map#tiles} {@code char} array.
     */
    final public int getSize(){
        return size;
    }

    /**
     * Simple getter for individual tiles in the {@link Map#tiles} array.
     * @param x Row in the {@link Map#tiles} array.
     * @param y Column in the {@link Map#tiles} array.
     * @return The character in the {@code x}th row and {@code y}th column of the
     * {@link Map#tiles} array.
     */
    final public char getTileType(int x, int y){
        return tiles[x][y];
    }

    /**
     * Simple setter for individual tiles in the {@link Map#tiles} array.
     *
     * <p>
     *     The character in the {@code x}th row and {@code y}th column of the
     *     {@link Map#tiles} array is set to the parameter {@code type}, as long as
     *     it is equal to one of {@code g} (grass), {@code w} (water), {@code t}
     *     (treasure) or {@code ?} (undiscovered).
     * </p>
     * @param x Row in the {@link Map#tiles} array.
     * @param y Column in the {@link Map#tiles} array.
     * @param type One of {@code g}, {@code w}, {@code t}, or {@code ?}.
     * @throws IllegalStateException Should the parameter {@code type} not be one of
     * {@code g}, {@code w}, {@code t}, or {@code ?}.
     */
    final public void setTile(int x, int y, char type) throws IllegalStateException{
        switch(type){
            case '?':
            case 'g':
            case 'w':
            case 't':
                tiles[x][y] = type;
                break;

            default:
                throw new IllegalStateException("Invalid tile type encountered.");
        }
    }
}
