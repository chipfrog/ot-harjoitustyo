/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.ui;


import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
        this.width = 1280;
        this.height = 720;
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
        
        ArrayList<EnemyMovement> enemies = createEnemies(10);
        for (int i = 0; i < enemies.size(); i ++) {
            root.getChildren().add(enemies.get(i).getShape());
        }
        imageview.setLayoutX(600);
        imageview.setLayoutY(300);
        
        Scene scene = new Scene(root, width, height);
        PlayerMovement movement = new PlayerMovement(scene, imageview);
        movement.keyCommands();
        ArrayList<Bullet> bullets = new ArrayList<>();
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent m) {
                Point2D target = new Point2D(m.getX(), m.getY());
                Bullet b = new Bullet(movement.getPlayerLocation(), target);
                bullets.add(b);
                root.getChildren().add(b.getShape());
                b.getShape().relocate(movement.getPlayerLocation().getX(), movement.getPlayerLocation().getY());
            }
        });
        hbox.toFront();
        endText.toFront();
        stage.setScene(scene);
        stage.show();
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movement.movePlayerIcon();
                shoot(bullets);
                for (EnemyMovement e : enemies) {
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
                removeDeadObjects(bullets, enemies, root); 
                if (player.alive() == false) {
                    this.stop();
                    endText.relocate(400, 310);
                    endText.setText("Game over!");
                }
                if (enemies.isEmpty()) {
                    this.stop();
                    endText.relocate(400, 310);
                    endText.setText("You survived!");
                }
            }
        };timer.start();
    }
    public void shoot(ArrayList<Bullet> bullets) {
        for (Bullet b : bullets) {
            double angle = Math.atan2(b.target.getX() - b.bulletLocation.getX(), b.target.getY() - b.bulletLocation.getY());
            double xV = 3 * Math.sin(angle);
            double yV = 3 * Math.cos(angle);
            b.getShape().relocate(b.getShape().getLayoutX() + xV, b.getShape().getLayoutY() + yV);
            
            if(b.getShape().getLayoutX() > width || b.getShape().getLayoutY() > height) {
                b.setDead();
            }
        }
    }
    public void removeDeadObjects(ArrayList<Bullet> bullets, ArrayList<EnemyMovement> enemies, Pane pane) {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        ArrayList<EnemyMovement> enemiesToRemove = new ArrayList<>();
        
        for (Bullet b : bullets) {
            if (b.alive == false) {
                pane.getChildren().remove(b.getShape());
                bulletsToRemove.add(b);
            }
        }
        for (EnemyMovement e: enemies) {
            if (e.alive == false) {
                pane.getChildren().remove(e.getShape());
                enemiesToRemove.add(e);
            }
        }
        bullets.removeAll(bulletsToRemove);
        enemies.removeAll(enemiesToRemove);
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
    public ArrayList<EnemyMovement> createEnemies(int number) {
        ArrayList<EnemyMovement> enemies = new ArrayList<>();
        
        for (int i = 0; i < number; i ++) {
            Random rx = new Random();
            Random ry = new Random();
            double x = rx.nextInt(width);
            double y = ry.nextInt(height);
            
            EnemyMovement e = new EnemyMovement(x, y);
            enemies.add(e);
            
        }
        return enemies;
    }    
}
