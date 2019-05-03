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
import org.testfx.framework.junit.ApplicationTest;
import shooter.gameobjects.Bullet;
import shooter.gameobjects.Enemy;


/**
 *
 * @author jajuuso
 */
public class BulletTest extends ApplicationTest{
    
    Point2D pointA;
    Point2D pointB;
    Bullet bullet;
    int sceneWidth;
    int sceneHeight;
    
    public BulletTest() {
    }
    @Before
    public void setUp() {
        this.pointA = new Point2D(10, 10);
        this.pointB = new Point2D(20, 10);
        this.bullet = new Bullet( new ImageView("fireball.png"), 3, pointA, pointB);
        this.sceneWidth = 600;
        this.sceneHeight = 400;
    }
    @Test
    public void bulletDies() {
        bullet.setDead();
        assertTrue(bullet.isAlive() == false);
    }
    @Test
    public void bulletFliesTowardsTarget() {
        bullet.fly();
    }
    
    
    @Test
    public void bulletDiesIfOutOfSceneWidth() {
        bullet.getShape().relocate(sceneWidth + 300 , 30);
        bullet.outOfGameArea(sceneWidth, sceneHeight);
        assertTrue(bullet.isAlive() == false);
    }
    @Test
    public void bulletDiesIfOutOfSceneHeight() {
        bullet.getShape().relocate(20, sceneHeight + 300);
        bullet.outOfGameArea(sceneWidth, sceneHeight);
        assertTrue(bullet.isAlive() == false);
    }
    @Test
    public void bulletDiesIfOutSceneWidthAndHeight() {
        bullet.getShape().relocate(sceneWidth + 300, sceneHeight + 300);
        bullet.outOfGameArea(sceneWidth, sceneHeight);
        assertTrue(bullet.isAlive() == false);
    }
    @Test
    public void bulletIsAliveInsideSceneArea() {
        bullet.getShape().relocate(30, 30);
        bullet.outOfGameArea(sceneWidth, sceneHeight);
        assertTrue(bullet.isAlive() == true);
    }
    
    
   

    
}
