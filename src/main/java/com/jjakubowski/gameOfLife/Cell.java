package com.jjakubowski.gameOfLife;
import javafx.scene.layout.StackPane;
public class Cell extends StackPane
{
    int column;
    int row;
    boolean alive;
    public Cell(int column, int row) {

        this.column = column;
        this.row = row;
        alive = false;

        getStyleClass().add("cell");
        setOpacity(0.9);
    }
    public void highlight()
    {
        this.alive = true;
        // to be sure that it's the only style
        getStyleClass().remove("cell-highlight");
        // add style
        getStyleClass().add("cell-highlight");
    }
    public void unhighlight()
    {
        this.alive = false;
        getStyleClass().remove("cell-highlight");
    }
    public void hoverHighlight()
    {
        // to be sure that it's the only style
        getStyleClass().remove("cell-hover-highlight");
        // add style
        getStyleClass().add("cell-hover-highlight");
    }
    public void hoverUnhighlight()
    {
        getStyleClass().remove("cell-hover-highlight");
    }
    public String toString()
    {
        return this.column + "/" + this.row;
    }
}