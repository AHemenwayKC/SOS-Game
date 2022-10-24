package com.example;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GameBoard {

   private GridPane gameBoard;
   private TextField boardSpace = new TextField();

   public void boardSize() {
      gameBoard.add(boardSpace, 0, 0);
      gameBoard.add(boardSpace, 0, 1);
      gameBoard.add(boardSpace, 0, 2);
      gameBoard.add(boardSpace, 1, 0);
      gameBoard.add(boardSpace, 1, 1);
      gameBoard.add(boardSpace, 1, 2);
      gameBoard.add(boardSpace, 2, 0);
      gameBoard.add(boardSpace, 2, 1);
      gameBoard.add(boardSpace, 2, 2);
   }

}