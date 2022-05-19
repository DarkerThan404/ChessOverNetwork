package Game;


import Network.PeerToPeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Game {
    private PeerToPeer  net;
    private Integer[] fromInput = new Integer[2];
    private Integer[] toInput = new Integer[2];
    private String horizontal = "abcdefgh";
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public Game(PeerToPeer net){
        this.net = net;
    }

    public void run(){
        ChessBoard board = Controller.CreateChessBoard();
        board.draw();

        boolean whiteTurn = true;
        try {
            net.InitializeConnectionToPeer();
            while(true) {
                if(board.IsCheckMate(whiteTurn)){
                    System.out.println("Checkmate");
                    break;
                }

                if(board.IsKingInCheck(whiteTurn)){
                    if(whiteTurn){
                        System.out.println("White king is in check!");
                    } else {
                        System.out.println("Black king is in check!");
                    }
                }
                if(whiteTurn){
                    System.out.println("Player 1 enter a move ");
                } else {
                    System.out.println("Player 2 enter a move ");
                }
                var rawInput = getInput(whiteTurn);
                var splitInput = rawInput.split("\s+");
                if (splitInput.length == 1) {
                    if (splitInput[0].equals("help")) {
                        System.out.println("Help invoked");
                    }
                } else if (splitInput.length == 2) {
                    if (Controller.IsValidMove(board,splitInput[0],splitInput[1],whiteTurn)) {
                        board = Controller.PerformMove(board, splitInput[0], splitInput[1], whiteTurn);
                        whiteTurn = !whiteTurn;
                        board.draw();
                        sendOutput(whiteTurn, rawInput);
                    } else {
                        System.out.println("Unsupported format! Perhaps piece cannot move that way");
                    }
                } else if (splitInput.length == 3){
                    var thirdToken = splitInput[2];
                    if(Objects.equals(thirdToken, "R") ||
                            Objects.equals(thirdToken, "Q") ||
                            Objects.equals(thirdToken, "K") ||
                            Objects.equals(thirdToken, "B") ||
                            Objects.equals(thirdToken, "N") ||
                            Objects.equals(thirdToken, "P")
                    ){
                        if(thirdToken.equals("P")){
                            System.out.println("Cannot promote pawn to pawn");
                            continue;
                        }
                        if(thirdToken.equals("K")){
                            System.out.println("Cannot promote pawn to king");
                            continue;
                        }
                        var firstToken = splitInput[0];
                        var secondToken = splitInput[1];
                        if(Controller.IsValidMove(board,firstToken,secondToken,whiteTurn)){
                            try{
                                board = Controller.ProcessPromotion(board, firstToken, secondToken, whiteTurn, thirdToken);
                                board.draw();
                                whiteTurn = !whiteTurn;
                                sendOutput(whiteTurn, rawInput);
                            } catch (IllegalArgumentException exception){
                                System.out.println(exception.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private String getInput(boolean isWhiteSide) throws IOException {
        var result = "";
        if(net.isServer && isWhiteSide || !isWhiteSide && !net.isServer){
            result = bufferedReader.readLine();
        } else {
            result = net.getMessage();
        }
        return result;
    }

    private void sendOutput(boolean isWhiteSide, String message) throws IOException {
        if(net.isServer && !isWhiteSide || !net.isServer && isWhiteSide){
            net.sendMessage(message);
        }
    }
}
