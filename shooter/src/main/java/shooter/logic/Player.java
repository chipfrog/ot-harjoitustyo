/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;

import javafx.scene.image.ImageView;


/**
 *
 * @author jajuuso
 */
public class Player {
    private String name;
    private int hp;
    private int maxhp;
    private boolean alive;
    private boolean canBeHit;
    private double startTime;
    private double endTime;
    private int score;
    private ImageView imageview;
    
    /**
     * Luodaan uusi pelaaja.
     * @param name Pelaajan nimi, joka tallennetaan pelin lopuksi myös leaderboardiin.
     * @param score Pelaajan pistemäärä, aluksi nolla.
     */
    public Player(String name, int score) {
        this.name = name;
        this.maxhp = 50;
        this.hp = 50;
        this.canBeHit = true;
        this.alive = true;
        this.score = 0;
        this.imageview = new ImageView("velho.png");
        
    }

    /**
     *
     * @return
     */
    public int getHp() {
        return this.hp;
    }
    /**
     *
     * @return
     */
    public int getMaxHp() {
        return this.maxhp;
    }
    /**
    * Vähentää pelaajan hitpointseja annetulla määrällä. Jos hp menisi
    * negatiiviseksi, asetetaan sen arvoksi nolla. Käynnistää samalla ajastimen (timer) ja muuttaa canBeHit-arvoksi false.
    * 
    * @param damage Pelaajan hp:sta vähennettävä määrä
    */
    public void takeDamage(int damage) {
        timer(2000);
        if (hp - damage > 0) {
            this.hp -= damage;
            canBeHit = false;
        } else {
            this.hp = 0;
            alive = false;
        }
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }
    /**
    * Ajastin, joka tallentaa muuttujaan startTime metodin kutsumishetken ajan ja laskee ajastuksen päättymisajan (endTime) 
    * lisäämällä startTime:een määritellyn ajastuksen keston (duration).
    * 
    * @param duration Ajastuksen kesto millisekunteina.
    */
    public void timer(int duration) {
        this.startTime = System.currentTimeMillis();
        this.endTime = startTime + duration;
    }
    /**
    * Tarkistaa onko ajastimessa (timer) määritelty ajastuksen loppumisaika (endTime) jo ohitettu. Jos on, muuttujan canBeHit arvoksi asetetaan true.
    * 
    * @return Kertoo onko ajastimen aika kulunut loppuun, eli voiko pelaaja jälleen menettää hitpointseja osuessaan viholliseen.
    */
    public boolean invincibilityOff() {
        if (System.currentTimeMillis() >= endTime) {
            canBeHit = true;
        }
        return canBeHit;
    }

    /**
     *
     * @return
     */
    public boolean alive() {
        return this.alive;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return this.score;
    }
    /**
     * Metodi kasvattaa pelaajan pistesaldoa annetulla määrällä 
     * 
     * @param points Annetut pisteet
     */
    public void increaseScore(int points) {
        this.score += points;
    }
    
    /**
     *
     * @return
     */
    public ImageView getImageView() {
        return this.imageview;
    }
    
}
