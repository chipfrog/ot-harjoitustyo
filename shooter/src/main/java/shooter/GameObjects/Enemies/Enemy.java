/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.GameObjects.Enemies;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import shooter.GameObjects.Bullet;
import shooter.GameObjects.GameObject;
import shooter.logic.Player;


/**
 *
 * @author jajuuso
 */
public class Enemy extends GameObject{
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
     * @param x Vihollisen sijainnin x-koordinaatti
     * @param y Vihollisen sijainnin y-koordinaatti
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
     *
     * @return
     */
    public int givePoints() {
        return this.points;
    }

    /**
     *
     * @return
     */
    public int getDamage() {
        return this.damage;
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
