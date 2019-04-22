/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;


import shooter.logic.PlayerMovement;
import shooter.logic.Enemy;
import shooter.logic.Bullet;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shooter.logic.GameLogic;
import shooter.logic.LevelHandler;
import shooter.logic.ObjectHandler;
import shooter.logic.Player;


/**
 *
 * @author jajuuso
 */
public class Ui extends Application{
    int width;
    int height;
   
    
    public Ui() {
        this.width = 1980;
        this.height = 1080;
        
        
    }
    @Override
    public void start(Stage stage) throws Exception {
        VBox box = new VBox();
        Text title = new Text("Top Down Shooter");
        title.setFont(Font.font("Impact", 30));
        Button gameButton = new Button("New Game");
        Button exitButton = new Button("Exit");
        
        box.getChildren().addAll(title, gameButton, exitButton);
        box.setPrefSize(600, 400);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        
       
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
        
        gameButton.setOnAction(event -> {
            newGame();
        });
        exitButton.setOnAction(event -> {
            stage.close();
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void gameScreen(Player player, GameLogic logic) {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        Pane root = new Pane();
        HBox hbox = new HBox();
        Label healthbar = new Label("Hp: " + player.getHp() + "/" + player.getMaxHp());
        Label scoreLabel = new Label("Score: " + player.getScore());
        Label endText = new Label();
        
        healthbar.setFont(Font.font("Impact", 20));
        scoreLabel.setFont(Font.font("Impact", 20));
        endText.setFont(Font.font("Impact", 100));
        hbox.getChildren().addAll(healthbar, scoreLabel);
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setSpacing(25);
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
                        endText.relocate(scene.getWidth() / 3, scene.getHeight() / 3);
                        endText.setText("Game over!");
                    } else {
                        endText.relocate(scene.getWidth() / 3, scene.getHeight() / 3);
                        endText.setText("You survived!");
                        nextLevel(stage, player, logic);
                    }
                }
            }
        };timer.start();
        
    }
    public void newGame() {
        Stage stage = new Stage();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        
        Label label = new Label("Name: ");
        TextField nameField = new TextField();
        hbox.getChildren().addAll(label, nameField);
        
        Button startButton = new Button("Start!");
        Label errorMessage = new Label();
        vbox.getChildren().addAll(hbox, startButton, errorMessage);
        vbox.setSpacing(20);
        vbox.setPrefSize(400, 200);
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.setPadding(new Insets(20));
        
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
        
        startButton.setOnAction(event ->{
            if (nameField.getText().isEmpty()) {
                errorMessage.setText("You need to enter a name for the player!");
            } else {
                Player player = new Player(nameField.getText());
                GameLogic logic = new GameLogic(player);
                stage.close();
                gameScreen(player, logic);
            }
        });
    }
    public void nextLevel(Stage stageToClose, Player player, GameLogic logic) {
        Stage stage = new Stage();
        VBox vbox = new VBox();
        
        Button toNextLevel = new Button("Next level!");
        Button quitGame = new Button("Quit game");
        vbox.getChildren().addAll(toNextLevel, quitGame);
        vbox.setSpacing(20);
        vbox.setPrefSize(400, 200);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
        
        toNextLevel.setOnAction(event ->{
            stageToClose.close();
            stage.close();
            logic.moveToNextLevel();
            gameScreen(player, logic);
            
        });
        quitGame.setOnAction(event ->{
            stageToClose.close();
            stage.close();
        });
    }
    
        
}
