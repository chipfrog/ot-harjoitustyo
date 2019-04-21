/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.geometry.Point2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shooter.logic.Bullet;

/**
 *
 * @author jajuuso
 */
public class BulletTest {
    
    Point2D pointA;
    Point2D pointB;
    Bullet bullet;
    
    public BulletTest() {
    }
    
    @Before
    public void setUp() {
        this.pointA = new Point2D(10, 10);
        this.pointB = new Point2D(20, 20);
        this.bullet = new Bullet(3, pointA, pointB);
    }
    @Test
    public void bulletDies() {
        bullet.setDead();
        assertTrue(bullet.isAlive() == false);
    }
    
   

    
}
