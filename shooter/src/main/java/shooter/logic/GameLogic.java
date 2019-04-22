/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import shooter.ui.Level;

/**
 *
 * @author jajuuso
 */
public class GameLogic {
    LevelHandler levelHandler;
    int waves;
    int enemiesInWave;
    int interval;
    int levelsBeaten;
    Level level;
    Scene scene;
    Pane root;
    Player player;
    PlayerMovement pMovement;
    ArrayList<Enemy> enemies;
    ArrayList<Bullet> bullets;
    
    public GameLogic(Player player) {
        this.waves = 2;
        this.enemiesInWave = 3;
        this.interval = 5;
        this.levelHandler = new LevelHandler(waves, enemiesInWave, interval);
        this.player = player;
        this.enemies = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.levelsBeaten = 0;
        
        
    }
    public void update() {
        updateEnemies();
        pMovement.movePlayerIcon();
        removeDeadEnemies(enemies, root);
        removeDeadBullets(bullets, root);
        shootBullets(bullets, scene);
        chasePlayer();
        
    }
    public void moveToNextLevel() {
        levelHandler.increaseClearedLevels();
        level = levelHandler.createNewLevel(scene);
    }
    public void updateEnemies() {
        ArrayList<Enemy> enemisToSpawn = level.spawnWave();
        if (enemisToSpawn != null) {
            for (Enemy enemy : enemisToSpawn) {
            enemies.add(enemy);
            root.getChildren().add(enemy.getShape());
            }
        }
        
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
        level.defeatEnemies(enemiesToRemove);
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
    public void shootBullets(ArrayList<Bullet> bullets, Scene scene) {
        for (Bullet bullet : bullets) {
            bullet.fly();
            bullet.outOfGameArea((int) scene.getWidth(),(int) scene.getHeight());
        }
    }
    public void chasePlayer() {
        for (Enemy e : enemies) {
            for (Bullet b : bullets) {
                if(e.isHit(b)) {
                    player.increaseScore();
                }
            }
            e.chasePlayer(pMovement.getPlayerLocation());
            e.playerIsHit(player); 
        }
    }
    public void setUpControls() {
        pMovement.keyCommands();
        pMovement.mouseControl(root, bullets);
    }
    public void addAssetsToPane() {
        root.getChildren().add(player.getImageView());
        player.getImageView().setLayoutX(600);
        player.getImageView().setLayoutY(300);
        
    }
    public boolean stopGame() {
        if (!player.alive()) {
            return true;
        } else {
            return level.cleared();
        }
    }
    public void setScreenAndPane(Scene scene, Pane root) {
        this.scene = scene;
        this.root = root;
    }
    public void setupClasses() {
        this.pMovement = new PlayerMovement(scene, player.getImageView());
        this.level = levelHandler.createNewLevel(scene);
    }
    
}
