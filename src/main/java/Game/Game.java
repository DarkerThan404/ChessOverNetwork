package Game;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Game {

    private Integer[] fromInput = new Integer[2];
    private Integer[] toInput = new Integer[2];
    private String horizontal = "abcdefgh";
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public Game(){
    }

    public void run(){
        ChessBoard board = Controller.CreateChessBoard();
        board.draw();
        boolean whiteTurn = true;
        try {
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

                var rawInput = getInput().split("\s+");
                if (rawInput.length == 1) {
                    if (rawInput[0].equals("help")) {
                        System.out.println("Help invoked");
                    }
                } else if (rawInput.length == 2) {
                    if (Controller.IsValidMove(board,rawInput[0],rawInput[1],whiteTurn)) {
                        board = Controller.PerformMove(board, rawInput[0], rawInput[1], whiteTurn);
                        whiteTurn = !whiteTurn;
                        board.draw();
                    } else {
                        System.out.println("Unsupported format! Perhaps piece cannot move that way");
                    }
                } else if (rawInput.length == 3){
                    var thirdToken = rawInput[2];
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
                        var firstToken = rawInput[0];
                        var secondToken = rawInput[1];
                        if(Controller.IsValidMove(board,firstToken,secondToken,whiteTurn)){
                            try{
                                board = Controller.ProcessPromotion(board, firstToken, secondToken, whiteTurn, thirdToken);
                                board.draw();
                                whiteTurn = !whiteTurn;
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

    private String getInput() throws IOException {
        var rawInput = bufferedReader.readLine();
        return rawInput;
    }
}
