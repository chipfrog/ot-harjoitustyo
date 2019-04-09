package ui;

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
    boolean goUp, goDown, goLeft, goRight, run;
    Scene scene;
    Point2D playerLocation;
    ImageView imageview;
    
    
    public PlayerMovement (Scene scene, ImageView imageview) {
        this.goUp = false;
        this.goDown = false;
        this.goLeft = false;
        this.goRight = false;
        this.run = false;
        this.scene = scene;
        this.imageview = imageview;
        
    }
    public void keyCommands() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case W: goUp = true; break;
                    case S: goDown = true; break;
                    case A: goLeft = true; break;
                    case D: goRight = true; break;
                    case SHIFT: run = true; break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case W: goUp = false; break;
                    case S: goDown = false; break;
                    case A: goLeft = false; break;
                    case D: goRight = false; break;
                    case SHIFT: run = false; break;
                }
            }
            
        });
    }
    public void movePlayerIcon() {
        int distanceX = 0; int distanceY = 0;
        if (goUp) distanceY -= 1;
        if (goDown) distanceY += 1;
        if (goLeft) distanceX -= 1;
        if (goRight) distanceX += 1;
        if (run) {
            distanceX *= 2;
            distanceY *= 2;
        }
        movePlayerBy(distanceX, distanceY);
    }
    public void movePlayerBy(double distanceX, double distanceY) {
        double centerX = imageview.getBoundsInLocal().getWidth() / 2;
        double centerY = imageview.getBoundsInLocal().getHeight() / 2;
        double x = centerX + distanceX + imageview.getLayoutX();
        double y = centerY + distanceY + imageview.getLayoutY();
        movePlayerTo(x, y);
    }
    public void movePlayerTo(double x, double y) {
        double centerX = imageview.getBoundsInLocal().getWidth() / 2;
        double centerY = imageview.getBoundsInLocal().getHeight() / 2;
        if (x - centerX >= 0 && x + centerX <= scene.getWidth() && y - centerY >= 0 && y + centerY <= scene.getHeight()) {
            imageview.relocate(x - centerX, y - centerY);
        }
    }
    public Point2D getPlayerLocation() {
        this.playerLocation = new Point2D(imageview.getLayoutX() + imageview.getBoundsInLocal().getWidth()/2, 
                imageview.getLayoutY() + imageview.getBoundsInLocal().getHeight() /2);
        return playerLocation;
    }
    
}
