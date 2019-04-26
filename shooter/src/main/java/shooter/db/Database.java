/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jajuuso
 */
public class Database {
    String URL;
    
    public Database(String URL) {
        this.URL = URL;
    }
    public void createDatabase() {
        try {
            Connection conn = getConnection();
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS Leaderboard (name varchar(16), score integer, PRIMARY KEY(name));").executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public Connection getConnection () throws SQLException{
        Connection conn = DriverManager.getConnection(URL);
        return conn;
    }
    
}
