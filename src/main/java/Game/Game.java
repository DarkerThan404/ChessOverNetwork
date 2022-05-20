package Game;


import Network.PeerToPeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Game {
    private final PeerToPeer net;
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public Game(PeerToPeer net){
        this.net = net;
    }

    /**
     * ChessOverNetwork.Main loop of the game
     */
    public void run(){

        ChessBoard board = Controller.CreateChessBoard();
        board.draw();

        boolean whiteTurn = true;
        System.out.println("Welcome to ChessOverNetwork! Input -h for usage");
        System.out.println("Players: \'w\' = white | \'b\' = black");
        System.out.println("Pieces: \'K\' = King | \'Q\' = Queen | \'R\' = Rook | \'B\' = Bishop | \'N\' = Knight | \'P\' = Pawn");
        try {
            net.InitializeConnectionToPeer();
            while(true) {
                if(board.IsCheckMate(whiteTurn)){
                    if(whiteTurn){
                        System.out.println("Black wins the game! Checkmate!");
                    } else {
                        System.out.println("White wins the game! Checkmate!");
                    }
                    break;
                }

                if(board.IsKingInCheck(whiteTurn)){
                    if(whiteTurn){
                        System.out.println("White king is in check!");
                    } else {
                        System.out.println("Black king is in check!");
                    }
                }
                var rawInput = getInput(whiteTurn);
                var splitInput = rawInput.split("\s+");
                if (splitInput.length == 1) {
                    if (splitInput[0].equals("help") || splitInput[0].equals("-h")) {
                        System.out.println("Valid turn prompt: ");
                        System.out.println("1) <from> <to> regular move ");
                        System.out.println("2) <from> <to> <piece type> promotion to specific piece, implicit is Queen");
                        System.out.println("3) help -h prints this message");
                        System.out.println("<from> <to> are coordinates e.g. e2 e4 , moves piece on e2 to e4 if it is according to rules");
                    }
                } else if (splitInput.length == 2) {
                    if (Controller.IsValidMove(board,splitInput[0],splitInput[1],whiteTurn)) {
                        board = Controller.PerformMove(board, splitInput[0], splitInput[1], whiteTurn);
                        whiteTurn = !whiteTurn;
                        board.draw();
                        sendOutput(whiteTurn, rawInput);
                    } else {
                        System.out.println("Invalid move! Try something else.");
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
            net.closeConnection();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Gets input either from input or network depending on game state
     * @param isWhiteSide True, if it's white's turn, false if it's black's turn
     * @return Message, processed by network or standard input
     * @throws IOException
     */
    private String getInput(boolean isWhiteSide) throws IOException {
        var result = "";
        if(net.isServer && isWhiteSide || !isWhiteSide && !net.isServer){
            if(isWhiteSide){
                System.out.println("White player is playing, enter a move: ");
            } else {
                System.out.println("Black player is playing, enter a move: ");
            }
            result = bufferedReader.readLine();
        } else {
            if(isWhiteSide){
                System.out.println("Waiting for a white player ...");
            } else {
                System.out.println("Waiting for a black player ...");
            }
            result = net.getMessage();
        }
        return result;
    }

    /**
     * Sends message depending on game state
     * @param isWhiteSide True, if it's white's turn, false if it's black's turn
     * @param message Message to send to other side.
     * @throws IOException
     */
    private void sendOutput(boolean isWhiteSide, String message) throws IOException {
        if(net.isServer && !isWhiteSide || !net.isServer && isWhiteSide){
            net.sendMessage(message);
        }
    }
}
