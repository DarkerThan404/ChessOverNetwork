package Game;

import Piece.*;
import Player.Player;
import Square.Square;

public class Game {
    private int size = 8;
    private Square[][] board= new Square[size][size];

    public Game(){}

    public void run(){
        draw();
    }

    private void InitializeBoard(){
        for(int i = 0; i < size; i++){
            board[1][i] = new Square(i,1, new Pawn(new Player(false)));
            board[6][i] = new Square(i, 6, new Pawn(new Player(true)));
        }
        board[7][3] = new Square(3, 7, new Queen(new Player(false)));
        board[0][3] = new Square(3,7, new Queen(new Player(true)));

        board[7][4] = new Square(4,7, new King(new Player(true)));
        board[0][4] = new Square(4,0, new King(new Player(false)));

        board[0][0] = new Square(0,0, new Rook(new Player(false)));
        board[0][7] = new Square(7,0, new Rook(new Player(false)));

        board[7][0] = new Square(0,7, new Rook(new Player(true)));
        board[7][7] = new Square(7,7, new Rook(new Player(true)));

        board[0][1] = new Square(1,0, new Knight(new Player(false)));
        board[0][6] = new Square(6,0, new Knight(new Player(false)));

        board[7][1] = new Square(1,7, new Knight(new Player(true)));
        board[7][6] = new Square(6,7, new Knight(new Player(true)));

        board[0][2] = new Square(2,0, new Bishop(new Player(false)));
        board[0][5] = new Square(5,0, new Bishop(new Player(false)));

        board[7][2] = new Square(2,7, new Bishop(new Player(true)));
        board[7][5] = new Square(5,7, new Bishop(new Player(true)));

    }
    private void draw(){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                board[x][y].getPiece().draw();
            }
            System.out.println();
        }
    }
}
