package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

public class PrimaryController implements Initializable {

   private Square[][] squares;


   @FXML
   private BorderPane borderpane;

   @FXML
   public static ArrayList<Square> boardSpaces = new ArrayList<Square>();

   @FXML
   private Button newGame;

   @FXML
   private ComboBox<String> gameMode;
   private String[] gameModes = {"Simple", "General"};

   @FXML
   private ComboBox<String> gameSize;
   private String[] gameSizes = {"3", "4", "5", "6", "7", "8", "9"};

   @FXML
   public static RadioButton rS, rO, bS, bO;
   public static Label redScore = new Label("0"), blueScore = new Label("0");
   public static String redMove = new String("S"), blueMove = new String("S");

   @FXML
   private Label turnLabel;
   public static Label playerTurn = new Label("R");


   /*____________________________________*/
   /* ----- START Red Player Logic ----- */
   public static String getRedMove() {
      if (rO.isSelected()) {
         redMove = "O";
      }
      else if (rS.isSelected()) {
         redMove = "S";
      }
      return redMove;
   }

   public static void setRedMove(String move) {
      redMove = move;
   }
   
   public static void updateRedMove(ActionEvent event) {
      if (rO.isSelected()) {
         setRedMove("O");
      }
      else if (rS.isSelected()) {
         setRedMove("S");
      }
   }

   // UNFINISHED
   public static void setRedScore(String score) {
      redScore.setText(score);
   }
   /* ----- END Red Player Logic ----- */
   /*__________________________________*/




   /*_____________________________________*/
   /* ----- START Blue Player Logic ----- */
   public static String getBlueMove() {
      return blueMove;
   }

   public static void setBlueMove(String move) {
      blueMove = move;
   }

   public void updateBlueMove(ActionEvent event) {
      if (bO.isSelected()) {
         blueMove = "O";
      }
      else if (bS.isSelected()) {
         blueMove = "S";
      }
   }

   // UNFINISHED
   public static void setBlueScore(String score) {
      blueScore.setText(score);
   }
   /* ----- END Blue Player Logic ----- */
   /*__________________________________*/




   /*_______________________________*/
   /* ----- START Game Logic ----- */

   public void setGameSize(ActionEvent event) {
      GridPane board = new GridPane();

      int size = Integer.parseInt(gameSize.getValue());

      squares = new Square[size][size];

      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {

            board.add(squares[i][j] = new Square(i, j), j, i);
         }
      }
      borderpane.setCenter(board);
   }

   public static void setPlayerTurn(String turn) {
      playerTurn.setText(turn);
   }

   public static Label getPlayerTurn() {
      return playerTurn;
   }

   public static void makeMove (Square id) {
      if (boardSpaces.contains(id)) {
         if (playerTurn.getText() == "R") {
            Text move = new Text(redMove);
            move.setFont(Font.font("Arial",36));
            move.autosize();
            move.setFill(Color.RED);
            id.getChildren().add(move);
            Square.setAlignment(move, Pos.CENTER);
            setPlayerTurn("B");
         }
         else if (playerTurn.getText() == "B") {
            Text move = new Text(blueMove);
            move.setFont(Font.font("Arial",36));
            move.autosize();
            move.setFill(Color.BLUE);
            id.getChildren().add(move);
            Square.setAlignment(move, Pos.CENTER);
            setPlayerTurn("R");
         }
      }
      boardSpaces.remove(id);
   }
   /* ----- END Game Logic ----- */
   /*____________________________*/



   @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
      gameMode.getItems().addAll(gameModes);
      gameSize.getItems().addAll(gameSizes);
   }
}