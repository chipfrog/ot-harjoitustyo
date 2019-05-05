/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import shooter.db.Database;
import shooter.db.PlayerDao;
import shooter.filehandling.ConfigFileReader;
import shooter.logic.GameLogic;
import shooter.logic.Player;

/**
 * Luokka vastaa pelinäkymien vaihtamisesta; esim. päävalikosta leaderboardiin jne.
 * @author jajuuso
 */
public class SceneSwitcher {
    Database database;
    PlayerDao playerDao;
    
    public SceneSwitcher() {
        this.database = new Database(ConfigFileReader.getString("database"));
        database.createDatabase();
        this.playerDao = new PlayerDao(database);
    }
    public void mainMenu(Stage stage) {
        try {
            MainMenu menu = new MainMenu();
            menu.start(stage);
            stage.centerOnScreen();
        } catch (Exception e) {
        }
    }
    public void leaderboard(Stage stage) {
        Leaderboard lb = new Leaderboard(stage);
        Scene scene = lb.openLeaderboard();
        stage.setScene(scene);
    }
    public void newGame(Stage stage) {
        PlayerCreation pc = new PlayerCreation();
        Scene scene = pc.openPlayerCreation(stage);
        stage.setScene(scene);
    }
    public void game(Stage stage, Player player, GameLogic logic) {
        GameWindow game = new GameWindow(playerDao, player, logic, stage);
        Scene scene = game.gameScreen();
        stage.setScene(scene);
    }
}
