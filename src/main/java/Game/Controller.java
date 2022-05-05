package Game;

import Piece.*;
import Player.Player;
import Square.Square;

public class Controller {
    public static ChessBoard CreateChessBoard(){
        int size = 8;
        Square[][] squares = new Square[size][size];

        for(int i = 0; i < size; i++){
            squares[1][i] = new Square(new Pawn(new Player(false)));
            squares[6][i] = new Square(new Pawn(new Player(true)));
        }
        squares[0][3] = new Square(new Queen(new Player(false)));
        squares[7][3] = new Square( new Queen(new Player(true)));

        squares[7][4] = new Square( new King(new Player(true)));
        squares[0][4] = new Square(new King(new Player(false)));

        squares[0][0] = new Square( new Rook(new Player(false)));
        squares[0][7] = new Square( new Rook(new Player(false)));

        squares[7][0] = new Square(new Rook(new Player(true)));
        squares[7][7] = new Square(new Rook(new Player(true)));

        squares[0][1] = new Square( new Knight(new Player(false)));
        squares[0][6] = new Square( new Knight(new Player(false)));

        squares[7][1] = new Square( new Knight(new Player(true)));
        squares[7][6] = new Square( new Knight(new Player(true)));

        squares[0][2] = new Square( new Bishop(new Player(false)));
        squares[0][5] = new Square( new Bishop(new Player(false)));

        squares[7][2] = new Square( new Bishop(new Player(true)));
        squares[7][5] = new Square( new Bishop(new Player(true)));
        var board = new ChessBoard(squares, size);
        return board;
    }
}
