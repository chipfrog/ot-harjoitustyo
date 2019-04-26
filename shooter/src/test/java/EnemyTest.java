/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shooter.gameobjects.Enemy;

/**
 *
 * @author jajuuso
 */
public class EnemyTest {
    Enemy enemy;
    
    public EnemyTest() {
    }
    @Before
    public void setUp() {
        this.enemy = new Enemy(new ImageView("chick.png"), 2, 3, 5, 5, 0, 20);
    }
    @Test
    public void enemyTakesDamage() {
        enemy.damageHealth();
        assertTrue(enemy.getHp() == 2);
    }
    @Test
    public void enemyDies() {
        enemy.damageHealth();
        enemy.damageHealth();
        enemy.damageHealth();
        assertTrue(enemy.isAlive() == false);
    }
    @Test
    public void enemyMovesTowardsGivenLocation() {
        Point2D targetLocation = new Point2D(10, 20);
        enemy.chasePlayer(targetLocation);
        enemy.chasePlayer(targetLocation);
        assertTrue(enemy.getLocation().getX() == 4 && enemy.getLocation().getY() == 20);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
