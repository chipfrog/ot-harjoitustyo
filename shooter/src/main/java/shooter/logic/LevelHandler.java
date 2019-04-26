/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import javafx.scene.Scene;

/**
 *
 * @author jajuuso
 */
public class LevelHandler {
    int waves;
    int enemiesInWave;
    double spawnInterval;
    int levelNumber;
    
    
    public LevelHandler(int waves, int enemiesInWave, double spawnInterval) {
        this.waves = waves;
        this.enemiesInWave = enemiesInWave;
        this.spawnInterval = spawnInterval;
        this.levelNumber = 1;
    }
    public Level createNewLevel(Scene scene) {
        if (levelNumber == 1) {
            Level level = new Level(levelNumber, waves, enemiesInWave, spawnInterval, scene);
            return level;
        } else {
            waves += 1;
            enemiesInWave += 1;
            
        }
        Level level = new Level(levelNumber, waves, enemiesInWave, spawnInterval, scene);
        return level;
    }
    public void nextLevel() {
        levelNumber ++;
    }
    
    
    
    
}
