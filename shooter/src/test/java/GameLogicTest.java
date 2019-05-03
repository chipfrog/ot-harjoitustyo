/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shooter.gameobjects.Bullet;
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
    @Test
    public void deadEnemyRemoveWorks() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
           enemies.add(new Enemy(new ImageView("chick.png"), 1, 1, 1, 1, 1, 1)); 
        }
        Enemy deadEnemy1 = new Enemy(new ImageView("chick.png"), 1, 1, 1, 1, 1, 1);
        deadEnemy1.setDead();
        enemies.add(deadEnemy1);
        
        assertTrue(enemies.size() == 6);
        gamelogic.removeDeadEnemies(enemies, pane);
        assertTrue(enemies.size() == 5);
    }
    @Test
    public void deadBulletRemoveWorks() {
        ArrayList<Bullet> bullets = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            bullets.add(new Bullet(new ImageView("fireball.png"), 2, Point2D.ZERO, Point2D.ZERO));
        }
        Bullet deadBullet = new Bullet(new ImageView("fireball.png"), 2, Point2D.ZERO, Point2D.ZERO);
        deadBullet.setDead();
        bullets.add(deadBullet);
        
        assertTrue(bullets.size() == 6);
        gamelogic.removeDeadBullets(bullets, pane);
        assertTrue(bullets.size() == 5);
    }
    @Test
    public void addingAssetsToRightLocationInPaneWorks() {
        gamelogic.addAssetsToPane();
        assertTrue(player.getImageView().getLayoutX() == 600 && player.getImageView().getLayoutY() == 300);
    }
    @Test
    public void hitDetectionWorks() {
        Enemy enemyTouch = new Enemy(new ImageView("chick.png"), 2, 2, 2, 2, 10, 10);
        Bullet bullet = new Bullet(new ImageView("fireball.png"), 2, new Point2D(10, 10), new Point2D(20, 20));
        Enemy enemyNotTouch = new Enemy(new ImageView("chick.png"), 2, 2, 2, 2, 100, 100);
        pane.getChildren().addAll(enemyTouch.getImage(), enemyNotTouch.getImage(), bullet.getShape());
        bullet.fly();
        enemyNotTouch.chasePlayer(Point2D.ZERO);
        assertTrue(enemyTouch.hitDetection(bullet.getImage()) == true);
        assertTrue(enemyNotTouch.hitDetection(bullet.getImage()) == false);
    }

   
}
