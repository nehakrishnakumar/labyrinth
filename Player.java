public class Player{
   Tile location;

   public Player(Tile l){
      location=l;
      l.addPlayer(this);
   }

   public boolean moveRight(Tile r){
      if(location.right()&&r.left()){
         location.removePlayer();
         location=r;
         r.addPlayer(this);
         return true;
      }
      return false;
   }
   
   public boolean moveLeft(Tile r){
      if(location.left()&&r.right()){
         location.removePlayer();
         location=r;
         r.addPlayer(this);
         return true;
      }
      return false;
   }
   
   public boolean moveUp(Tile r){
      if(location.top()&&r.bottom()){
         location.removePlayer();
         location=r;
         r.addPlayer(this);
         return true;
      }
      return false;
   }
   
   public boolean moveDown(Tile r){
      if(location.bottom()&&r.top()){
         location.removePlayer();
         location=r;
         r.addPlayer(this);
         return true;
      }
      return false;
   }
}