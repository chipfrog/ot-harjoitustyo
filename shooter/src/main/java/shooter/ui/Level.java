/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;

import shooter.logic.Enemy;
import java.util.ArrayList;
import javafx.scene.Scene;
import shooter.logic.Wave;

/**
 *
 * @author jajuuso
 */
public class Level {
    boolean cleared;
    int waves;
    int totalEnemies;
    int enemiesInWave;
    double spawnInterval;
    double startTime;
    Scene scene;
    ArrayList<Enemy> enemies;
    boolean firstWave;
    int waveCounter;
    
    public Level(int waves, int enemiesInWave, double spawnInterval, Scene scene) {
        this.waves = waves;
        this.spawnInterval = spawnInterval;
        this.startTime = 0;
        this.scene = scene;
        this.enemies = new ArrayList<>();
        this.firstWave = true;
        this.enemiesInWave = enemiesInWave;
        this.waveCounter = 0;
        this.totalEnemies = waves * enemiesInWave;
        this.cleared = false;
    }
    public ArrayList<Enemy> spawnWave() {
        if (firstWave) {
            Wave wave = new Wave(enemiesInWave, 100, scene);
            startTime = System.currentTimeMillis();
            firstWave = false;
            waveCounter ++;
            enemies.addAll(wave.getEnemyList());
            return wave.getEnemyList();
        }
        double timeDiff = (System.currentTimeMillis() - startTime) / 1000;
        if (timeDiff > spawnInterval && waveCounter < waves) {
            startTime = System.currentTimeMillis();
            waveCounter ++;
            Wave wave = new Wave(enemiesInWave, 100, scene);
            enemies.addAll(wave.getEnemyList());
            return wave.getEnemyList();
        }
        return null;
    }
    public void defeatEnemies(ArrayList<Enemy> defeatedEnemies) {
        totalEnemies -= defeatedEnemies.size();
    }
    public boolean cleared() {
        if (totalEnemies == 0) {
            cleared = true;
            return true;
        }
        return false;
    }
}
