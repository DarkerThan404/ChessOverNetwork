import Game.*;
import Network.PeerToPeer;

public class Main {
    public static void main(String[] args){
        Game game = new Game(new PeerToPeer(args));
        game.run();
        //System.out.println("Hello world");
        //System.out.println("\u2657");
        //System.out.println("\u265D");
    }
}
