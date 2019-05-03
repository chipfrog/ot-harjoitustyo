/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import javafx.scene.Scene;
import shooter.filehandling.ConfigFileReader;

/**
 * Luokka vastaa uusien tasojen luomisesta ja niiden vihollismäärien asettamisesta ja kasvattamisesta.
 * @author jajuuso
 */
public class LevelHandler {
    int waves;
    int enemiesInWave;
    double spawnInterval;
    int levelNumber;
    
    /**
     *
     * @param waves Vihollisaaltojen määrä
     * @param enemiesInWave Vihollisten määrä aallossa
     * @param spawnInterval Aikaväli uuden vihollisaallon ilmestymiseen
     */
    public LevelHandler(int waves, int enemiesInWave, double spawnInterval) {
        this.waves = waves;
        this.enemiesInWave = enemiesInWave;
        this.spawnInterval = spawnInterval;
        this.levelNumber = 1;
    }

    /**
     * Luo uuden tason. Jos kyseessä on ensimmäinen taso, kohtien waves, enemiesInWave ja spawnInterval arvoiksi asetetetaan config.properties-tiedostossa olevat arvot.
     * Muulloin näitä arvoja kasvatetaan aina tason vaihtuessa samassa tiedostossa määrätyillä arvoilla.
     * @param scene Scene
     * @return uusi taso
     */
    public Level createNewLevel(Scene scene) {
        if (levelNumber == 1) {
            Level level = new Level(levelNumber, waves, enemiesInWave, spawnInterval, scene);
            return level;
        } else {
            waves += ConfigFileReader.getInt("wavesIncreaseBy");
            enemiesInWave += ConfigFileReader.getInt("enemiesInWaveIncreaseBy");
        }
        Level level = new Level(levelNumber, waves, enemiesInWave, spawnInterval, scene);
        return level;
    }

    /**
     * Kasvattaa tason numeroa.
     */
    public void increaseLevelNumber() {
        levelNumber++;
    }

    /**
     * Palauttaa nykyisen tason numeron.
     * @return tason numero
     */
    public int getLevelNumber() {
        return this.levelNumber;
    }
    
    
    
    
}
