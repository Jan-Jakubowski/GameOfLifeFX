package com.jjakubowski.gameOfLife;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;

public class MouseGestures
{
    private GameLogic gameLogic;
    public MouseGestures(GameLogic gameLogic)
    {
        this.gameLogic = gameLogic;
    }

    static boolean showHoverCursor = false; // off hovering effect for now
    public void makePaintable( Node node)
    {
        if(showHoverCursor) {
            node.hoverProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if( newValue) {
                        ((Cell) node).hoverHighlight();
                    } else {
                        ((Cell) node).hoverUnhighlight();
                    }
                }
            });
        }
        node.setOnMousePressed(onMousePressedEventHandler);
        node.setOnDragDetected(onDragDetectedEventHandler);
        node.setOnMouseReleased(onMouseReleasedEventHandler);
        node.setOnMouseDragEntered(onMouseDragEnteredEventHandler);
        node.setOnMouseDragReleased(onMouseReleasedEventHandler);

    }

    EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
        gameLogic.stop();
        Cell cell = (Cell) event.getSource();

        if( event.isPrimaryButtonDown()) {
            cell.highlight();
        } else if( event.isSecondaryButtonDown()) {
            cell.unhighlight();
        }
    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
        gameLogic.stop();
        PickResult pickResult = event.getPickResult();
        Node node = pickResult.getIntersectedNode();

        if( node instanceof Cell) {
            Cell cell = (Cell) node;

            if( event.isPrimaryButtonDown()) {
                cell.highlight();
            } else if( event.isSecondaryButtonDown()) {
                cell.unhighlight();
            }
        }
        if(!gameLogic.paused)
            gameLogic.start();
    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> {
        if(!gameLogic.paused)
            gameLogic.start();
    };

    EventHandler<MouseEvent> onDragDetectedEventHandler = event -> {
        gameLogic.stop();

        Cell cell = (Cell) event.getSource();
        cell.startFullDrag();
    };

    EventHandler<MouseEvent> onMouseDragEnteredEventHandler = event -> {
        Cell cell = (Cell) event.getSource();
        gameLogic.stop();
        if(event.isPrimaryButtonDown())
            cell.highlight();
        else if( event.isSecondaryButtonDown())
            cell.unhighlight();
    };


}
