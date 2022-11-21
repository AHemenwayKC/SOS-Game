package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
   public static String mode = "Simple";

   @FXML
   private ComboBox<String> gameSize;
   
   private String[] gameSizes = {"3", "4", "5", "6", "7", "8", "9"};

   @FXML
   private RadioButton rS, rO, bS, bO;

   @FXML
   private Label turnLabel;
   public static Label playerTurn = new Label("R");

   @FXML
   private Label scoreRed;
   @FXML
   private Label scoreBlue;

   /*____________________________________*/
   /* ----- START Red Player Logic ----- */
   
   public void updateRedMove(ActionEvent event) {
      if (rO.isSelected()) {
         Player.setRedMove("O");
      }
      else if (rS.isSelected()) {
         Player.setRedMove("S");
      }
   }

   public void updateRedScore(ActionEvent event) {
      scoreRed.setText("" + Player.redScore);
   }

   /* ----- END Red Player Logic ----- */
   /*__________________________________*/




   /*_____________________________________*/
   /* ----- START Blue Player Logic ----- */

   public void updateBlueMove(ActionEvent event) {
      if (bO.isSelected()) {
         Player.setBlueMove("O");
      }
      else if (bS.isSelected()) {
         Player.setBlueMove("S");
      }
   }
   
   /* ----- END Blue Player Logic ----- */
   /*__________________________________*/




   /*_______________________________*/
   /* ----- START Game Logic ----- */

   public void setGameMode(ActionEvent event) {
      mode = gameMode.getValue();
   }

   public String getGameMode() {

      return mode;
   }

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
            Text move = new Text(Player.redMove);
            move.setFont(Font.font("Arial",36));
            move.autosize();
            move.setFill(Color.RED);
            id.getChildren().add(move);
            Square.setAlignment(move, Pos.CENTER);
            checkSquare(id);
            setPlayerTurn("B");
         }
         else if (playerTurn.getText() == "B") {
            Text move = new Text(Player.blueMove);
            move.setFont(Font.font("Arial",36));
            move.autosize();
            move.setFill(Color.BLUE);
            id.getChildren().add(move);
            Square.setAlignment(move, Pos.CENTER);
            checkSquare(id);
            setPlayerTurn("R");
         }
      }
      boardSpaces.remove(id);
   }

   public static String direction(Square n, String direction) {
      Integer x = Integer.valueOf(n.getId().split(",")[0]);
      Integer y = Integer.valueOf(n.getId().split(",")[1]);
      String out = "";
      switch(direction) {
         case("downRight"):
            out = String.valueOf(x + 1) + "," + String.valueOf(y + 1);
            break;
         case("downLeft"):
            out = String.valueOf(x + 1) + "," + String.valueOf(y - 1);
            break;
         case("upRight"):
            out = String.valueOf(x - 1) + "," + String.valueOf(y + 1);
            break;
         case("upLeft"):
            out = String.valueOf(x - 1) + "," + String.valueOf(y - 1);
            break;
         case("down"):
            out = String.valueOf(x + 1) + "," + String.valueOf(y);
            break;
         case("right"):
            out = String.valueOf(x) + "," + String.valueOf(y + 1);
            break;
         case("left"):
            out = String.valueOf(x) + "," + String.valueOf(y - 1);
            break;
         case("up"):
            out = String.valueOf(x - 1) + "," + String.valueOf(y);
            break;
      }

      return out;
   }

   public static void checkSquare(Square currSq) {
      List children = currSq.getParent().getChildrenUnmodifiable();
      List<Square> squares = children;

      for(Square i: squares) {
         Text temp = (Text)currSq.getChildren().get(0);
         String currText = temp.getText();

         if(i.getId().equals(direction(currSq, "downRight"))) {
            if(!i.getChildren().isEmpty()) {
               Text k = (Text)i.getChildren().get(0);
               if (currText.equals("S")) {
                  if (k.getText().equals("O")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(i, "downRight"))) {
                           if (!j.getChildren().isEmpty()){
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    //gameOver(playerTurn);
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "diagBack");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
               else if (currText.equals("O")) {
                  if (k.getText().equals("S")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "upLeft"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")){
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                       //UPDATE GAME OVER/TURN HERE
                                       break;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "diagBack");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
         else if (i.getId().equals(direction(currSq, "right"))) {
            if (!i.getChildren().isEmpty()) {
               Text text = (Text)i.getChildren().get(0);
               if (currText.equals("S")) {
                  if (text.getText().equals("O")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(i, "right"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    //UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "horizontal");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
               else if (currText.equals("O")) {
                  if (text.getText().equals("S")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "left"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "horizontal");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
         else if (i.getId().equals(direction(currSq, "upRight"))) {
            if (!i.getChildren().isEmpty()) {
               Text text = (Text)i.getChildren().get(0);
               if (currText.equals("S")) {
                  if (text.getText().equals("O")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "upRight"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "diagForward");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
               else if (currText.equals("O")) {
                  if (text.getText().equals("S")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "downLeft"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "diagForward");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
         else if (i.getId().equals(direction(currSq, "up"))) {
            if (!i.getChildren().isEmpty()) {
               Text text = (Text)i.getChildren().get(0);
               if (currText.equals("S")) {
                  if (text.getText().equals("O")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "up"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "vertical");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
               else if (currText.equals("O")) {
                  if (text.getText().equals("S")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "down"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "vertical");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
         else if (i.getId().equals(direction(currSq, "upLeft"))) {
            if (!i.getChildren().isEmpty()) {
               Text text = (Text)i.getChildren().get(0);
               if (currText.equals("S")) {
                  if (text.getText().equals("O")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "upLeft"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "diagBack");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
               else if (currText.equals("O")) {
                  if (text.getText().equals("S")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "downRight"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "diagBack");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
         else if (i.getId().equals(direction(currSq, "left"))) {
            if (!i.getChildren().isEmpty()) {
               Text text = (Text)i.getChildren().get(0);
               if (currText.equals("S")) {
                  if (text.getText().equals("O")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "left"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "horizontal");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
               else if (currText.equals("O")) {
                  if (text.getText().equals("S")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "right"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "horizontal");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
         else if (i.getId().equals(direction(currSq, "downLeft"))) {
            if (!i.getChildren().isEmpty()) {
               Text text = (Text)i.getChildren().get(0);
               if (currText.equals("S")) {
                  if (text.getText().equals("O")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "downLeft"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "diagForward");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
               else if (currText.equals("O")) {
                  if (text.getText().equals("S")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "upRight"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")){
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "diagForward");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
         else if (i.getId().equals(direction(currSq, "down"))) {
            if (!i.getChildren().isEmpty()) {
               Text text = (Text)i.getChildren().get(0);
               if (currText.equals("S")) {
                  if (text.getText().equals("O")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "down"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "vertical");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
               else if (currText.equals("O")) {
                  if (text.getText().equals("S")) {
                     for (Square j: squares) {
                        if (j.getId().equals(direction(currSq, "up"))) {
                           if (!j.getChildren().isEmpty()) {
                              Text n = (Text)j.getChildren().get(0);
                              if (n.getText().equals("S")) {
                                 if (mode == "Simple") {
                                    boardSpaces.clear();
                                    // UPDATE GAME OVER HERE
                                    break;
                                 }
                                 else if (mode == "General") {
                                    if (playerTurn.equals("B")) {
                                       Player.blueScore = Player.blueScore + 1;
                                    }
                                    else if (playerTurn.equals("R")) {
                                       Player.redScore = Player.redScore + 1;
                                    }
                                 }
                                 Square.drawLine(currSq, i, j, "vertical");
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      // if (mode == "General") {
      //    if (boardSpaces.isEmpty()) {
      //       gameOver();
      //    }
      // }
   }

   public static void gameOver(String status) {
      if (Player.redScore > Player.blueScore) {
         System.out.println("Red Player has won!");
      }
      else if (Player.blueScore > Player.redScore) {
         System.out.println("Blue Player has won!");
      }
      else {
         System.out.println("Game Over! The result is a tie!");
      }
   }
   /* ----- END Game Logic ----- */
   /*____________________________*/



   @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
      gameMode.getItems().addAll(gameModes);
      gameSize.getItems().addAll(gameSizes);
   }
}