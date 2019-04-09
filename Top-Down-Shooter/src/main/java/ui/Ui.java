/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import java.util.ArrayList;
import java.util.Random;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Player;

/**
 *
 * @author jajuuso
 */
public class Ui extends Application{
    int width;
    int height;
    ImageView imageview;
    
    public Ui() {
        this.width = 1280;
        this.height = 720;
        this.imageview = new ImageView("player.png");
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
    public void gameScreen(Player player) {
        Stage stage = new Stage();
        Pane root = new Pane();
        HBox hbox = new HBox();
        Label healthbar = new Label("Hp: " + player.getHp() + "/" + player.getMaxHp());
        Label gameOver = new Label();
        
        healthbar.setFont(Font.font("Impact", 20));
        gameOver.setFont(Font.font("Impact", 100));
        hbox.getChildren().add(healthbar);
        hbox.setAlignment(Pos.TOP_LEFT);
        root.getChildren().addAll(hbox, imageview, gameOver);
        ArrayList<Enemy> enemies = createEnemies(10);
        for (int i = 0; i < enemies.size(); i ++) {
            root.getChildren().add(enemies.get(i).getShape());
        }
        imageview.setLayoutX(600);
        imageview.setLayoutY(300);
        
        Scene scene = new Scene(root, width, height);
        PlayerMovement movement = new PlayerMovement(scene, imageview);
        movement.keyCommands();
        stage.setScene(scene);
        stage.show();
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movement.movePlayerIcon();
                for (Enemy e : enemies) {
                    e.chasePlayer(movement.getPlayerLocation());
                    if (e.playerIsHit(player, imageview)) {
                        healthbar.setText("Hp: " + player.getHp() + "/" + player.getMaxHp());
                    }
                }
                if (player.alive() == false) {
                    this.stop();
                    gameOver.relocate(400, 310);
                    gameOver.setText("Game over!");
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
    public ArrayList<Enemy> createEnemies(int number) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        
        for (int i = 0; i < number; i ++) {
            Random rx = new Random();
            Random ry = new Random();
            double x = rx.nextInt(width);
            double y = ry.nextInt(height);
            
            Enemy e = new Enemy(x, y);
            enemies.add(e);
            
        }
        return enemies;
    }    
}
