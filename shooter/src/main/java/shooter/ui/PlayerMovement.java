package shooter.ui;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jajuuso
 */
public class PlayerMovement {
    boolean goUp, goDown, goLeft, goRight, run, clicked;
    Scene scene;
    Point2D playerLocation;
    ImageView imageview;
    ArrayList<Bullet> bullets;
    ArrayList<Point2D> targets;
    Point2D target;
    
    public PlayerMovement(Scene scene, ImageView imageview) {
        this.goUp = false;
        this.goDown = false;
        this.goLeft = false;
        this.goRight = false;
        this.run = false;
        this.scene = scene;
        this.imageview = imageview;
        this.bullets = new ArrayList<>();
        this.targets = new ArrayList<>();
    }
    public void keyCommands() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case W: goUp = true; 
                    break;
                    case S: goDown = true; 
                    break;
                    case A: goLeft = true; 
                    break;
                    case D: goRight = true; 
                    break;
                    case SHIFT: run = true; 
                    break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case W: goUp = false; 
                    break;
                    case S: goDown = false; 
                    break;
                    case A: goLeft = false; 
                    break;
                    case D: goRight = false; 
                    break;
                    case SHIFT: run = false; 
                    break;
                }
            }
        });
    }
    public void movePlayerIcon() {
        int distanceX = 0; 
        int distanceY = 0;
        if (goUp) {
            distanceY -= 1;
        }
        if (goDown) {
            distanceY += 1;
        }
        if (goLeft) {
            distanceX -= 1;
        }
        if (goRight) {
            distanceX += 1;
        }
        if (run) {
            distanceX *= 2;
            distanceY *= 2;
        }
        movePlayerBy(distanceX, distanceY);
    }
    public void movePlayerBy(double distanceX, double distanceY) {
        double x = getPlayerCenterX() + distanceX + imageview.getLayoutX();
        double y = getPlayerCenterY() + distanceY + imageview.getLayoutY();
        movePlayerTo(x, y);
    }
    public void movePlayerTo(double x, double y) {
        double centerX = getPlayerCenterX();
        double centerY = getPlayerCenterY();
        if (x - centerX >= 0 && x + centerX <= scene.getWidth() && y - centerY >= 0 && y + centerY <= scene.getHeight()) {
            imageview.relocate(x - centerX, y - centerY);
        }
    }
    public Point2D getPlayerLocation() {
        this.playerLocation = new Point2D(imageview.getLayoutX() + getPlayerCenterX(), imageview.getLayoutY() + getPlayerCenterY());
        return playerLocation;
    }
    public double getPlayerCenterX() {
        double x = imageview.getBoundsInLocal().getWidth() / 2;
        return x;
    }
    public double getPlayerCenterY() {
        double y = imageview.getBoundsInLocal().getHeight() / 2;
        return y;
    }
    public void mouseControl() {
        
    }
}
