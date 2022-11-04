package com.jjakubowski.gameOfLife;

public class GameBoard
{
    private Cell[][] grid;
    private int gridSize;

    public GameBoard(int gridSize)
    {
        this.gridSize = gridSize;
        this.grid = new Cell[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                grid[i][j] = new Cell();
            }
        }
    }
    public int getGridSize()
    {
        return gridSize;
    }
    public Cell[][] getGrid() {
        return grid;
    }

    public void printGrid()
    {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j].isAlive()) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }
}
