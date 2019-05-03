/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import shooter.gameobjects.Enemy;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;


/**
 * Luokka sisältää pelitasoon liittyviä metodeja, kuten tason vihollisten luomisen.
 * @author jajuuso
 */
public class Level {
    int waves;
    int totalEnemies;
    int enemiesInWave;
    double spawnInterval;
    double startTime;
    Scene scene;
    boolean firstWave;
    int waveCounter;
    int levelNumber;
    Random random;
    int distance;
    
    /**
     * Tason luominen
     * @param levelNumber tason numero, vaikuttaa vihollisten tyyppiin
     * @param waves vihollisaaltojen määrä
     * @param enemiesInWave vihollisten määrä aallossa
     * @param spawnInterval aikaväli, jonka jälkeen uusi vihollisaalto ilmestyy
     * @param scene Scene-olio / peliruutu
     */
    public Level(int levelNumber, int waves, int enemiesInWave, double spawnInterval, Scene scene) {
        this.waves = waves;
        this.spawnInterval = spawnInterval;
        this.startTime = 0;
        this.scene = scene;
        this.firstWave = true;
        this.enemiesInWave = enemiesInWave;
        this.waveCounter = 0;
        this.totalEnemies = waves * enemiesInWave;
        this.levelNumber = levelNumber;
        this.random = new Random();
        this.distance = 300;
    }

    /**
     * Luo uuden vihollisaallon, mikäli on kulunut riittävän pitkä aika edellisen aallon luomisesta. Jos kyseessä on tason ensimmäinen aalto, se luodaan heti ilman aikaviivettä.
     * @return
     */
    public ArrayList<Enemy> spawnWave() {
        if (firstWave) {
            startTime = System.currentTimeMillis();
            firstWave = false;
            waveCounter++;
            return createWave();
        }
        double timeDiff = (System.currentTimeMillis() - startTime) / 1000;
        if (timeDiff > spawnInterval && waveCounter < waves) {
            startTime = System.currentTimeMillis();
            waveCounter++;
            return createWave();
        }
        return null;
    }

    /**
     * Vähentää kuolleiden vihollisten määrän tason kokonaisvihollismäärästä. 
     * @param defeatedEnemies lista tapetuista vihollisista
     */
    public void defeatEnemies(ArrayList<Enemy> defeatedEnemies) {
        totalEnemies -= defeatedEnemies.size();
    }

    /**
     * Kertoo onko tason jäljellä oleva kokonaisvihollismäärä 0. Jos on, taso on läpäisty.
     * @return true / false; vihollisia jäljellä
     */
    public boolean cleared() {
        return totalEnemies == 0;
    }

    /**
     * Palauttaa jäljellä olevien vihollisten määrän.
     * @return jäljellä olevat viholliset (int)
     */
    public int getTotalEnemies() {
        return this.totalEnemies;
    }
    /**
     * Luo uuden vihollisaallon (lista vihollisista). Arpoo koordinaatit, johon kukin luotu vihollinen ilmestyy peliruudussa. 
     * Viholliset ilmestyvät aina peliruudun näkyvän osan ulkopuolelle. 
     * @return vihollisaalto (lista)
     */
    private ArrayList<Enemy> createWave() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < enemiesInWave; i++) {
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
            Enemy e = generateEnemy(rx, ry);
            enemies.add(e);
        }
        return enemies;
    }
    /**
     * Luo satunnaisen vihollisen. Mitä suurempi i:n arvo on, sitä todennäköisemmin uusi vihollinen on zombi. Tason numero kasvattaa
     * i:n arvoa ja sitä kautta zombien ilmestymisen todennäköisyyttä, eli pelin vaikeutta. 
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return uusi vihollinen
     */
    private Enemy generateEnemy(int x, int y) {
        int i = random.nextInt(levelNumber + 10);
        if (i < 9) {
            return new Enemy(new ImageView("chick.png"), 0.7, 1, 5, 5, x, y);
        } else {
            return new Enemy(new ImageView("zombie.png"), 1.6, 3, 10, 10, x, y);
        
        }
    }
    
    
}
