import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Tile { //will be for 7 x 7 board
   private int row; //current row location of tile
   private int column; //current col location of tile
   private boolean pathAvail;  //checks if there are any bordering pieces that could lead to a path or if you are locked in
   private boolean isTreasure;  //checks if the treasure on the piece is what you want
   private boolean movable; //checks if the card is movable or not
   private ImageIcon tileImage;
   private boolean[] paths;//0=up,1=right,2=down,3=left
   private Player[] occupants;
   
   
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
   
   public ImageIcon getTileImage() {
      return tileImage;
   }
   
   public boolean getMovable() {
      return movable;
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
   
   public int numOccupants(){
      return occupants.size();
   }
   
   public Player[] occupants(){
      return occupants;
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