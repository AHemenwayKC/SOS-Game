package com.example;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Square extends StackPane {

   public Square(int x, int y) {
      setStyle("-fx-border-color: white; -fx-background-color: lightgrey;");
      setId(String.valueOf(x)+"," +String.valueOf(y));
      this.setPrefSize(2000, 2000);
      this.setOnMouseClicked(e -> handleMouseClick());
      PrimaryController.boardSpaces.add(this);
   }

   // UNFINISHED
   private void handleMouseClick() {
      PrimaryController.makeMove(this);
   }

   // public void drawS() {
   //    Text s = new Text("S");
   //    s.setFont(Font.font(24));
   //    this.getChildren().add(s);
   // }

   // public void drawO() {
   //    Text o = new Text("O");
   //    o.setFont(Font.font(24));
   //    this.getChildren().add(o);
   // }

   // public static void drawLine() {

   // }

}