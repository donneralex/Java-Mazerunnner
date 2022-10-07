package com.donner.mazerunner;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MazeController {

    boolean running =false, goNorth = false, goSouth = false, goEast = false, goWest =false;
    public Stage stage;
    public Scene scene;

    @FXML
    Circle player;

    @FXML
    Button button;

    @FXML
    GridPane pane;

    int width = 10;
    int length= 8;

    Maze maze;

    @FXML
    public void initialize()
    {
        maze = new Maze(width,length);


//        for (int x = 0 ; x < 10; x++){
//            for(int y = 0; y < 10; y++){
//                StackPane panel = new StackPane();
//
//                panel.setStyle("-fx-background-color: blue");
//                GridPane.setConstraints(panel, x,y);
//                pane.getChildren().add(panel);
//            }
//        }
        while(maze.generationThread.isAlive()){}
        printMaze(maze);






    }



    public void printMaze(Maze maze)
    {
        MazeCell[][] mazeCells = maze.getMaze();

        for(int x = 0; x<width;x++)
        {
            ColumnConstraints con = new ColumnConstraints();
            con.setPrefWidth(500);

            pane.getColumnConstraints().add(con);
        }
        for(int y = 0; y<length;y++)
        {
            RowConstraints con = new RowConstraints();
            con.setPrefHeight(500);

            pane.getRowConstraints().add(con);
        }

        for (int x = 0 ; x < width; x++){
            for(int y = 0; y < length; y++){
                // Top Right Bottom Left
                int[] borders = mazeCells[x][y].borders;
                CellType type = mazeCells[x][y].cellType;

                ArrayList<String> border = new ArrayList<String>();
                for (int bord :
                        borders) {
                    if(bord ==1)border.add("solid");
                    else{border.add("hidden");}

                }

                StackPane panel = new StackPane();

                panel.setStyle("-fx-border-style: " + border.get(0) +" "+border.get(1)+" "+ border.get(2)+" "+border.get(3));
                if(type == CellType.start) panel.setStyle("-fx-background-color: blue"+"; -fx-border-style: " + border.get(0) +" "+border.get(1)+" "+ border.get(2)+" "+border.get(3));

                GridPane.setConstraints(panel, x,y);
                pane.getChildren().add(panel);
            }
        }

    }

    public void setScene(Scene scene) {
        this.scene = scene;

        moveHeroTo(25,145);     // Maze hardcoded

        scene.setOnKeyPressed(keyEvent ->{
            switch (keyEvent.getCode()) {
                case UP:
                case W:
                    goNorth = true; break;
                case DOWN:
                case S:
                    goSouth = true; break;
                case LEFT:
                case A:
                    goWest  = true; break;
                case RIGHT:
                case D:
                    goEast  = true; break;

                case F5: moveHeroTo(25,145); break;

            }

        });

        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                case W:
                    goNorth = false;
                    break;
                case DOWN:
                case S:
                    goSouth = false;
                    break;
                case LEFT:
                case A:
                    goWest = false;
                    break;
                case RIGHT:
                case D:
                    goEast = false;
                    break;

            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) dy -= 1;
                if (goSouth) dy += 1;
                if (goEast)  dx += 1;
                if (goWest)  dx -= 1;
                if (running) { dx *= 3; dy *= 3; }

                moveHeroBy(dx, dy);
            }
        };
        timer.start();
    }

    private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;



        double x =player.getLayoutX() + dx;
        double y =player.getLayoutY() + dy;

        player.setLayoutX(x);
        player.setLayoutY(y);

        System.out.println("X"+player.getLayoutX()+ " Y:" +player.getLayoutY());
        MazeCell curr = maze.getCurrrentCellAndCheckCollision(x,y);
        if(curr == null){ moveHeroTo(25,145);}
        else{System.out.println("Current Cell: "+curr.x + " "+ curr.y);}
        //moveHeroTo(x, y);
    }

    private void moveHeroTo(double x, double y) {
        player.setLayoutX(x);
        player.setLayoutY(y);

    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}