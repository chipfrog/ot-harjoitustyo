/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shooter.logic.Player;

/**
 *
 * @author jajuuso
 * @param <T>
 * @param <K>
 */
public interface Dao<T, K> {

    /**
     *
     * @param object
     * @throws SQLException
     */
    void addPlayer(T object) throws SQLException;

    /**
     *
     * @param object
     * @throws SQLException
     */
    
    
    void updateBestScore(T object) throws SQLException;

    /**
     *
     * @param object
     * @return
     * @throws SQLException
     */
    boolean contains(T object) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    ArrayList<T> list() throws SQLException;

    /**
     *
     * @throws SQLException
     */
    void resetLeaderboard() throws SQLException;
}
