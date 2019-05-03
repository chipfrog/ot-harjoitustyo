/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shooter.logic.GameLogic;
import shooter.logic.Player;

/**
 *
 * @author jajuuso
 */
public class PlayerCreation extends SceneSwitcher{
    
    /**
     *
     */
    public  PlayerCreation() {
        
    }

    /**
     *
     * @param stage
     * @return
     */
    public Scene openPlayerCreation(Stage stage) {
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        
        Label label = new Label("Name: ");
        TextField nameField = new TextField();
        hbox.getChildren().addAll(label, nameField);
        
        Button startButton = new Button("Start!");
        Label errorMessage = new Label();
        vbox.getChildren().addAll(hbox, startButton, errorMessage);
        vbox.setSpacing(20);
        vbox.setPrefSize(600, 400);
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.setPadding(new Insets(20));
        
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
        
        startButton.setOnAction(event ->{
            if (nameField.getText().isEmpty() || nameField.getText().length() < 4 || nameField.getText().length() > 15) {
                errorMessage.setText("You need to enter a name for the player! \n The name must be at least 4 characters long \n and not over 15 characters.");
            } else {
                Player player = new Player(nameField.getText(), 0);
                GameLogic logic = new GameLogic(player);
                game(stage, player, logic);
            }
        });
        return scene;
    }
    
}
