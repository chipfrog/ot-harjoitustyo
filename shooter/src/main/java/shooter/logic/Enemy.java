/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import shooter.logic.Bullet;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shooter.logic.GameObject;
import shooter.logic.Player;


/**
 *
 * @author jajuuso
 */
public class Enemy extends GameObject{
    Point2D location;
    Point2D direction;
    Rectangle rectangle;
    
    public Enemy(double speed, double x, double y) {
        super(speed);
        this.location = new Point2D(x, y);
        this.rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.GREEN);
    }
    public void chasePlayer(Point2D playerLocation) {
        direction = playerLocation.subtract(location).normalize();
        location = location.add(direction.multiply(getSpeed()));
        rectangle.relocate(location.getX(), location.getY());
    }
    public Rectangle getShape() {
        return this.rectangle;
    }
    public void playerIsHit(Player p) {
        if (p.invincibilityOff() == true && rectangle.getBoundsInParent().intersects(p.getImageView().getBoundsInParent())) {
            p.takeDamage();
        }
    }
    public boolean isHit(Bullet bullet) {
        if (rectangle.getBoundsInParent().intersects(bullet.getShape().getBoundsInParent())) {
            setDead();
            bullet.setDead();
            return true;
        }
        return false;
    }
    
    
    
    
}
