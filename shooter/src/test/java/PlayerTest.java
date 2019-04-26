/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;
import shooter.logic.Player;

/**
 *
 * @author jajuuso
 */
public class PlayerTest extends ApplicationTest{
    Player player;
    
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
        this.player = new Player("John", 0);
        
    }
    @Test
    public void playerTakesDamage() {
        player.takeDamage(5);
        player.takeDamage(15);
        assertTrue(player.getHp() == 30);
    }
    @Test
    public void hpIsNeverNegative() {
        player.takeDamage(10);
        player.takeDamage(10);
        player.takeDamage(10);
        player.takeDamage(10);
        player.takeDamage(20);
        player.takeDamage(50);
        assertTrue(player.getHp() == 0);
    }
    @Test
    public void nameIsRight() {
        assertTrue(player.getName().equals("John"));
    }
    @Test
    public void canBeHitWhenNotInvincible() {
        assertTrue(player.invincibilityOff() == true);
    }
    @Test
    public void cannotBeHitWhenInvincible() {
        player.takeDamage(10);
        assertTrue(player.invincibilityOff() == false);
    }
    @Test
    public void scoreStartsAtZero() {
        assertTrue(player.getScore() == 0);
    }
    @Test
    public void scoreIncreases() {
        player.increaseScore(2);
        player.increaseScore(5);
        assertTrue(player.getScore() == 7);
    }
    @Test
    public void playerIsAliveAtStart() {
        assertTrue(player.alive() == true);
    }
    @Test
    public void playerDiesAfterTakingTooMuchDamage() {
        player.takeDamage(40);
        player.takeDamage(100);
        
        assertTrue(player.alive() == false);
    }
    @Test
    public void maxHpIsRight() {
        assertTrue(player.getMaxHp() == 50);
    }
    
    
    
    

    

    
    
    
}
