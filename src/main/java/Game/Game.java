package Game;

import Piece.*;
import Player.Player;
import Square.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private int size = 8;
    private Square[][] board= new Square[size][size];
    private String[] input;
    private Integer[] fromInput = new Integer[2];
    private Integer[] toInput = new Integer[2];
    private String horizontal = "abcdefgh";
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public Game(){
        InitializeBoard();
        draw();
    }

    public void run(){
        try {
            while(true){
                getInput();
                if(isValidCoord()){
                    System.out.println("Is valid coord");
                    System.out.println("From: x:" + fromInput[0] + ", y:" + fromInput[1]);
                    System.out.println("To: x:" + toInput[0] + ", y:" + toInput[1]);
                } else {
                    System.out.println("Is not valid coord");
                }
            }
        }catch (IOException ex) {

        }
    }

    private void getInput() throws IOException {
        input = bufferedReader.readLine().split("\s+");
    }

    private boolean isValidCoord(){
        Integer from;
        Integer to;
        if(input.length != 2){
            System.out.println("Length incorrect");
            return false;
        } else if(input[0].length() != 2 || input[1].length() != 2){
            System.out.println("Word are short or too long");
            return false;
        } else if(horizontal.indexOf(input[0].charAt(0)) == -1 || horizontal.indexOf(input[1].charAt(0)) == -1){
            System.out.println("Could not find character");
            return false;
        } else {
            from = Integer.parseInt(input[0].substring(1));
            to = Integer.parseInt(input[1].substring(1));
            if(from > 8 || from < 1 || to > 8 || to < 1){
                return false;
            }
        }
        fromInput[0] = horizontal.indexOf(input[0].charAt(0)) ;
        fromInput[1] = from - 1;
        toInput[0] = horizontal.indexOf(input[1].charAt(0)) ;
        toInput[1] = to - 1;
        return true;
    }

    private void InitializeBoard(){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                board[x][y] = new EmptySquare(new NoPiece(new Player(false)));
            }
        }
        for(int i = 0; i < size; i++){
            board[1][i] = new OccupiedSquare(new Pawn(new Player(false)));
            board[6][i] = new OccupiedSquare(new Pawn(new Player(true)));
        }
        board[0][3] = new OccupiedSquare(new Queen(new Player(false)));
        board[7][3] = new OccupiedSquare( new Queen(new Player(true)));

        board[7][4] = new OccupiedSquare( new King(new Player(true)));
        board[0][4] = new OccupiedSquare(new King(new Player(false)));

        board[0][0] = new OccupiedSquare( new Rook(new Player(false)));
        board[0][7] = new OccupiedSquare( new Rook(new Player(false)));

        board[7][0] = new OccupiedSquare(new Rook(new Player(true)));
        board[7][7] = new OccupiedSquare(new Rook(new Player(true)));

        board[0][1] = new OccupiedSquare( new Knight(new Player(false)));
        board[0][6] = new OccupiedSquare( new Knight(new Player(false)));

        board[7][1] = new OccupiedSquare( new Knight(new Player(true)));
        board[7][6] = new OccupiedSquare( new Knight(new Player(true)));

        board[0][2] = new OccupiedSquare( new Bishop(new Player(false)));
        board[0][5] = new OccupiedSquare( new Bishop(new Player(false)));

        board[7][2] = new OccupiedSquare( new Bishop(new Player(true)));
        board[7][5] = new OccupiedSquare( new Bishop(new Player(true)));

    }
    private void draw(){
        for(int x = 0; x < size; x++){
            System.out.print((8 - x  )% 9 + " ");
            for(int y = 0; y < size; y++){
                board[x][y].getPiece().draw();
                System.out.print(" ");
                //System.out.println(board[x][y]);
            }
            System.out.println();
        }
        System.out.println("  a  b c  d  e f  g  h");
    }
}
