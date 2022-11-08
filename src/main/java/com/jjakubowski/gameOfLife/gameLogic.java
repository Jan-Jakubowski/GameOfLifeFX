package com.jjakubowski.gameOfLife;
import javafx.animation.AnimationTimer;

import java.util.Arrays;
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
            Thread.sleep(100); // 0,1s -> new generation
            nextGeneration();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void nextGeneration()
    {
//        System.out.println("nextgen");


        //copy of old array to test
        Cell[][] oldCopy = new Cell[rows][columns];
        for (int i = 0; i < rows; i++)
            oldCopy[i] = Arrays.copyOf(cells[i], columns);



        Cell[][] newGen = new Cell[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                newGen[i][j] = new Cell(i,j);
                cells[i][j].alive = grid.cells[i][j].alive;
            }

        }

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                int aliveNeighbours = countAliveNeighbours(x, y);
//                System.out.println("nbours: " + aliveNeighbours);

                if (getState(x, y) == 1) {
                    if (aliveNeighbours < 2) {
//                        System.out.println("less than 2 nbours()");
                        newGen[x][y].alive = false;
                    } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
//                        System.out.println("3 or 2 nbours()");
                        newGen[x][y].alive = true;
                    } else if (aliveNeighbours > 3) {
//                        System.out.println("3 nbours()");
                        newGen[x][y].alive = false;
                    }
                } else {
                    if (aliveNeighbours == 3) {
//                        System.out.println("dead, 3 nbours");
                        newGen[x][y].alive = true;
                    }
                }

                if(newGen[x][y].alive)
                {
                    grid.getCell(y,x).highlight();
                }
                else
                {
                    grid.getCell(y,x).unhighlight();
                }
            }
        }
        this.cells = newGen;
    }
    public int countAliveNeighbours(int x, int y) {
        int count = 0;

        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);

        return count;
    }
    public int getState(int x, int y)
    {
        if (x < 0 || x >= rows) {
            return 0;
        }

        if (y < 0 || y >= columns) {
            return 0;
        }
        if(this.cells[x][y].alive)
            return 1;
        return 0;
    }
    public static <T> boolean equals(Cell [][] a, Cell [][] b) {
        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a.length; j++)
            {
                if(a[j][i].alive != b[j][i].alive)
                    return false;
            }
        }

        return true;
    }


}
