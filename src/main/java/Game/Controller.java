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
        //Pawn whitePawn = new Pawn(white);
        for(int i = 0; i < size; i++){
            squares[1][i] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{i,1}), new Pawn(black));
            squares[6][i] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{i,6}), new Pawn(white));
        }
        squares[0][3] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{3,0}), new Queen(black));
        squares[7][3] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{3,7}), new Queen(white));

        squares[7][4] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{4,7}), new King(white));
        squares[0][4] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{4,0}), new King(black));

        squares[0][0] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{0,0}), new Rook(black));
        squares[0][7] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{7,0}), new Rook(black));

        squares[7][0] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{0,7}), new Rook(white));
        squares[7][7] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{7,7}), new Rook(white));

        squares[0][1] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{1,0}), new Knight(black));
        squares[0][6] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{6,0}), new Knight(black));

        squares[7][1] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{1,7}), new Knight(white));
        squares[7][6] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{6,7}), new Knight(white));

        squares[0][2] = new Square( CoordinateConvertor.IntToStringCoord(new Integer[]{2,0}), new Bishop(black));
        squares[0][5] = new Square( CoordinateConvertor.IntToStringCoord(new Integer[]{5,0}), new Bishop(black));

        squares[7][2] = new Square( CoordinateConvertor.IntToStringCoord(new Integer[]{2,7}), new Bishop(white));
        squares[7][5] = new Square( CoordinateConvertor.IntToStringCoord(new Integer[]{5,7}),new Bishop(white));
        var board = new ChessBoard(squares, size);
        return board;
    }

    public static boolean IsValidMove(ChessBoard chessBoard, String from, String to, boolean whiteTurn){
        if(!CoordInBounds(from) || !CoordInBounds(to)){
            System.out.println("Not in bounds");
            return false;
        }
        var fromCoord = CoordinateConvertor.StringToIntCoord(from);
        var toCoord = CoordinateConvertor.StringToIntCoord(to);

        var movingPiece = chessBoard.board[fromCoord[1]][fromCoord[0]];
        if(movingPiece != null){
            if(movingPiece.getPiece().IsValidMove(chessBoard, fromCoord, toCoord, whiteTurn)){
                //System.out.println("Piece can move in that way");
                return true;
            } else {
                System.out.println("Piece cannot move in that way");
                return false;
            }
        } else {
            System.out.println("Trying to move non-existing piece! Try again!");
        }

        return false;
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
    /// This method assumes that input values are always correct
    public static ChessBoard PerformMove(ChessBoard board, String from, String to, boolean whiteTurn){
        var newBoard = board;
        var IntsFrom = CoordinateConvertor.StringToIntCoord(from);
        var IntsTo = CoordinateConvertor.StringToIntCoord(to);
        var targetSquare = newBoard.board[IntsFrom[1]][IntsFrom[0]];
        assert (targetSquare != null);
        if(whiteTurn){
            var movingPiece = targetSquare.getPiece();
            if(movingPiece instanceof Pawn){ //en passent
                if(IntsFrom[1] == 3 && IntsTo[1] == 2 && IntsFrom[0] != IntsTo[0] && newBoard.board[IntsTo[1]][IntsTo[0]] == null ){
                    var EnPassableSquare = newBoard.board[IntsTo[1]+1][IntsTo[0]];
                    assert (EnPassableSquare != null);
                    newBoard.board[IntsTo[1]+1][IntsTo[0]] = null;
                }
                System.out.println("Is intance of pawn");
            }

        } else {
            var movingPiece = targetSquare.getPiece();
            if(movingPiece instanceof Pawn){
                if(IntsFrom[1] == 4 && IntsTo[1] == 5 && IntsFrom[0] != IntsTo[0] && newBoard.board[IntsTo[1]][IntsTo[0]] == null ){
                    var EnPassableSquare = newBoard.board[IntsTo[1]-1][IntsTo[0]];
                    assert (EnPassableSquare != null);
                    newBoard.board[IntsTo[1]-1][IntsTo[0]] = null;
                }
                System.out.println("Is intance of pawn");
            }
        }
        board.coordLastPieceMoved = IntsTo;
        newBoard.board[IntsTo[1]][IntsTo[0]] = targetSquare;
        newBoard.board[IntsFrom[1]][IntsFrom[0]] = null;
        targetSquare.getPiece().moveCount++;
        return newBoard;
    }
}
