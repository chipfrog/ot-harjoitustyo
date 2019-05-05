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
import java.util.ArrayList;
import shooter.logic.Player;

/**
 *
 * @author jajuuso
 */
public class PlayerDao implements Dao<Player, String> {
    Database database;
    
    /**
     *
     * @param database
     */
    public PlayerDao(Database database) {
        this.database = database;
    }

    /**
     * Lisää pelaajan tietokantatauluun (nimi ja pisteet)
     * @param player player-olio
     * @throws SQLException
     */
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

    /**
     * Päivittää olemassaolevan pelaajan pistetuloksen, jos se on suurempi kuin vanha tulos.
     * @param player player-olio
     * @throws SQLException
     */
    @Override
    public void updateBestScore(Player player) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Leaderboard SET score = ? WHERE name = ? AND score < ?;");
        statement.setInt(1, player.getScore());
        statement.setString(2, player.getName());
        statement.setInt(3, player.getScore());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    /**
     * Luo listan kaikista tietokantataulussa olevista pelaajista ja laittaa ne järjestykseen pistemäärän mukaan suurimmasta pienimpään.
     * @return ArrayList Player-olioista
     */
    @Override
    public ArrayList<Player> list() {
        ArrayList<Player> list = new ArrayList<>();
        try {
            Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT name, score FROM Leaderboard ORDER BY score DESC;");
            ResultSet rs = statement.executeQuery();
            
            while (rs.next() == true) {
                String name = rs.getString(1);
                Integer score = rs.getInt(2);
                Player p = new Player(name, score);
                list.add(p);
            }
            rs.close();
            statement.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    /**
     * Tarkistaa nimen perusteella, onko pelaaja ennestään olemassa tietokantataulussa.
     * @param player Player-olio
     * @return true/false
     * @throws SQLException
     */
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

    /**
     * Tyhjentää kaiken rividatan tietokantataulusta.
     * @throws SQLException
     */
    @Override
    public void resetLeaderboard() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Leaderboard;");
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
