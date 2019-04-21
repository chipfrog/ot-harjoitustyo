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
import javafx.stage.Stage;
import shooter.logic.ObjectHandler;
import shooter.logic.Player;


/**
 *
 * @author jajuuso
 */
public class Ui extends Application{
    int width;
    int height;
    ImageView imageview;
    
    public Ui() {
        this.width = 1980;
        this.height = 1080;
        this.imageview = new ImageView("velho.png");
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
        
        Player player = new Player("John");
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
    public void gameScreen(Player player) {
        Stage stage = new Stage();
        stage.setResizable(true);
        Pane root = new Pane();
        HBox hbox = new HBox();
        Label healthbar = new Label("Hp: " + player.getHp() + "/" + player.getMaxHp());
        Label score = new Label("Score: " + player.getScore());
        Label endText = new Label();
        
        healthbar.setFont(Font.font("Impact", 20));
        score.setFont(Font.font("Impact", 20));
        endText.setFont(Font.font("Impact", 100));
        hbox.getChildren().addAll(healthbar, score);
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setSpacing(25);
        root.getChildren().addAll(hbox, imageview, endText);
        imageview.setLayoutX(600);
        imageview.setLayoutY(300);
        
        Scene scene = new Scene(root, width, height);
        ArrayList<Bullet> bullets = new ArrayList<>();
        ObjectHandler objectHandler = new ObjectHandler(scene);
        PlayerMovement movement = new PlayerMovement(scene, imageview);
        movement.keyCommands();
        movement.mouseControl(root, bullets);
        ArrayList<Enemy> enemies = new ArrayList<>();
        Level level = new Level(enemies, 10, 7, scene);
        
        hbox.toFront();
        endText.toFront();
        stage.setScene(scene);
        stage.show();
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                level.spawnWave(root);
                movement.movePlayerIcon();
                objectHandler.shootBullets(bullets);
                
                for (Enemy e : enemies) {
                    for (Bullet b : bullets) {
                        if(e.isHit(b)) {
                            player.increaseScore();
                            score.setText("Score: " + player.getScore());
                        }
                    }
                    e.chasePlayer(movement.getPlayerLocation());
                    if (e.playerIsHit(player, imageview)) {
                        healthbar.setText("Hp: " + player.getHp() + "/" + player.getMaxHp());
                    }
                }
                objectHandler.removeDeadBullets(bullets, root);
                objectHandler.removeDeadEnemies(enemies, root);
                
                if (player.alive() == false) {
                    this.stop();
                    endText.relocate(400, 310);
                    endText.setText("Game over!");
                }
                if (level.levelContinues()) {
                    this.stop();
                    endText.relocate(400, 310);
                    endText.setText("You survived!");
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
        vbox.setSpacing(10);
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
                stage.close();
                gameScreen(player);
            }
        });
    }    
        
}
