/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shooter.db.Database;
import shooter.db.PlayerDao;
import shooter.logic.GameLogic;
import shooter.logic.Player;

/**
 *
 * @author jajuuso
 */
public class Leaderboard extends SceneSwitcher{
    Stage stage;
    Scene previousScene;
    GameLogic logic;
    
    /**
     *
     * @param database
     * @param playerDao
     * @param stage
     * @param previousScene
     */
    public Leaderboard (Database database, PlayerDao playerDao, Stage stage, Scene previousScene) {
        this.stage = stage;
        this.previousScene = previousScene;
        
    }

    /**
     *
     * @return
     */
    public Scene openLeaderboard() {
        ArrayList<Player> list = playerDao.list();
        Button deleteAll = new Button("Delete");
        Button back = new Button("Back");
        
        TableView<Player> tableview = new TableView<>();
        TableColumn<Player, String> nameCol = new TableColumn("Name");
        TableColumn<Player, Integer> scoreCol = new TableColumn("Score");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        tableview.getColumns().addAll(nameCol, scoreCol);
     
        HBox hbox = new HBox(deleteAll, back);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(20));
        
        VBox allItems = new VBox(tableview, hbox);
        allItems.setPadding(new Insets(20));
        allItems.setPrefSize(600, 400);
        tableview.getItems().addAll(list);
        
        deleteAll.setOnAction(event -> {
            try {
                playerDao.resetLeaderboard();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        
        back.setOnAction(event -> {
            mainMenu(stage);
//            stage.setScene(previousScene);
        });
        
        Scene scene = new Scene(allItems);
        return scene;
    }
    
   
    
}
