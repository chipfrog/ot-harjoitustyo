/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import shooter.db.Database;
import shooter.db.PlayerDao;
import shooter.filehandling.ConfigFileReader;
import shooter.logic.GameLogic;
import shooter.logic.Player;

/**
 *
 * @author jajuuso
 */
public class GameWindow extends SceneSwitcher{
    int width;
    int height;
    Player player;
    GameLogic logic;
    Stage stage;
    
    public GameWindow(PlayerDao playerDao, Player player, GameLogic logic, Stage stage) {
        this.width = ConfigFileReader.getInt("width");
        this.height = ConfigFileReader.getInt("height");
        this.player = player;
        this.logic = logic;
        this.stage = stage;
        
    }
    public Scene gameScreen() {
        stage.setResizable(true);
        Pane root = new Pane();
        HBox hbox = new HBox();
        Label healthbar = new Label("Hp: " + player.getHp() + "/" + player.getMaxHp());
        Label scoreLabel = new Label("Score: " + player.getScore());
        Label endText = new Label();
        Button nextB = new Button("Next level");
        Button quitB = new Button("Quit game");
        
        nextB.setOnAction(event -> {
            game(stage, player, logic);
        });
        quitB.setOnAction(event -> {
            boolean playerExists = false;
            try {
                playerExists = playerDao.contains(player);
            } catch (Exception e){
            }
            if (!playerExists) {
                try {
                  playerDao.addPlayer(player);  
                } catch (Exception e){
                }
            } else {
                try {
                    playerDao.updateBestScore(player);
                } catch (Exception e) {
                    
                }
            }    
            mainMenu(stage);
        });
        healthbar.setFont(Font.font("Impact", 30));
        scoreLabel.setFont(Font.font("Impact", 30));
        endText.setFont(Font.font("Impact", 100));
        hbox.getChildren().addAll(healthbar, scoreLabel);
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setSpacing(30);
        hbox.setPadding(new Insets(50));
        root.getChildren().addAll(hbox, endText);
        int score = player.getScore();
        int hp = player.getHp();
        
        Scene scene = new Scene(root, width, height);
        
        logic.setScreenAndPane(scene, root);
        logic.setupClasses();
        logic.addAssetsToPane();
        logic.setUpControls();
        
        hbox.toFront();
        endText.toFront();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                logic.update();
                if (player.getScore() != score) {
                    scoreLabel.setText("Score: " + player.getScore());
                }
                if (player.getHp() != hp) {
                    healthbar.setText("Hp: " + player.getHp() + "/" + player.getMaxHp());
                }
                if (logic.stopGame()) {
                    this.stop();
                    if (player.alive() == false) {
                        gameOverOptions(endText, quitB, scene, root);
                    } else {
                        winningOptions(endText, nextB, quitB, scene, root);
                    }
                }
            }
        };timer.start();
        return scene;
    }
    private void winningOptions (Label label, Button next, Button back, Scene scene, Pane pane) {
        label.relocate(scene.getWidth() / 3, scene.getHeight() / 3);
        label.setText("You survived!");
        VBox vbox = new VBox(label, next, back);
        vbox.setSpacing(20);
        vbox.setLayoutX(scene.getWidth() / 3);
        vbox.setLayoutY(scene.getHeight() / 3);
        pane.getChildren().add(vbox);
    }
    private void gameOverOptions (Label label, Button back, Scene scene, Pane pane) {
        label.relocate(scene.getWidth() / 3, scene.getHeight() / 3);
        label.setText("Game over!");
        VBox vbox = new VBox(label, back);
        vbox.setSpacing(20);
        vbox.setLayoutX(scene.getWidth() / 3);
        vbox.setLayoutY(scene.getHeight() / 3);
        pane.getChildren().add(vbox);
    }
   
    
    
    
}
