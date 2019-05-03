/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.gameobjects;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * Luokka vastaa ammusten liikeradoista, sijainneista yms.
 * @author jajuuso
 */
public class Bullet extends GameObject {
//    ImageView imageview;
    int damage;
    int hitsBeforeDead;
    Point2D bulletLocation;
    Point2D target;
    
    /**
     * Luo uuden ammuksen.
     * @param imageview Ammuksen kuva
     * @param speed Nopeus, jolla ammus liikkuu.
     * @param bulletLocation Piste, josta ammus lähtee liikkeelle (sama kuin pelaajan sijainti)
     * @param target Piste, jonka suuntaan ammus liikkuu. Määritellään hiiren kursorin sijainnin perusteella klikkauksen yhteydessä. 
     */
    public Bullet(ImageView imageview, double speed, Point2D bulletLocation, Point2D target) {
        super(imageview, speed);

        this.target = target;
        this.bulletLocation = bulletLocation;
    }

    /**
     * Palauttaa ImageView:n (kuvan) ammuksesta.
     * @return ammuksen ImageView
     */
    public ImageView getShape() {
        return this.imageview;
    }

    /**
     * Antaa ammuksen sijainnin. Sijainti päivittyy joka kutsun yhteydessä, sillä joka kutsun yhteydessä luodaan uusi Point2D-vektori,
     * joka kertoo ammuksen sen hetkisen sijainnin.
     *
     * @return Ammuksen sijainti
     */
    public Point2D getBulletLocation() {
        this.bulletLocation = new Point2D(imageview.getLayoutX(), imageview.getLayoutY());
        return bulletLocation;
    }

    /**
     * Laskee kulman, jonka suuntaan ammus lähtee määritellyllä nopeudella (speed).(Suunta määritellään PlayerMovement-luokassa kursorin sijainnin perusteella.)
     */
    public void fly() {
        double angle = Math.atan2(target.getX() - bulletLocation.getX(), target.getY() - bulletLocation.getY());
        double xV = getSpeed() * Math.sin(angle);
        double yV = getSpeed() * Math.cos(angle);
        getShape().relocate(getShape().getLayoutX() + xV, getShape().getLayoutY() + yV);
    }

    /**
     * Tarkistaa onko ammus pelialueen ulkopuolella. Jos on, ammus asetetaan kuolleeksi poistamista varten. 
     * @param width Peliruudun leveys
     * @param height Peliruudun korkeus
     */
    public void outOfGameArea(int width, int height) {
        if (getShape().getLayoutX() > width || getShape().getLayoutY() > height) {
            setDead();
        }
    }
}
