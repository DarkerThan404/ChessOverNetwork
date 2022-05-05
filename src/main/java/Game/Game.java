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
                var rawInput = getInput().split("\s+");
                if (rawInput.length == 1) {
                    if (rawInput[0].equals("help")) {
                        System.out.println("Help invoked");
                    }
                } else if (rawInput.length == 2) {
                    if (Controller.IsValidMove(board,rawInput[0],rawInput[1],whiteTurn)) {
                        System.out.println("Is valid coord");
                        //System.out.println("From: x:" + fromInput[0] + ", y:" + fromInput[1]);
                        //System.out.println("To: x:" + toInput[0] + ", y:" + toInput[1]);
                        board = Controller.PerformMove(board, rawInput[0], rawInput[1], whiteTurn);
                        board.draw();
                        whiteTurn = !whiteTurn;
                    } else {
                        System.out.println("Is not valid input");
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

    /*private boolean isValidCoord(String rawMoveInput){
        var moveInput = rawMoveInput.split("\s+");
        Integer from;
        Integer to;
        if(moveInput.length != 2){
            System.out.println("Length incorrect");
            return false;
        } else if(moveInput[0].length() != 2 || moveInput[1].length() != 2){
            System.out.println("Word are short or too long");
            return false;
        } else if(horizontal.indexOf(moveInput[0].charAt(0)) == -1 || horizontal.indexOf(moveInput[1].charAt(0)) == -1){
            System.out.println("Could not find character");
            return false;
        } else {
            from = Integer.parseInt(moveInput[0].substring(1));
            to = Integer.parseInt(moveInput[1].substring(1));
            if(from > 8 || from < 1 || to > 8 || to < 1){
                return false;
            }
        }
        fromInput[0] = horizontal.indexOf(moveInput[0].charAt(0)) ;
        fromInput[1] = from - 1;
        toInput[0] = horizontal.indexOf(moveInput[1].charAt(0)) ;
        toInput[1] = to - 1;
        return true;
    }*/



}
