package Game;

import Piece.*;
import Player.Player;
import Square.Square;

public class Controller {
    public static ChessBoard CreateChessBoard(){
        int size = 8;
        Square[][] squares = new Square[size][size];
        Player white = new Player(true);
        Player black = new Player(false);
        for(int i = 0; i < size; i++){
            squares[1][i] = new Square( new Pawn(black));
            squares[6][i] = new Square( new Pawn(white));
        }
        squares[0][3] = new Square( new Queen(black));
        squares[7][3] = new Square( new Queen(white));

        squares[7][4] = new Square( new King(white));
        squares[0][4] = new Square( new King(black));

        squares[0][0] = new Square( new Rook(black));
        squares[0][7] = new Square( new Rook(black));

        squares[7][0] = new Square( new Rook(white));
        squares[7][7] = new Square( new Rook(white));

        squares[0][1] = new Square( new Knight(black));
        squares[0][6] = new Square( new Knight(black));

        squares[7][1] = new Square( new Knight(white));
        squares[7][6] = new Square( new Knight(white));

        squares[0][2] = new Square( new Bishop(black));
        squares[0][5] = new Square( new Bishop(black));

        squares[7][2] = new Square( new Bishop(white));
        squares[7][5] = new Square( new Bishop(white));
        var board = new ChessBoard(squares, size);
        return board;
    }

    public static boolean IsValidMove(ChessBoard board, String from, String to, boolean whiteTurn){
        if(!CoordInBounds(from) || !CoordInBounds(to)){
            System.out.println("Not in bounds");
            return false;
        }


        return true;
    }

    private static boolean CoordInBounds(String coord){
        if(coord.length() != 2){
            return false;
        }

        var coords = CoordinateConvertor.StringToIntCoord(coord);
        var x = coords[0];
        var y = coords[1];
        //System.out.println("X: " + x + ", Y:" + y);
        if(x > 7 || x < 0 || y > 7 || y < 0){
            return false;
        }
        return true;
    }

    public static ChessBoard PerformMove(ChessBoard board, String from, String to, boolean whiteTurn){
        var newBoard = board;
        var IntsFrom = CoordinateConvertor.StringToIntCoord(from);
        var IntsTo = CoordinateConvertor.StringToIntCoord(to);
        if(whiteTurn){
            var movingPiece = board.board[IntsFrom[1]][IntsFrom[0]];
            System.out.println("From x:" + IntsFrom[0] + ", y:" + IntsFrom[1]);
            var back = CoordinateConvertor.IntToStringCoord(IntsFrom);
            System.out.println(back);
            System.out.println(CoordinateConvertor.IntToStringCoord(IntsTo));
            movingPiece.getPiece().draw();
            newBoard.board[IntsTo[1]][IntsTo[0]] = movingPiece;
            newBoard.board[IntsFrom[1]][IntsFrom[0]] = null;
            newBoard.draw();
        }
        return newBoard;
    }
}
