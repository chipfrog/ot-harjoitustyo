/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shooter.logic.Player;



/**
 *
 * @author jajuuso
 */
public class EnemyMovement {
    double speed;
    Point2D enemyLocation;
    Point2D direction;
    Rectangle rectangle;
    
    public EnemyMovement(double x, double y) {
        this.speed = 0.3;
        this.enemyLocation = new Point2D(x, y);
        this.rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.GREEN);
        rectangle.relocate(x, y);
        
    }
    public void chasePlayer(Point2D playerLocation) {
        direction = playerLocation.subtract(enemyLocation).normalize();
        enemyLocation = enemyLocation.add(direction.multiply(speed));
        rectangle.relocate(enemyLocation.getX(), enemyLocation.getY());
        
    }
    public Rectangle getShape() {
        return this.rectangle;
    }
    public boolean playerIsHit(Player p, ImageView imageview) {
        if (p.invincibility() == true && rectangle.getBoundsInParent().intersects(imageview.getBoundsInParent())) {
            p.takeDamage();
            return true;
        }
        return false;
    }
    
    
    
}
