package com.jjakubowski.gameOfLife;

import javafx.animation.AnimationTimer;

import java.util.concurrent.TimeUnit;

public class gameLogic extends AnimationTimer
{
    Grid grid;
    int columns;
    int rows;
    Cell[][] cells;
    int counter = 0;

    public gameLogic(Grid grid) {
        this.grid = grid;
        this.columns = grid.columns;
        this.rows = grid.rows;
        this.cells = grid.cells;
    }

    public void handle(long now)
    {
        try {
            Thread.sleep(1000); // 0,1s -> new generation
            nextGeneration();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void nextGeneration()
    {
        for(int y=0; y<rows; y++)
        {
            for (int x = 0; x<columns; x++)
            {

            }
        }
    }
}
