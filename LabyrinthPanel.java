import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.io.*;


public class LabyrinthPanel extends JPanel implements MouseListener, MouseMotionListener
{
   private ImageIcon crossHair = new ImageIcon("images/crossHair.GIF");	//GIF immages can have transparency
   //REMOVE CROSSHAIR LATER -- THIS IS TEMPORARY AND FOR COMPILING PURPOSES ONLY!

   private static final int SIZE=80;	//size of cell being drawn
 
   //This array will be represented graphically on the screen
   private static Tile[][] board;
   private static Card[] cards;
   private static int numberOfCards;  //represents number of cards not turned over
   private static Tile freeTile; //tile that is not placed on board
   
   private static int numRows;
   private static int numColumns;
   	
 	//A moveable smiley face will start in the center of the field
   private static int playerR;			//start row for the player
   private static int playerC;			//start col for the player
     
   protected static int mouseX;			//locations for the mouse pointer
   protected static int mouseY;
   
   //TODO: contact graphics person to give the images so I will be able to set up the board
   public static void initBoard(Tile[][] board) throws IOException {
      numberOfCards = 12;  //keep @ 12 for now b/c it has to divide up evenly among computer and player
      cards = new Card [numberOfCards];
      int ci = 0;
      for (int r = 0; r < numRows; r++) {
         for (int c = 0; c < numColumns; c++) {
            if (r % 2 == 1 || c % 2 == 1) {
               //board[r][c].setTileImage(new ImageIcon ("blue.jpg"));  //blank space for the other cards
               
               board[r][c].setMovable(true);
               //MAKE A CONDITION THAT WILL MAKE THE TILES BE MOVABLE OR IMMOVABLE
            }
            else {
               board[r][c].setMovable(false);
               //board[r][c].setTileImage("images based on graphics.jpg")
               //here we will set up the 8 treasure set cards
            }
         }
      }
   
   }
   public LabyrinthPanel() throws IOException
   {
      addMouseListener( this );
      addMouseMotionListener( this );
      mouseX = 0;
      mouseY = 0;
   
      numRows = 7;		//array has a random size
      numColumns = 7;
      board = new Tile[numRows][numColumns];
      initBoard(board);
      playerR = board.length/2;							//start player position in the middle
      playerC = board[0].length/2;	
      freeTile = null;
   }

   public int getNumberOfCards() {
      return numberOfCards;
   }

	//post:  shows different pictures on the screen in grid format depending on the values stored in the array board
	//			0-blank, 1-white, 2-black and gives priority to drawing the player
   public void showBoard(Graphics g)	
   {
      int x =0, y=0;		//upper left corner location of where image will be drawn
      for(int r=0;r<board.length;r++)
      {
         x = 0;						//reset the row distance
         for(int c=0;c<board[0].length;c++)
         {
            if(board[r][c] != null)
               g.drawImage(board[r][c].getTileImage().getImage(), x, y, SIZE, SIZE, null);  //scaled image
                         
            if(r==playerR && c==playerC)	//draw the crosshair on the board after the cell has been drawn
               g.drawImage(crossHair.getImage(), x, y, SIZE, SIZE, null);  //scaled image
         
            x+=SIZE;
         }
         y+=SIZE;
      }
   }

	//THIS METHOD IS ONLY CALLED THE MOMENT A KEY IS HIT - NOT AT ANY OTHER TIME
	//pre:   k is a valid keyCode
	//post:  changes the players position depending on the key that was pressed (sent from the driver)
	//			keeps the player in the bounds of the size of the array board, then the enemy moves
   public void processUserInput(int k)
   {
      if(k==KeyEvent.VK_Q || k==KeyEvent.VK_ESCAPE)					//End the program	
         System.exit(1);
      if(k==KeyEvent.VK_UP && playerR>0)
         playerR--;
      else 
         if(k==KeyEvent.VK_DOWN && playerR<board.length-1)
            playerR++;
         else
            if(k==KeyEvent.VK_LEFT && playerC>0)
               playerC--;
            else 
               if(k==KeyEvent.VK_RIGHT && playerC<board[0].length-1)
                  playerC++;
               else 
                  if(k==KeyEvent.VK_SPACE)	//select company
                  {
                  //keep empty for now
                  }
      repaint();			//refresh the screen
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); 
      g.setColor(Color.white);		//draw a blue boarder around the board
      g.fillRect(0, 0, (board[0].length*SIZE), (board.length*SIZE));
      showBoard(g);					//draw the contents of the array board on the screen
   }
   
	 //***BEGIN MOUSE STUFF***
private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)	//this is called for each timer iteration - make the enemy move randomly
      {
         repaint();
      }
   }
   public static void shift(Tile[][] board, int shift) {  //params: board and row or column w/ arrow to shift
   //TODO: involve the movable tile somehow, find a way to "track" it, maybe with mouse location
      for (int r = 0; r < board.length; r++) {
         for (int c = 0; c < board[0].length; c++) {
            if (c != board[0].length && shift % 2 == 1) //arrow is on row and c != board[0].length
               board[r][c].setTileImage (board[shift][c + 1].getImage());
            else if (r != board.length && shift % 2 == 0) //arrow is on column and r != board.length
               board[r][c].setTileImage (board[r + 1][shift].getImage());
            else if (r == board.length && shift % 2 == 1) {
               board[r][c].setMovable(true);
            }
            else if (c == board.length && shift % 2 == 0) {
               board[r][c].setMovable(true);
            }
            if (r == board.length || c == board[0].length) {
		board[r][c].setMovable(false); //is the end tile to be "taken out" for later use
	    }
         }
      }
   }
   public void mouseClicked( MouseEvent e )
   {      
	//TODO: track location of where it is clicked and use location to figure out if on a certain tile
	   //check if on row or column that has arrow present
      //if (on row or column w/ arrow present)
      //if shift is horizontal shift variable will be 0
      //if shift is vertical shift variable will be 1
      int shift = 0;  //temporary shifting variable
      shift(board, shift);//shift all neighbors up by one (manipulate the neighbors array so everything goes up by one for each member of that row)
      //for each member in board
      //setNeighbors to set neighbors to the next one's neighbors, and keep moving on. when you reach the last one take it out and set as the outOfBoardTile
      //this'll be for all of board
      
      //take the card at the end and keep on the side (if that makes sense)
      
      repaint();
   }

   public void mousePressed( MouseEvent e )
   {
   }

   public void mouseReleased(MouseEvent e)
   {
   
   }

   public void mouseEntered( MouseEvent e )
   {}

   public void mouseMoved( MouseEvent e)
   {
      repaint();
   }

   public void mouseDragged( MouseEvent e)
   {}

   public void mouseExited( MouseEvent e )
   {}
   
}
