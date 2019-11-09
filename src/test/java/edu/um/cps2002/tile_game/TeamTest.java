package edu.um.cps2002.tile_game;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * The {@code TeamTest} class tests the functionality of
 * methods in the {@link Team} class.
 *
 * @author Luke Collins &amp; Stefania Damato
 *
 */
public class TeamTest {

    /**
     * {@link Team} instance used for testing.
     */
    Team team;

    /**
     * Initialises the {@link Team} instance for use in tests.
     */
    @Before
    public void setup(){
        team = new Team(1);
    }

    /**
     * Tests the function {@link Team#notifyObservers(Map, int, int)}
     */
    @Test
    public void testNotifyObservers(){

        // Map
        HazardousMap map = new HazardousMap(25);
        map.generate();

        // Players
        Player player1 = new Player(1, 1);
        player1.setTeamNo(1);
        Player player2 = new Player(2, 2);
        player2.setTeamNo(1);

        // Attach to team
        team.attachObserver(player1);
        team.attachObserver(player2);

        // Notify team members of tile (3,3)
        team.notifyObservers(map, 3, 3);

        // Check that players have visited
        assertTrue(map.tiles[3][3].hasVisited(player1));
        assertTrue(map.tiles[3][3].hasVisited(player2));
    }
}
