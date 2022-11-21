package com.example;
import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;


public class Square extends StackPane {

   public Square(int x, int y) {
      setStyle("-fx-border-color: white; -fx-background-color: lightgrey;");
      setId(String.valueOf(x)+"," +String.valueOf(y));
      this.setPrefSize(2000, 2000);
      this.setOnMouseClicked(e -> handleMouseClick());
      PrimaryController.boardSpaces.add(this);
   }

   private void handleMouseClick() {
      PrimaryController.makeMove(this);
   }

   public static void drawLine(Square currentSq, Square i, Square j, String direction) {
      switch(direction) {
         case "vertical":
            Line vertLine1 = new Line((currentSq.getWidth() / 2), 0.0f, (currentSq.getWidth()/2), currentSq.getWidth());
            if (PrimaryController.getPlayerTurn().equals("R")) {
               vertLine1.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               vertLine1.setStroke(Color.BLUE);
            }
            currentSq.getChildren().add(vertLine1);
            Line vertLine2 = new Line((currentSq.getWidth() / 2), 0.0f, (currentSq.getWidth() / 2), currentSq.getWidth());
            if (PrimaryController.getPlayerTurn().equals("R")) {
               vertLine2.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               vertLine2.setStroke(Color.BLUE);
            }
            i.getChildren().add(vertLine2);
            Line vertLine3 = new Line((currentSq.getWidth() / 2), 0.0f, (currentSq.getWidth() / 2), currentSq.getWidth());
            if (PrimaryController.getPlayerTurn().equals("R")) {
               vertLine3.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               vertLine3.setStroke(Color.BLUE);
            }
            j.getChildren().add(vertLine3);
            break;

         case "horizontal":
            Line horizLine1 = new Line(0.0f, (currentSq.getHeight() / 2), currentSq.getWidth(), (currentSq.getHeight() / 2));
            if (PrimaryController.getPlayerTurn().equals("R")) {
               horizLine1.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               horizLine1.setStroke(Color.BLUE);
            }
            currentSq.getChildren().add(horizLine1);
            Line horizLine2 = new Line(0.0f, (currentSq.getHeight() / 2), currentSq.getWidth(), (currentSq.getHeight() / 2));
            if (PrimaryController.getPlayerTurn().equals("R")) {
               horizLine2.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               horizLine2.setStroke(Color.BLUE);
            }
            i.getChildren().add(horizLine2);
            Line horizLine3 = new Line(0.0f, (currentSq.getHeight() / 2), currentSq.getWidth(), (currentSq.getHeight() / 2));
            if (PrimaryController.getPlayerTurn().equals("R")) {
               horizLine3.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               horizLine3.setStroke(Color.BLUE);
            }
            j.getChildren().add(horizLine3);
            break;
         
         case "diagForward":
            Line diagForw1 = new Line(currentSq.getWidth(), 0.0f, 0.0f, currentSq.getHeight());
            if (PrimaryController.getPlayerTurn().equals("R")) {
               diagForw1.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               diagForw1.setStroke(Color.BLUE);
            }
            currentSq.getChildren().add(diagForw1);
            Line diagForw2 = new Line(currentSq.getWidth(), 0.0f, 0.0f, currentSq.getHeight());
            if (PrimaryController.getPlayerTurn().equals("R")) {
               diagForw2.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               diagForw2.setStroke(Color.BLUE);
            }
            i.getChildren().add(diagForw2);
            Line diagForw3 = new Line(currentSq.getWidth(), 0.0f, 0.0f, currentSq.getHeight());
            if (PrimaryController.getPlayerTurn().equals("R")) {
               diagForw3.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               diagForw3.setStroke(Color.BLUE);
            }
            j.getChildren().add(diagForw1);
            break;

         case "diagBack":
            Line diagBack1 = new Line(10.0f, 10.0f, (currentSq.getWidth() - 20), (currentSq.getHeight() - 20));
            if (PrimaryController.getPlayerTurn().equals("R")) {
               diagBack1.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               diagBack1.setStroke(Color.BLUE);
            }
            currentSq.getChildren().add(diagBack1);
            Line diagBack2 = new Line(0.0f, 0.0f, currentSq.getWidth(), currentSq.getHeight());
            if (PrimaryController.getPlayerTurn().equals("R")) {
               diagBack2.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               diagBack2.setStroke(Color.BLUE);
            }
            i.getChildren().add(diagBack2);
            Line diagBack3 = new Line(0.0f, 0.0f, currentSq.getWidth(), currentSq.getHeight());
            if (PrimaryController.getPlayerTurn().equals("R")) {
               diagBack3.setStroke(Color.RED);
            }
            else if (PrimaryController.getPlayerTurn().equals("B")) {
               diagBack3.setStroke(Color.BLUE);
            }
            j.getChildren().add(diagBack3);
            break;
   
      }
   
   }


}