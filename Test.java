import java.util.*;

public class Test{
   public static void main(String[] args){
      Tile[][] board=new Tile[2][2];
      for(int i=0;i<board.length;i++){
         for(int c=0;c<board[i].length;c++){
            board[i][c]=new Tile(true,true,true,true);
         }
      }
      Player aidan=new Player(board[0][0]);
      Scanner input=new Scanner(System.in);
      int count=0;
      String user;
      while(count<5){
         System.out.println("S for down, D for Right");
         user = input.next();
         if(user.equals("s"))
            aidan.moveDown(board[0][1]);
         if(user.equals("d"))
            aidan.moveRight(board[1][0]);
         count++;
      }   
   }
}