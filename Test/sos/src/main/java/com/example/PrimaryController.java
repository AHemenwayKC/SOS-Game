package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

public class PrimaryController implements Initializable {

   @FXML
   private Button newGame;

   @FXML
   private ComboBox<String> gameMode;
   private String[] gameModes = {"Simple", "General"};

   @FXML
   private ComboBox<String> gameSize;
   private String[] gameSizes = {"3x3", "6x6", "9x9"};

   @FXML
   private static GameBoard gameBoard = new GameBoard();

   public int getGameSize(ActionEvent event) {
      int gBoardSize = 3;
      String gSize = gameSize.getValue();
      if (gSize == "6x6") {
         gBoardSize = 6;
      }
      if (gSize == "9x9") {
         gBoardSize = 9;
      }

      return gBoardSize;
   }
   
   @FXML
   public static TextField textField = new TextField();

   public static void gameBoard(GridPane board) {
      int gBoardSize = 9;
      for (int i = 0; i < gBoardSize; i++) {
          TextField textField = new TextField();
          textField.setAlignment(Pos.CENTER);
          board.add(textField, 0, i);
      }
      board.setAlignment(Pos.CENTER);
  }

   @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
      gameMode.getItems().addAll(gameModes);
      gameSize.getItems().addAll(gameSizes);

   }



}