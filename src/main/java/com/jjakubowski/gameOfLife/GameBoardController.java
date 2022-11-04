package com.jjakubowski.gameOfLife;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class GameBoardController
{
    @FXML
    public GridPane gridPane;



    private void initializeGridPane(){

        int gridSize = 750;
        int cellSize = 25;

        // Set up the rows and columns.
        for (int i = 0; i < gridSize; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(cellSize));
            gridPane.getRowConstraints().add(new RowConstraints(cellSize));
        }

        //Add an image to each cell with on OnClick mouse event.
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                initializeGridRectangles(i,j);
            }
        }
    }



    public void initializeGridRectangles(int row, int column)
    {
        int cellSize = 25;

        // All cells will initially be dead, build a Rectangle for each.
        Rectangle cellRect = new Rectangle();
        cellRect.setHeight(cellSize);
        cellRect.setWidth(cellSize);
        cellRect.setFill(Color.WHITE);
        cellRect.setStroke(Color.web("#F6F6F6"));
        cellRect.setStrokeType(StrokeType.INSIDE);
        cellRect.setStrokeWidth(0.5);
        cellRect.setSmooth(true);
    }

}