package com.donner.mazerunner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class MazeApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("maze-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Maze");
        MazeController controller = (MazeController) fxmlLoader.getController();
        controller.setScene(scene);
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();


    }




    public static void main(String[] args) {
        launch();
    }
}