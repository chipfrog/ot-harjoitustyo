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
import shooter.gameobjects.Enemy;
import shooter.logic.GameLogic;
import shooter.logic.Level;
import shooter.logic.LevelHandler;
import shooter.logic.Player;

/**
 *
 * @author jajuuso
 */
public class GameLogicTest {
    Pane pane;
    Scene scene;
    Level level;
    Player player;
    GameLogic gamelogic;
    
    public GameLogicTest() {
    }
    
    @Before
    public void setUp() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1920, 1080);
        this.player = new Player("John", 0);
        this.gamelogic = new GameLogic(player);
        gamelogic.setScreenAndPane(scene, pane);
        gamelogic.setupClasses();
        
    }
    @Test
    public void stopGameIfPlayerIsDead() {
        player.takeDamage(100);
        assertTrue(gamelogic.stopGame() == true);
    }
    @Test
    public void stopGameIfLevelHasNoEnemiesLeft() {
        ArrayList<Enemy> defeatedEnemies = new ArrayList<>();
        int enemies = gamelogic.getWaves() * gamelogic.getEnemiesInWave();
        for (int i = 0; i < enemies; i ++) {
            defeatedEnemies.add(new Enemy(new ImageView("chick.png"), 1, 1, 1, 1, 1, 1));
        }
        gamelogic.getLevel().defeatEnemies(defeatedEnemies);
        assertTrue(gamelogic.stopGame() == true);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
