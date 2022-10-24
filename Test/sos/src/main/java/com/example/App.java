// ***MAIN JAVA FILE***

package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));

        
        scene = new Scene(root);
        stage.setTitle("SOS Game");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();


    }
    public static void main(String[] args) {
        launch();
    }

}