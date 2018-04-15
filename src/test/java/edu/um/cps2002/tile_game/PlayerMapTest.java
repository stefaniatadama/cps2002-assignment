package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * The {@code PlayerMapTest} class tests the functionality of
 * methods in the {@link PlayerMap} class.
 */
public class PlayerMapTest {


    /**
     * Attribute {@code size} was used as a fixed size of
     * the map for the tests.
     */
    int size = 35;


    /**
     * {@link PlayerMap} object used for testing.
     */
    PlayerMap playermap, playermap2;


    /**
     * This method creates a {@link PlayerMap} instance for
     * future tests.
     */
    @Before
    public void setup(){
        playermap = new PlayerMap(size);
    }


    /**
     * Tests {@link Map#setTile(int, int, char)} by ensuring
     * tiles are set to the specified type.
     */
    @Test
    public void testSetTileWater(){
        playermap.generate();

        playermap.setTile(0, 5, 'w');
        assertEquals('w', playermap.getTileType(0,5));
    }


    /**
     * Tests {@link Map#setTile(int, int, char)} with an invalid
     * tile type 'a'. This throws an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void testSetTileInvalid(){
        playermap.generate();
        playermap.setTile(1,6, 'a');
    }


    /**
     * Tests {@link PlayerMap#htmlMapTable(int, int)} by setting
     * the types of a number of tiles and ensuring that the HTML
     * is generated appropriately.
     */
    @Test
    public void testHTMLTable(){
        playermap2 = new PlayerMap(2);
        playermap2.setTile(0,0, 'w');
        playermap2.setTile(1,0, '?');
        playermap2.setTile(0,1, 't');
        playermap2.setTile(1,1, 'g');

        String table = playermap2.htmlMapTable(1, 1);

        assertEquals("<tr>\n" +
                "<td class = water>&ensp;</td>\n" +
                "<td class = treasure>&#x1F3C1;</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>&ensp;</td>\n" +
                "<td class = grass>&#x1F468;</td>\n" +
                "</tr>\n", table);
    }


    /**
     * This method frees memory after all tests are completed.
     */
    @After
    public void teardown(){
        playermap = null;
    }
}
