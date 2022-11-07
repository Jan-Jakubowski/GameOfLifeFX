package com.jjakubowski.gameOfLife;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Iterator;

public class ControllerMainView
{

    @FXML
    private VBox vBox1;

    public void initialize()
    {
        Iterator<Node> nodes = vBox1.getChildren().iterator();
        while (nodes.hasNext())
        {
            while(nodes.hasNext())
            {
                Node node = nodes.next();
                if(node instanceof RadioButton)
                {
                    RadioButton radioButton = (RadioButton) node;
                    radioButton.setOnAction(actionEvent -> {
                        Application.setUserAgentStylesheet(null);
                        radioButton.getScene().getStylesheets().clear();
                        radioButton.getScene().getStylesheets().add(String.valueOf(getClass().getResource("css/mainViewApp" + radioButton.getText() + ".css")));
                    });
                }
            }
        }
    }


    @FXML
    public void newWindowButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = FXMLLoader.load(getClass().getResource("GameBoardView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void start()
    {
        int rows = 15;
        int columns = 20;
        double width = 800;
        double height = 600;

        try {
            StackPane root = new StackPane();
            // create grid
            Grid grid = new Grid( columns, rows, width, height);
            gameLogic game = new gameLogic(grid);
            MouseGestures mg = new MouseGestures(game);
            // fill grid
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {

                    Cell cell = new Cell(column, row);
                    mg.makePaintable(cell);

                    if(Math.random() > 0.5)
                        cell.highlight();
                    grid.add(cell, column, row);
                }
            }
            game.start();

            root.getChildren().addAll(grid);
            // create scene and stage
            Scene scene = new Scene(root, width, height);
            scene.getStylesheets().add(getClass().getResource("css/grid.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

