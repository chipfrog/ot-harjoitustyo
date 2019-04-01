/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author jajuuso
 */
public class Ui extends Application{
    int width;
    int height;
    ImageView playerIcon;
    
    public Ui() {
        this.width = 1280;
        this.height = 720;
        this.playerIcon = new ImageView("player.png");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        root.getChildren().add(playerIcon);
        Scene scene = new Scene(root, width, height);
        PlayerMovement movement = new PlayerMovement(scene, playerIcon);
        movement.keyCommands();
        stage.setScene(scene);
        stage.show();
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movement.movePlayerIcon();
            }
        };timer.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
