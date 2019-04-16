/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jajuuso
 */
public class Bullet {
    Rectangle rectangle;
    Point2D bulletLocation;
    Point2D target;
    Point2D direction;
    boolean alive;
    
    public Bullet(Point2D bulletLocation, Point2D target) {
        this.rectangle = new Rectangle(10, 10);
        rectangle.setFill(Color.BLUE);
        this.target = target;
        this.bulletLocation = bulletLocation;
        this.alive = true;
    }
    public Rectangle getShape() {
        return this.rectangle;
    }
    public Point2D getBulletLocation() {
        this.bulletLocation = new Point2D(rectangle.getLayoutX(), rectangle.getLayoutY());
        return bulletLocation;
    }
    public void setDead() {
        this.alive = false;
    }
}
