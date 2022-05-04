package Game;

import Piece.*;
import Player.Player;
import Square.*;

public class Game {
    private int size = 8;
    private Square[][] board= new Square[size][size];

    public Game(){}

    public void run(){
        InitializeBoard();
        draw();
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
            for(int y = 0; y < size; y++){
                board[x][y].getPiece().draw();
                //System.out.println(board[x][y]);
            }
            System.out.println();
        }
    }
}
