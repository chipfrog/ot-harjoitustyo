/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.gameobjects;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * Luokka sisältää pelin vihollisten toimintoihin, kuten liikkumiseen ja sijaintiin liittyvät metodit.
 * @author jajuuso
 */
public class Enemy extends GameObject {
    Point2D location;
    Point2D direction;
    int points;
    int damage;
    int hp;
    
    /**
     * Luodaan uusi vihollinen ja asetetaan sille halutut attribuutit.
     *
     * @param imageview Vihollisen kuva 
     * @param speed Nopeus, jolla vihollinen liikkuu pelaajaa kohti.
     * @param hp Vihollisen hitpointsit, eli montako osumaa vihollinen kestää ennen kuolemista.
     * @param points Vihollisen tappamisesta pelaajalle annetut pisteet
     * @param damage Vahinko, jonka vihollinen aiheuttaa osuessaan pelaajaa. Tämä määrä vähennetään pelaajan hp:sta.
     * @param x Vihollisen sijainnin x-koordinaatti (käytetään Point2D-vektorin luomiseen)
     * @param y Vihollisen sijainnin y-koordinaatti (käytetään Point2D-vektorin luomiseen)
     */
    public Enemy(ImageView imageview, double speed, int hp, int points, int damage, double x, double y) {
        super(imageview, speed);
        this.hp = hp;
        this.location = new Point2D(x, y);
        this.points = points;
        this.damage = damage;
    }

    /**
     * Laskee suuntavektorin vihollisen sijainnista pelaajan sijaintiin ja kertoo sen vihollisen nopeudella.
     * Vihollinen siirtyy lasketun määrän verran kohti pelaajaa.
     * 
     * @param playerLocation Pelaajan viimeisin sijainti Point2D-vektorina.
     */
    public void chasePlayer(Point2D playerLocation) {
        direction = playerLocation.subtract(location).normalize();
        location = location.add(direction.multiply(getSpeed()));
        getImage().relocate(location.getX(), location.getY());
    }

    /**
     * Palauttaa kyseisen vihollisen tuhoamisesta annettavan pistemäärän.
     * @return annetteavat pisteet
     */
    public int givePoints() {
        return this.points;
    }

    /**
     * Palauttaa vihollisen pelaajalle aiheuttaman hp-vahingon.
     * @return aiheutettu hp-vahinko
     */
    public int getDamage() {
        return this.damage;
    }
    /**
     * Palauttaa vihollisen hp:n.
     * @return vihollisen hp
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Palauttaa vihollisen sijainnin Point2D-vektorina
     * @return sijainti Point2D-vektorina
     */
    public Point2D getLocation() {
        return this.location;
    }
    /**
     * Vähentää vihollisen hp:ta yhdellä, Jos hp menee nollaksi tai negatiiviseksi, asettaa vihollisen kuolleeksi metodilla setDead(),
     *
     */
    public void damageHealth() {
        this.hp -= 1;
        if (this.hp <= 0) {
            setDead();
        }
        
    } 
    
    
    
    
    
}
