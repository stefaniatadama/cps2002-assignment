package edu.um.cps2002.tile_game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PlayerMapTest {

    int size = 35;

    PlayerMap playermap, playermap2;

    @Before
    public void setup(){
        playermap = new PlayerMap(size);
    }

    @Test
    public void testSetTileWater(){
        playermap.generate();

        playermap.setTile(0, 5, 'w');
        assertEquals('w', playermap.getTileType(0,5));
    }

    @Test(expected = IllegalStateException.class)
    public void testSetTileInvalid(){
        playermap.generate();
        playermap.setTile(1,6, 'a');
    }

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

    @After
    public void teardown(){
        playermap = null;
    }
}
