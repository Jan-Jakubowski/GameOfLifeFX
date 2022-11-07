package com.jjakubowski.gameOfLife;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;

public class MouseGestures
{
    gameLogic gameLogic;

    public MouseGestures(gameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    static boolean showHoverCursor = false;
    public void makePaintable( Node node) {
        // that's all there is needed for hovering, the other code is just for painting
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

        node.setOnMousePressed( onMousePressedEventHandler);
        node.setOnDragDetected( onDragDetectedEventHandler);
        node.setOnMouseReleased(onMouseReleasedEventHandler);
        node.setOnMouseDragEntered(onMouseDragEnteredEventHandler);
        node.setOnMouseDragReleased(onMouseReleasedEventHandler);

    }

    EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
        System.out.println("onMousePressedEventHandler");
        Cell cell = (Cell) event.getSource();

        if( event.isPrimaryButtonDown()) {
            cell.highlight();
        } else if( event.isSecondaryButtonDown()) {
            cell.unhighlight();
        }
    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
        System.out.println("onMouseDraggedEventHandler");
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
        gameLogic.start();

    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> {
        System.out.println("onMouseReleasedEventHandler");
        gameLogic.start();
    };

    EventHandler<MouseEvent> onDragDetectedEventHandler = event -> {
        System.out.println("onDragDetectedEventHandler");
        gameLogic.stop();

        Cell cell = (Cell) event.getSource();
        cell.startFullDrag();

    };

    EventHandler<MouseEvent> onMouseDragEnteredEventHandler = event -> {
        System.out.println("onMouseDragEnteredEventHandler");
        Cell cell = (Cell) event.getSource();
        gameLogic.stop();
        if( event.isPrimaryButtonDown()) {
            cell.highlight();
        } else if( event.isSecondaryButtonDown()) {
            cell.unhighlight();
        }

    };
    //todo: event handler ze start();
}
