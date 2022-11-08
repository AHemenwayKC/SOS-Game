package com.example;


public class Player {
   
   public static Integer redScore = 0, blueScore = 0;
   public static String redMove = new String("S"), blueMove = new String("S");

   public static String getRedMove() {
      return redMove;
   }

   public static void setRedMove(String move) {
      redMove = move;

   }

   public static void setRedScore(Integer score) {
      redScore = score;
   }

   public String getBlueMove() {
      return blueMove;
   }

   public void setBlueMove(String move) {
      blueMove = move;
   }

   public static void setBlueScore(Integer score) {
      blueScore = score;
   }
}