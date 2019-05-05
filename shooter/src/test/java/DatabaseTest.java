/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import shooter.db.Database;
import shooter.db.PlayerDao;
import shooter.logic.Player;

/**
 *
 * @author jajuuso
 */
public class DatabaseTest {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    File tempFile;
    
    PlayerDao pDao;
    Database database;
    
    @Before
    public void setUp() throws Exception {
        tempFile = tempFolder.newFile("testLeaderboard.db");
        database = new Database("jdbc:sqlite:" + tempFile.getPath());
        database.createDatabase();
        pDao = new PlayerDao(database);
        Player p = new Player("Mario", 30);
        Player p2 = new Player("Luigi", 25);
        Player p3 = new Player("Wario", 28);
        pDao.addPlayer(p);
        pDao.addPlayer(p2);
        pDao.addPlayer(p3);
    }
    @Test
    public void leaderboardIsInDescOrder() {
        ArrayList<Player> list = pDao.list();
        assertTrue(list.get(0).getScore() == 30);
        assertTrue(list.get(1).getScore() == 28);
        assertTrue(list.get(2).getScore() == 25);
    }
    @Test
    public void addsNewPlayer() throws Exception{
        Player p = new Player("Yoshi", 10);
        pDao.addPlayer(p);
        assertTrue(pDao.contains(p) == true);
    }
    @Test
    public void doesNotUpdateScoreIfNewScoreIsLower() throws Exception {
        Player p = new Player("Luigi", 10);
        pDao.updateBestScore(p);
        ArrayList<Player> playerList = pDao.list();
        Player player = new Player("Placeholder", 0);
        for (int i = 0; i < playerList.size(); i ++) {
            if (playerList.get(i).getName().equals("Luigi")) {
                player = playerList.get(i);
            }
        }
        assertTrue(player.getScore() == 25);
    }
    @Test
    public void updatesScoreIfNewScoreHigherThanOld() throws Exception {
        Player p = new Player("Luigi", 100);
        pDao.updateBestScore(p);
        ArrayList<Player> playerList = pDao.list();
        Player player = new Player("Placeholder", 0);
        for (int i = 0; i < playerList.size(); i ++) {
            if (playerList.get(i).getName().equals("Luigi")) {
                player = playerList.get(i);
            }
        }
        assertTrue(player.getScore() == 100);
    }
    @Test
    public void leaderboardIsEmptyAfterReset() throws Exception{
        pDao.resetLeaderboard();
        ArrayList<Player> list = pDao.list();
        assertTrue(list.isEmpty());
    }
    @After
    public void tearDown() {
        tempFile.delete();
    }
    
}
