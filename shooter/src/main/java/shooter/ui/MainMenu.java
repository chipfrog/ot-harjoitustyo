/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author jajuuso
 */
public class MainMenu extends Application{
    SceneSwitcher sceneSwitcher;

    public MainMenu() {
        this.sceneSwitcher = new SceneSwitcher();
    }
    @Override
    public void start(Stage stage) throws Exception {
        VBox box = new VBox();
        Text title = new Text("Top Down Shooter");
        title.setFont(Font.font("Impact", 30));
        Button gameButton = new Button("New Game");
        Button leaderboard = new Button("Leaderboard");
        Button exitButton = new Button("Exit");
        
        box.getChildren().addAll(title, gameButton, leaderboard, exitButton);
        box.setPrefSize(600, 400);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
        
        gameButton.setOnAction(event -> {
            sceneSwitcher.newGame(stage);
        });
        leaderboard.setOnAction(event -> {
            sceneSwitcher.leaderboard(stage);
        });
        exitButton.setOnAction(event -> {
            stage.close();
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
