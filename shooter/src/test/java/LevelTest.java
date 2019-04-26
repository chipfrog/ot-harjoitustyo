/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;
import shooter.gameobjects.Enemy;
import shooter.logic.Level;

/**
 *
 * @author jajuuso
 */
public class LevelTest extends ApplicationTest{
    Level level;
    Scene scene;
    Pane pane;
    
    public LevelTest() {
    }
    
    @Before
    public void setUp() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1920, 1080);
        this.level = new Level(1, 2, 5, 0, scene);
    }
    @Test
    public void levelIsClearedWhenCorrectNumberOfEnemiesKilled() {
        ArrayList<Enemy> killedEnemies = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            Enemy e = new Enemy(new ImageView("chick.png"), 1, 1, 1, 1, 1, 1);
            killedEnemies.add(e);
        }
        level.defeatEnemies(killedEnemies);
        assertTrue(level.cleared() == true);
    }
    @Test
    public void levelNotClearedWhenNotEnoughEnemiesKilled() {
        assertTrue(level.cleared() == false);
    }
    @Test
    public void firstSpawnedWaveIsCorrectSize() {
        assertTrue(level.spawnWave().size() == 5);
    }
//    @Test
//    public void enemynumberIncreasesInTheNextWave() {
//        level.spawnWave();
//        assertTrue(level.spawnWave().size() == 6);
//    }
    @Test
    public void newWaveDoesNotSpawnIfEnoughTimeHasNotPassed() {
        Level level2 = new Level(2, 5, 10, 100, scene);
        level2.spawnWave();
        assertTrue(level2.spawnWave() == null);
    }
    @Test
    public void newWaveDoesNotSpawnIfMAxNumberOfWavesHaveAlreadySpawned() {
        level.spawnWave();
        level.spawnWave();
        assertTrue(level.spawnWave() == null);
    }
    
}
