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
    public static String schemeColor;

    @FXML
    private VBox vBox1;

    public void initialize() // method takes care of real time changing color scheme on mainView
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
                        schemeColor = radioButton.getText();
                    });
                }
            }
        }
    }
    @FXML
    public void start()
    {
        int rows = 40;
        int columns = 40;
        double width = 900;
        double height = 850;
        try
        {
            StackPane root = new StackPane();
            Grid grid = new Grid( columns, rows, width, height);
            GameLogic game = new GameLogic(grid);
            MouseGestures mg = new MouseGestures(game);
            // fill grid randomly
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {

                    Cell cell = new Cell(column, row);
                    mg.makePaintable(cell);

                    if(Math.random() > 0.5) // number of alive cells at start can be adjusted here
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

