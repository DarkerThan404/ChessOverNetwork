package ChessOverNetwork;

import Game.*;
import Network.PeerToPeer;

public class Main {
    public static void main(String[] args){
        //just runs the game
        Game game = new Game(new PeerToPeer(args));
        game.run();
    }
}
