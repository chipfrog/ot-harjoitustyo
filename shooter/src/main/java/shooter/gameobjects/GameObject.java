/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.gameobjects;

import javafx.scene.image.ImageView;



/**
 *
 * @author jajuuso
 */
public class GameObject {
    double speed;
    boolean alive;
    ImageView imageview;
    
    /**
     * Luo uuden GameObjectin. GameObject on Enemy- ja Bullet-luokkien yläluokka. 
     * @param imageview GameObjectin kuva
     * @param speed GameObjectin nopeus
     */
    public GameObject(ImageView imageview, double speed) {
        this.imageview = imageview;
        this.alive = true;
        this.speed = speed;
    }

    /**
     * Asettaa GameObjectin tilan kuolleeksi (alive = false). (Kuolleet objektit poistetaan pelistä GameLogic-luokan metodeilla removeDeadBullets ja removeDeadEnemies.)
     */
    public void setDead() {
        this.alive = false;
    }

    /**
     * Kertoo onko GameOject elossa, eli voiko sen poistaa pelistä vai ei.
     * @return Onko GameObject elossa vai ei.
     */
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Palauttaa nopeuden.
     * @return nopeus
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Palauttaa ImageView:n
     * @return kuva
     */
    public ImageView getImage() {
        return this.imageview;
    }

    /**
     * Kertoo osuuko pelihahmon kuva toisen pelihahmon kuvaan. Esim. osuuko ammus viholliseen tai vihollinen pelaajaan.
     * @param otherImage
     * @return Osuvatko hahmojen kuvat toisiinsa.
     */
    public boolean hitDetection(ImageView otherImage) {
        return imageview.getBoundsInParent().intersects(otherImage.getBoundsInParent());
    }
    
    
    
    
    
}
