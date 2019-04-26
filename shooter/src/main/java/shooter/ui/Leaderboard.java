/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author jajuuso
 */
public class Leaderboard {
    
    
    public Leaderboard() {
        
    }

    
    public static Scene newScene(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(600, 400);
        
        
        Button back = new Button("Back");
        Label label = new Label("Leaderboard");
        pane.getChildren().addAll(label, back);
        back.setOnAction(event -> {
            
        });
        
        Scene scene = new Scene(pane);
        return scene;
    }
    
   
    
}
