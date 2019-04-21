/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;

import shooter.logic.Enemy;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import shooter.logic.Bullet;
import shooter.logic.ObjectHandler;
import shooter.logic.Wave;

/**
 *
 * @author jajuuso
 */
public class Level {
    int waves;
    int numberOfEnemies;
    double spawnInterval;
    double startTime;
    Scene scene;
    ArrayList<Enemy> enemies;
    boolean noWaiting;
    ObjectHandler handler;
    int waveCounter;
    
    public Level(ArrayList<Enemy> enemies, int waves, double spawnInterval, Scene scene) {
        this.waves = waves;
        this.spawnInterval = spawnInterval;
        this.startTime = 0;
        this.scene = scene;
        this.enemies = enemies;
        this.noWaiting = true;
        this.handler = new ObjectHandler(scene);
        this.waveCounter = 0;
    }
    public void spawnWave(Pane root) {
        if (noWaiting) {
            Wave wave = new Wave(10, 100, scene);
            enemies.addAll(wave.getEnemyList());
            wave.spawnEnemies(root);
            startTime = System.currentTimeMillis();
            noWaiting = false;
            waveCounter ++;
        }
        double testi = (System.currentTimeMillis() - startTime) / 1000;
        if (testi > spawnInterval) {
            Wave wave = new Wave(20, 100, scene);
            enemies.addAll(wave.getEnemyList());
            wave.spawnEnemies(root);
            startTime = System.currentTimeMillis();
            waveCounter ++;
        }
        
    }
    public boolean levelContinues() {
        return (waveCounter > waves);
    }
//    public ArrayList<Enemy> getEnemies() {
//        return this.enemies;
//    }
//    public void update(ArrayList<Bullet> bullets, Pane root) {
//        handler.removeDeadBullets(bullets, root);
//        handler.removeDeadEnemies(allEnemies, root);
//        
//    }
    
    
    
}
