package com.jjakubowski.gameOfLife;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        Button changeButton = new Button("change css");

        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("css/mainViewAppGREEN-BLACK.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("GAME OF LIFE :> by JANEK JAKUBOWSKI");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}