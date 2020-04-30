import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Tile { //will be for 7 x 7 board
   private int row; //current row location of tile
   private int column; //current col location of tile
   private boolean pathAvail;  //checks if there are any bordering pieces that could lead to a path or if you are locked in
   private boolean isTreasure;  //checks if the treasure on the piece is what you want
   private boolean movable; //checks if the card is the last card in row or column for user to use
   private boolean isArrow; //check if it is on a row or column that is movable
   private ImageIcon tileImage;
   private boolean[] paths;//0=up,1=right,2=down,3=left
   private boolean onBoard; //checks if it is on board or not
   
   
   /****CONSTRUCTORS****/
   public Tile (int r, int c) {
      row = r;
      column = c;
      //this constructor may not be needed, or the bottom may not be needed
   }
   
   public Tile (int r, int c, boolean iT,boolean[] p) {
      row = r;
      column = c;
      //set pathAvail and isTreasure to random values
      //pathAvail = pA;
      isTreasure = iT;
      paths=p;
   }
   
   public int getRow() {
      return row;
   }
   
   public void setRow(int r) {
      row = r;
   }
   public int getColumn() {
      return column;
   }
   
   public void setColumn (int c) {
      column = c;
   }
   
   public boolean checkPathAvailable() {
      if (paths[0] == false && paths[1] == false && paths[2] == false && paths[3] == false) {
         return false;
      }
      else {
         return true;
      }
   }
   public boolean getPathAvailable() {
      return pathAvail;
   }
   
   public void setPathAvailable (boolean pA) {
      pathAvail = pA;
   }
   
   public boolean getIsTreasure() {
      return isTreasure;
   }
   
   public void setIsTreasure (boolean iT) {
      isTreasure = iT;
   }
   
   public boolean getIsArrow() {
	return isArrow;
   }

   public void setIsArrow (boolean iA) {
	isArrow = iA;
   }
   public ImageIcon getTileImage() {
      return tileImage;
   }
   
   public void setTileImage(ImageIcon i) {
      tileImage = i;
   }
   public boolean getMovable() {
      return movable;
   }
   
   public boolean setMovable(boolean sM) {
      movable = sM;
   }
   
   public boolean top(){
   return paths[0];
   }
   
   public boolean right(){
   return paths[1];
   }
   
   public boolean bottom(){
   return paths[2];
   }
   
   public boolean left(){
   return paths[3];
   }
   
   public void rotate(){
   boolean temp=paths[0];
   paths[0]=paths[3];
   paths[3]=paths[2];
   paths[2]=paths[1];
   paths[1]=temp;
   }
   
   public boolean getOnBoard() {
      return onBoard;
   }
   
   public void setOnBoard (boolean oB) {
      onBoard = oB;
   }
   public boolean equals (Card c) {
      if (c.getCardImage().equals (this.getTileImage())) {
         return true;
      }
      else {
         return false;
      }
   }

}
