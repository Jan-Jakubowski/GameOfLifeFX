package com.jjakubowski.gameOfLife;

import javafx.scene.layout.Pane;

public class Grid extends Pane
{
    int rows;
    int columns;
    double width;
    double height;
    Cell[][] cells;
    public Grid( int columns, int rows, double width, double height)
    {
        this.columns = columns;
        this.rows = rows;
        this.width = width;
        this.height = height;
        cells = new Cell[rows][columns];
    }

    public void add(Cell cell, int column, int row) // adding cells to array and to UI grid
    {
        cells[row][column] = cell;

        double w = width / columns;
        double h = height / rows;
        double x = w * column;
        double y = h * row;

        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(w);
        cell.setPrefHeight(h);

        getChildren().add(cell);
    }
    public Cell getCell(int column, int row)
    {
        return cells[row][column];
    }
    public void unhighlight() // unhiglight everything
    {
        for( int row=0; row < rows; row++) {
            for( int col=0; col < columns; col++)
            {
                cells[row][col].unhighlight();
            }
        }
    }
    public void highlightRandom()
    {
        for( int row=0; row < rows; row++) {
            for( int col=0; col < columns; col++)
            {
                if(Math.random() > 0.5)
                    cells[row][col].highlight();
                else
                    cells[row][col].unhighlight();
            }
        }
    }
}
