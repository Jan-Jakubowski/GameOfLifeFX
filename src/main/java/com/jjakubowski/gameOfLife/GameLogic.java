package com.jjakubowski.gameOfLife;
import javafx.animation.AnimationTimer;

public class GameLogic extends AnimationTimer
{
    Grid grid;
    int columns;
    int rows;
    Cell[][] cells;
    public GameLogic(Grid grid) {
        this.grid = grid;
        this.columns = grid.columns;
        this.rows = grid.rows;
        this.cells = grid.cells;
    }

    public void handle(long now) // method form AnimationTimer class, what happens every iteration
    {
        try
        {
            Thread.sleep(100); // 1000ms = 1s, adjustable to preferences
            nextGeneration();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
    public void nextGeneration()
    {
        Cell[][] newGen = new Cell[rows][columns]; // this array will be shown in next generation
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                newGen[i][j] = new Cell(i,j);
                // handling clicked cells to not lose them, because click changes are made in grid.cells
                cells[i][j].alive = grid.cells[i][j].alive;
            }
        }

        for (int x = 0; x < rows; x++) // new generations is set on newGen
        {
            for (int y = 0; y < columns; y++) {
                int aliveNeighbours = countAliveNeighbours(x, y);

                if (getState(x, y) == 1)
                {
                    if (aliveNeighbours < 2) // cell dies - underpopulation
                        newGen[x][y].alive = false;
                    else if (aliveNeighbours == 2 || aliveNeighbours == 3) // cell stays alive
                        newGen[x][y].alive = true;
                    else if (aliveNeighbours > 3) // cell dies - overpopulation
                        newGen[x][y].alive = false;
                }
                else
                {
                    if (aliveNeighbours == 3) // new cell is born
                        newGen[x][y].alive = true;
                }
                // setting new colors on the shown grid
                if(newGen[x][y].alive)
                    grid.getCell(y,x).highlight();
                else
                    grid.getCell(y,x).unhighlight();
            }
        }
        this.cells = newGen;
    }
    public int countAliveNeighbours(int x, int y)
    {
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
    public int getState(int x, int y) //returns if cell is alive or not
    {
        if (x < 0 || x >= rows)
            return 0;
        if (y < 0 || y >= columns)
            return 0;
        if(this.cells[x][y].alive)
            return 1;

        return 0;
    }
}
