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
public class Player {
    private String name;
    private int hp;
    private int maxhp;
    private int stamina;
    private boolean alive;
    private boolean canBeHit;
    private double startTime;
    private double endTime;
    private int score;
    
    public Player(String name) {
        this.name = name;
        this.maxhp = 50;
        this.hp = 50;
        this.stamina = 50;
        this.canBeHit = true;
        this.alive = true;
        this.score = 0;
    }
    public int getHp() {
        return this.hp;
    }
    public int getMaxHp() {
        return this.maxhp;
    }
    public void takeDamage() {
        timer(2000);
        if (hp - 10 > 0) {
            this.hp -= 10;
            canBeHit = false;
        } else {
            this.hp = 0;
            alive = false;
        }
    }
    public String getName() {
        return this.name;
    }

    public void timer(int duration) {
        this.startTime = System.currentTimeMillis();
        this.endTime = startTime + duration;
    }
    
    public boolean invincibility() {
        if (System.currentTimeMillis() >= endTime) {
            canBeHit = true;
        }
        return canBeHit;
    }
    public boolean alive() {
        return this.alive;
    }
    public int getScore() {
        return this.score;
    }
    public void increaseScore() {
        this.score += 1;
    }
}
