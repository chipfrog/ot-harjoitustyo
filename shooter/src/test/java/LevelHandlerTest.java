/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shooter.logic.Level;
import shooter.logic.LevelHandler;

/**
 *
 * @author jajuuso
 */
public class LevelHandlerTest {
    LevelHandler levelhandler;
    Pane pane;
    Scene scene;
    
    public LevelHandlerTest() {
    }
    @Before
    public void setUp() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1920, 1080);
        this.levelhandler = new LevelHandler(1, 8, 5);
    }
    @Test
    public void firstLevelHasRightNumberOfEnemies() {
        Level level = levelhandler.createNewLevel(scene);
        assertTrue(level.getTotalEnemies() == 8);
    }
    @Test
    public void enemyNumberIncreasesInNextLevel() {
        levelhandler.increaseLevelNumber();
        Level level = levelhandler.createNewLevel(scene);
        assertTrue(level.getTotalEnemies() == 18);
    }
    
}
