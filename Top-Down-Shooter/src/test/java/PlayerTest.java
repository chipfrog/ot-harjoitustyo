/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ui.Enemy;
import logic.Player;

/**
 *
 * @author jajuuso
 */
public class PlayerTest{
    Player player;
    Enemy enemy;
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
        this.player = new Player("John");
        this.enemy = new Enemy(0, 0);
        
    }
    @Test
    public void playerTakesDamage() {
        player.takeDamage();
        player.takeDamage();
        assertTrue(player.getHp() == 30);
    }
    @Test
    public void hpIsNeverNegative() {
        player.takeDamage();
        player.takeDamage();
        player.takeDamage();
        player.takeDamage();
        player.takeDamage();
        player.takeDamage();
        assertTrue(player.getHp() == 0);
    }
    @Test
    public void nameIsRight() {
        assertTrue(player.getName().equals("John"));
    }
    
    
    

    

    
    
    
}
