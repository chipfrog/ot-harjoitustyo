/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Luokka SQL-tietokantataulun luomiseen ja siihen yhteyden ottamiseen.
 * 
 * @author jajuuso
 */
public class Database {
    String url;
    
    /**
     * 
     * @param url Osoite käytettävään/luotavaan tietokantatauluun
     */
    public Database(String url) {
        this.url = url;
    }

    /**
     * Luo Leaderboard-tietokantataulun, mikäli sitä ei ennestään ole olemassa.
     */
    public void createDatabase() {
        try {
            Connection conn = getConnection();
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS Leaderboard (name varchar(16), score integer, PRIMARY KEY(name));").executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Muodostaa yhteyden tietokantaan.
     * @return yhteys
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
    
}
