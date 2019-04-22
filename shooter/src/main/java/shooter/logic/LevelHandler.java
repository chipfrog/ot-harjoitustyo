/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import java.util.ArrayList;
import javafx.scene.Scene;
import shooter.ui.Level;

/**
 *
 * @author jajuuso
 */
public class LevelHandler {
    int waves;
    int enemiesInWave;
    int spawnInterval;
    int clearedLevels;
    
    
    public LevelHandler(int waves, int enemiesInWave, int spawnInterval) {
        this.waves = waves;
        this.enemiesInWave = enemiesInWave;
        this.spawnInterval = spawnInterval;
        this.clearedLevels = 0;
    }
    public Level createNewLevel(Scene scene) {
        if (clearedLevels == 0) {
            Level level = new Level(waves, enemiesInWave, spawnInterval, scene);
            return level;
        } else {
            waves += 1;
            enemiesInWave += 1;
            clearedLevels ++;
        }
        Level level = new Level(waves, enemiesInWave, spawnInterval, scene);
        return level;
    }
    public void increaseClearedLevels() {
        clearedLevels ++;
    }
    
    
    
}
