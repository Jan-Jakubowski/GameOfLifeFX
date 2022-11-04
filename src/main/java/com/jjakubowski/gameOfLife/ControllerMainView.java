package com.jjakubowski.gameOfLife;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
}

