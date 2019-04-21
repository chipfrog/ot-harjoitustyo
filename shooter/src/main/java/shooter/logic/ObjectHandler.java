/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 *
 * @author jajuuso
 */
public class ObjectHandler {
    Scene scene;
    
    public ObjectHandler(Scene scene) {
        this.scene = scene;
    }
    public void removeDeadBullets(ArrayList<Bullet> list, Pane root) {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : list) {
            if (bullet.isAlive() == false) {
                root.getChildren().remove(bullet.getShape());
                bulletsToRemove.add(bullet);
            }
        }
        list.removeAll(bulletsToRemove);
    }
    public void removeDeadEnemies(ArrayList<Enemy> list, Pane root) {
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy enemy : list) {
            if (enemy.isAlive() == false) {
                root.getChildren().remove(enemy.getShape());
                enemiesToRemove.add(enemy);
            }
        }
        list.removeAll(enemiesToRemove);
    }
    public void shootBullets(ArrayList<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            bullet.fly();
            bullet.outOfGameArea((int) scene.getWidth(),(int) scene.getHeight());
        }
    }
    
}