/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

/**
 *
 * @author jajuuso
 */
public class Player {
    private String name;
    private int hp;
    private int stamina;
    boolean alive;
    
    public Player(String name) {
        this.name = name;
        this.hp = 50;
        this.stamina = 50;
    }
    public int getHp() {
        return this.hp;
    }
    public void takeDamage() {
        if (hp - 10 > 0) {
            this.hp -= 10;
        } else {
            this.hp = 0;
        }
        
    }
    public String getName() {
        return this.name;
    }
    
    
    
}
