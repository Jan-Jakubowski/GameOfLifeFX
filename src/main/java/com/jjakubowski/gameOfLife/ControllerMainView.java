package com.jjakubowski.gameOfLife;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
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

    public void start()
    {
        Stage stage = new Stage();
        final int size = 300;
        Pane root = new Pane();
        Scene scene = new Scene(root, size, size, Color.ALICEBLUE);

        // drawing constants
        final int gap = 25; // padding around the board
        final int n = 8; // squares per row and column
        final int k = (size - 2 * gap) / n; // size of squares

        // black board (red squares added later)
        Rectangle board = new Rectangle(gap, gap, n * k, n * k);
        board.setFill(Color.BLACK);
        root.getChildren().add(board);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetY(12);
        dropShadow.setOffsetX(12);
        dropShadow.setColor(new Color(0.3, 0.3, 0.3, 1));
        board.setEffect(dropShadow);

        // Fill in the red squares.
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row % 2 == col % 2) { // Check for red position.

                    // coordinates of top-left corner
                    int x = gap + col * k;
                    int y = gap + row * k;

                    Rectangle redSquare = new Rectangle(x, y, k, k);
                    redSquare.setFill(Color.FIREBRICK);
                    root.getChildren().add(redSquare);
                }
            }
        }

        stage.setTitle("Checkerboard");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }
}

