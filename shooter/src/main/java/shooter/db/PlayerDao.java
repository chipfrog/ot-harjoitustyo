/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shooter.logic.Player;

/**
 *
 * @author jajuuso
 */
public class PlayerDao implements Dao<Player, String>{
    Database database;
    
    public PlayerDao(Database database) {
        this.database = database;
    }

    @Override
    public void addPlayer(Player player) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Leaderboard (name, score) VALUES (?, ?);");
        statement.setString(1, player.getName());
        statement.setInt(2, player.getScore());
        statement.executeUpdate();
        statement.close();
        connection.close();
        
    }
    @Override
    public void updateBestScore(Player player) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Leaderboard SET score = ? WHERE name = ?;");
        statement.setInt(1, player.getScore());
        statement.setString(2, player.getName());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public ArrayList<Player> list() {
        ArrayList<Player> list = new ArrayList<>();
        try {
            Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT name, score FROM Leaderboard ORDER BY score DESC;");
            ResultSet rs = statement.executeQuery();
            
        int i = 1;
        while (rs.next() == true) {
            String name = rs.getString(i);
            int score = rs.getInt(i);
            Player p = new Player(name, score);
            list.add(p);
            i ++;
        }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
        
    }

    @Override
    public boolean contains(Player player) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Leaderboard WHERE name = ?;");
        statement.setString(1, player.getName());
        ResultSet rs = statement.executeQuery();
        
        if (rs.next() == false) {
            rs.close();
            statement.close();
            connection.close();
            return false;
        } else {
            rs.close();
            statement.close();
            connection.close();
            return true;
        }
        
    }
    
    

    
    
}
