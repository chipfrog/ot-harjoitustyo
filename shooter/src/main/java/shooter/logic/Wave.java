/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author jajuuso
 */
public class Wave {
    Scene scene;
    int numberOfEnemies;
    ArrayList<Enemy> enemiesList;
    int distance;
    
    public Wave(int numberOfEnemies, int distance, Scene scene) {
        this.numberOfEnemies = numberOfEnemies;
        this.scene = scene;
        this.distance = distance;
        this.enemiesList = createEnemies(numberOfEnemies);
        
    }
    public void spawnEnemies(Pane root) {
        for (Enemy enemy : enemiesList) {
           root.getChildren().add(enemy.getShape());
        }
    }
    private ArrayList<Enemy> createEnemies (int number) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < number; i ++) {
            int rx = 0;
            int ry = 0;
            Random randomDistance = new Random();
            int randomDirection = new Random().nextInt(4);
            if (randomDirection == 0) {
                rx = randomDistance.nextInt((int) scene.getWidth());
                ry = randomDistance.nextInt(distance) * -1;
            }
            if (randomDirection == 1) {
                rx = randomDistance.nextInt((int) scene.getWidth());
                ry = (int) scene.getHeight() + randomDistance.nextInt(distance);
            }
            if (randomDirection == 2) {
                rx = (int) scene.getWidth() + randomDistance.nextInt(distance);
                ry = randomDistance.nextInt((int) scene.getHeight());
            }
            if (randomDirection == 3) {
                rx = randomDistance.nextInt(distance) * -1;
                ry = randomDistance.nextInt((int) scene.getHeight());
            }
            Enemy e = new Enemy(2, rx, ry);
            enemies.add(e);
        }
        return enemies;
    }
    public ArrayList<Enemy> getEnemyList() {
        return this.enemiesList;
    }
    
    
    
    
}
