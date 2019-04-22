/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.logic;



/**
 *
 * @author jajuuso
 */
public class GameObject {
    private double speed;
    private boolean alive;
    
    public GameObject(double speed) {
        this.speed = speed;
        this.alive = true;
    }
    public void setDead() {
        this.alive = false;
    }
    public boolean isAlive() {
        return this.alive;
    }
    public double getSpeed() {
        return this.speed;
    }
    
    
    
    
    
}
