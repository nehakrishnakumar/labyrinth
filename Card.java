import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Card {
   private boolean isLast;
   private ImageIcon cardImage;
   
   public Card (boolean iL) {
      isLast = iL;
   }
   
   
   //TODO: method to test if Card's image is same as that of the tile
   public boolean treasureLocated() {
      return false; //temporary return statement
   }
   
   public ImageIcon getCardImage() {
      return cardImage;
   }
}