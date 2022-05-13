package Game;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
