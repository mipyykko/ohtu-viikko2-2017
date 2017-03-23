/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pyykkomi
 */
public class StatisticsTest {

    Statistics stats;

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Reader readerStub = new Reader() {

            @Override
            public List<Player> getPlayers() {
                ArrayList<Player> players = new ArrayList<Player>();

                players.add(new Player("Semenko", "EDM", 4, 12));
                players.add(new Player("Lemieux", "PIT", 45, 54));
                players.add(new Player("Kurri", "EDM", 37, 53));
                players.add(new Player("Yzerman", "DET", 42, 56));
                players.add(new Player("Gretzky", "EDM", 35, 89));

                return players;
            }
        };
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorTest() {
        try {
            Statistics s = new Statistics(null);
            fail("Luominen onnistui ilman reader-objektia");
        } catch (Exception e) {
            // kaikki hyvin
        }
    }

    @Test
    public void searchTest() {
        assertEquals("Search ei toimi kun pelaaja löytyy", "Kurri", stats.search("Kurri").getName());
        assertEquals("Search ei toimi kun pelaaja löytyy #2", "Kurri", stats.search("rr").getName());
        assertEquals("Search ei toimi kun pelaajaa ei löydy", null, stats.search("Kissa"));
    }
    
    @Test
    public void teamTest() {
        assertEquals("Team ei toimi kun joukkue löytyy", 3, stats.team("EDM").size());
        assertTrue("Team ei toimi kun joukkuetta ei löydy", stats.team("kissa").isEmpty());
    }

    @Test
    public void topscorerTest() {
        assertEquals("Topscorers ei anna oikeaa tulosta", 124, stats.topScorers(1).get(0).getPoints());
        assertTrue("Topscorers ei anna tyhjää listaa negatiivisella luvulla", stats.topScorers(-1).isEmpty());
    }
}
