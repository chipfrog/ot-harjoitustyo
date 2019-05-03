/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import shooter.gameobjects.Enemy;
import shooter.gameobjects.Bullet;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import shooter.filehandling.ConfigFileReader;

/**
 * Luokka, joka vastaa kokonaisuudessaan pelinkulun logiikasta, kuten ruudunpäivityksen aikana tapahtuvista metodeista 
 * (peliobjektien liikkeet, törmäykset toisiinsa, kuolleiden objektien poistaminen ruudusta jne.), pelin päättymisestä, vihollisten lisäämisestä yms.
 * @author jajuuso
 */
public class GameLogic {
    LevelHandler levelHandler;
    int waves;
    int enemiesInWave;
    int interval;
    Level level;
    Scene scene;
    Pane root;
    Player player;
    PlayerMovement pMovement;
    ArrayList<Enemy> enemies;
    ArrayList<Bullet> bullets;
    
    /**
     * Luo GameObject-olion
     * @param player Player-olio
     */
    public GameLogic(Player player) {
        this.waves = ConfigFileReader.getInt("waves");
        this.enemiesInWave = ConfigFileReader.getInt("enemiesInWave");
        this.interval = ConfigFileReader.getInt("interval");
        this.levelHandler = new LevelHandler(waves, enemiesInWave, interval);
        this.player = player;
        this.enemies = new ArrayList<>();
        this.bullets = new ArrayList<>();
        
    }

    /**
     * Metodin alle on koottu ruudunpäivityksen aikana tapahtuvat metodit, kuten vihollisten lisääminen, peliobjektien liikkeet, kuolleiden objektien poisto jne.
     */
    public void update() {
        updateEnemies();
        pMovement.movePlayerIcon();
        removeDeadEnemies(enemies, root);
        removeDeadBullets(bullets, root);
        shootBullets(bullets, scene);
        checkIfObjectsCollide();
    }

    /**
     * Lisää peliruutuun uudet viholliset, mikäli uusi vihollisaalto on luotu.
     */
    public void updateEnemies() {
        ArrayList<Enemy> enemisToSpawn = level.spawnWave();
        if (enemisToSpawn != null) {
            for (Enemy enemy : enemisToSpawn) {
                enemies.add(enemy);
                root.getChildren().add(enemy.getImage());
            }
        }
    }

    /**
     * Poistaa kuolleeksi merkatut viholliset ruudusta ja vihollisista kirjaa pitävästä ArrayLististä (enemies).
     * @param list Lista kaikista tason vihollisista. Kuolleet viholliset poistetaan tästä listasta.
     * @param root Pane-olio (eli "peliruutu), josta viholliset poistetaan.
     */
    public void removeDeadEnemies(ArrayList<Enemy> list, Pane root) {
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy enemy : list) {
            if (enemy.isAlive() == false) {
                root.getChildren().remove(enemy.getImage());
                enemiesToRemove.add(enemy);
            }
        }
        list.removeAll(enemiesToRemove);
        level.defeatEnemies(enemiesToRemove);
    }

    /**
     * Poistaa kuolleeksi merkatut ammukset peliruudusta ja bullets-listasta. Toimii samoin kuin removeDeadEnemies-metodi. 
     * @param list Lista kaikista ammutuista luodeista. Kuolleeksi merkatut luodit poistetaan tästä listasta.
     * @param root Pane-olio (eli "peliruutu), josta luodit poistetaan.
     */
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

    /**
     * Liikuttaa jokaista listan ammusta ammuksen suuntavektorin suuntaan ja tarkistaa onko ammus pelialueen ulkopuolella.
     * @param bullets ArrayList ammuksista
     * @param scene Scene-olio / pelialue
     */
    public void shootBullets(ArrayList<Bullet> bullets, Scene scene) {
        for (Bullet bullet : bullets) {
            bullet.fly();
            bullet.outOfGameArea((int) scene.getWidth(), (int) scene.getHeight());
        }
    }

    /**
     * Tarkistaa törmäävätkö peliobjektit toisiinsa: luodit vihollisiin tai viholliset pelaajaan. Jos luoti osuu viholliseen, 
     * vähenneetään vihollisen hp:ta ja luodin tila asetetaan kuolleeksi. Jos vihollisen hp menee nollaan, myös vihollisen tila asetetaan kuolleeksi ja pelaaja saa
     * pisteet vihollisen tuhoamisesta. Metodissa liikutetaan myös vihollisia kohti pelaajaa. Jos vihollinen osuu pelaajaan, vähennetään pelaajan hp:ta.
     */
    public void checkIfObjectsCollide() {
        for (Enemy e : enemies) {
            for (Bullet b : bullets) {
                if (e.hitDetection(b.getImage())) {
                    e.damageHealth();
                    b.setDead();
                    if (!e.isAlive()) {
                        player.increaseScore(e.givePoints()); 
                    }
                }
            }
            e.chasePlayer(pMovement.getPlayerLocation());
            if (e.hitDetection(player.getImageView()) && player.invincibilityOff()) {
                player.takeDamage(e.getDamage());
            } 
        }
    }

    /**
     * Ottaa käyttöön pelaajan ohjaamiseen käytetyt näppäin- ja hiirikomennot kyseisessä peli-instanssissa.
     */
    public void setUpControls() {
        pMovement.keyCommands();
        pMovement.mouseControl(root, bullets);
    }

    /**
     * Lisää pelihahmon peliruutuun ja sijoittaa sen anneettuun sijaintiin.
     */
    public void addAssetsToPane() {
        root.getChildren().add(player.getImageView());
        player.getImageView().setLayoutX(600);
        player.getImageView().setLayoutY(300);
        
    }

    /**
     * Tarkistaa täyttyvätkö pelin pysäyttämisen ehdot, eli onko pelaaja kuollut tai kaikki tason viholliset tuhottu.
     * @return true/false; pysäytetäänkö peli
     */
    public boolean stopGame() {
        if (!player.alive()) {
            return true;
        } else {
            return level.cleared();
        }
    }

    /**
     * Alustaa peliruudussa käytettävät Scene- ja Pane-oliot. Metodia täytyy kutsua ennen luokan muita metodeja, jotka vaativat kyseisten olioiden käyttöä.
     * @param scene Scene-olio
     * @param root Pane-olio
     */
    public void setScreenAndPane(Scene scene, Pane root) {
        this.scene = scene;
        this.root = root;
    }

    /**
     * Alustaa pMovement-luokan, luo uuden pelitason ja kasvattaa tason numeroa yhdellä. 
     */
    public void setupClasses() {
        this.pMovement = new PlayerMovement(scene, player);
        this.level = levelHandler.createNewLevel(scene);
        levelHandler.increaseLevelNumber();
    }

    /**
     * Paluttaa pelitason
     * @return Level
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Palauttaa vihollisaaltojen määrän
     * @return vihollisaaltojen määrä (int)
     */
    public int getWaves() {
        return this.waves;
    }

    /**
     * Palauttaa vihollisten määrän yhdessä vihollisaallossa
     * @return Vihollisten määrä per aalto (int)
     */
    public int getEnemiesInWave() {
        return this.enemiesInWave;
    }
}
