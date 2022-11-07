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
            Thread.sleep(1000);
            cells[counter++][0].highlight();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void nextGeneration()
    {

//        int[][] future = new int[M][N];
        // Loop through every cell
//        for (int l = 0; l < M; l++)
//        {
//            for (int m = 0; m < N; m++)
//            {
//                // finding no Of Neighbours that are alive
//                int aliveNeighbours = 0;
//                for (int i = -1; i <= 1; i++)
//                    for (int j = -1; j <= 1; j++)
//                        if ((l+i>=0 && l+i<M) && (m+j>=0 && m+j<N))
//                            aliveNeighbours += grid[l + i][m + j];
//
//                // The cell needs to be subtracted from
//                // its neighbours as it was counted before
//                aliveNeighbours -= grid[l][m];
//
//                // Implementing the Rules of Life
//
//                // Cell is lonely and dies
//                if ((grid[l][m] == 1) && (aliveNeighbours < 2))
//                    future[l][m] = 0;
//
//                    // Cell dies due to over population
//                else if ((grid[l][m] == 1) && (aliveNeighbours > 3))
//                    future[l][m] = 0;
//
//                    // A new cell is born
//                else if ((grid[l][m] == 0) && (aliveNeighbours == 3))
//                    future[l][m] = 1;
//
//                    // Remains the same
//                else
//                    future[l][m] = grid[l][m];
//            }
//        }
//
//        System.out.println("Next Generation");
//        for (int i = 0; i < M; i++)
//        {
//            for (int j = 0; j < N; j++)
//            {
//                if (future[i][j] == 0)
//                    System.out.print(".");
//                else
//                    System.out.print("*");
//            }
//            System.out.println();
//        }
//    }
    }
}
