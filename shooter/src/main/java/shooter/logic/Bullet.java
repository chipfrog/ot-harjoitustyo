/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jajuuso
 */
public class Bullet extends GameObject{
    Rectangle rectangle;
    Point2D bulletLocation;
    Point2D target;
    Point2D direction;

    public Bullet(double speed, Point2D bulletLocation, Point2D target) {
        super(speed);
        this.rectangle = new Rectangle(10, 10);
        rectangle.setFill(Color.BLUE);
        this.target = target;
        this.bulletLocation = bulletLocation;
    }
    public Rectangle getShape() {
        return this.rectangle;
    }
    public Point2D getBulletLocation() {
        this.bulletLocation = new Point2D(rectangle.getLayoutX(), rectangle.getLayoutY());
        return bulletLocation;
    }
    public void fly() {
        double angle = Math.atan2(target.getX() - bulletLocation.getX(), target.getY() - bulletLocation.getY());
        double xV = getSpeed() * Math.sin(angle);
        double yV = getSpeed() * Math.cos(angle);
        getShape().relocate(getShape().getLayoutX() + xV, getShape().getLayoutY() + yV);
    }
    public void outOfGameArea(int width, int height) {
        if(getShape().getLayoutX() > width || getShape().getLayoutY() > height) {
            setDead();
        }
    }
    
}
