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
        var board = new ChessBoard(squares, size);
        //Pawn whitePawn = new Pawn(white);
        for(int i = 0; i < size; i++){
            squares[1][i] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{i,1}), new Pawn(black));
            squares[6][i] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{i,6}), new Pawn(white));
            board.blackPieces.add(CoordinateConvertor.IntToStringCoord( new Integer[]{i,1}));
            board.whitePieces.add(CoordinateConvertor.IntToStringCoord( new Integer[]{i,6}));
        }
        squares[0][3] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{3,0}), new Queen(black));
        board.blackPieces.add( CoordinateConvertor.IntToStringCoord( new Integer[]{3,0}));
        squares[7][3] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{3,7}), new Queen(white));
        board.whitePieces.add(CoordinateConvertor.IntToStringCoord( new Integer[]{3,7}));

        squares[7][4] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{4,7}), new King(white));
        board.whitePieces.add(CoordinateConvertor.IntToStringCoord( new Integer[]{4,7}));
        squares[0][4] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{4,0}), new King(black));
        board.blackPieces.add( CoordinateConvertor.IntToStringCoord( new Integer[]{4,0}));

        squares[0][0] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{0,0}), new Rook(black));
        squares[0][7] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{7,0}), new Rook(black));
        board.blackPieces.add(CoordinateConvertor.IntToStringCoord( new Integer[]{0,0}));
        board.blackPieces.add(CoordinateConvertor.IntToStringCoord( new Integer[]{7,0}));

        squares[7][0] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{0,7}), new Rook(white));
        squares[7][7] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{7,7}), new Rook(white));
        board.whitePieces.add( CoordinateConvertor.IntToStringCoord( new Integer[]{0,7}));
        board.whitePieces.add( CoordinateConvertor.IntToStringCoord( new Integer[]{7,7}));

        squares[0][1] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{1,0}), new Knight(black));
        squares[0][6] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{6,0}), new Knight(black));
        board.blackPieces.add(CoordinateConvertor.IntToStringCoord( new Integer[]{1,0}));
        board.blackPieces.add(CoordinateConvertor.IntToStringCoord( new Integer[]{6,0}));

        squares[7][1] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{1,7}), new Knight(white));
        squares[7][6] = new Square( CoordinateConvertor.IntToStringCoord( new Integer[]{6,7}), new Knight(white));
        board.whitePieces.add( CoordinateConvertor.IntToStringCoord( new Integer[]{1,7}));
        board.whitePieces.add( CoordinateConvertor.IntToStringCoord( new Integer[]{6,7}));

        squares[0][2] = new Square( CoordinateConvertor.IntToStringCoord(new Integer[]{2,0}), new Bishop(black));
        squares[0][5] = new Square( CoordinateConvertor.IntToStringCoord(new Integer[]{5,0}), new Bishop(black));
        board.blackPieces.add( CoordinateConvertor.IntToStringCoord(new Integer[]{2,0}));
        board.blackPieces.add( CoordinateConvertor.IntToStringCoord(new Integer[]{5,0}));

        squares[7][2] = new Square( CoordinateConvertor.IntToStringCoord(new Integer[]{2,7}), new Bishop(white));
        squares[7][5] = new Square( CoordinateConvertor.IntToStringCoord(new Integer[]{5,7}),new Bishop(white));
        board.whitePieces.add( CoordinateConvertor.IntToStringCoord(new Integer[]{2,7}));
        board.whitePieces.add( CoordinateConvertor.IntToStringCoord(new Integer[]{5,7}));

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
            var isPosibleLogicalMove = movingPiece.getPiece().IsValidMove(chessBoard, fromCoord, toCoord, whiteTurn);
            if(!isPosibleLogicalMove) { System.out.println("Piece doesnt move that way"); return false; }
            if(chessBoard.wouldBeKingInDanger(from, to, whiteTurn)){
                System.out.println("King would be in danger. Cannot move that piece!"); return false;
            }
            return true;
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
        System.out.println(" square X: " + IntsFrom[0] + " Y: " + IntsFrom[1]);
        if(whiteTurn){
            System.out.println("Omeaglul");
            if(targetSquare == null) System.out.println("Is null");
            var movingPiece = targetSquare.getPiece();
            if(movingPiece instanceof Pawn){ //en passent
                if(IntsFrom[1] == 3 && IntsTo[1] == 2 && IntsFrom[0] != IntsTo[0] && newBoard.board[IntsTo[1]][IntsTo[0]] == null ){
                    var EnPassableSquare = newBoard.board[IntsTo[1]+1][IntsTo[0]];
                    assert (EnPassableSquare != null);
                    newBoard.board[IntsTo[1]+1][IntsTo[0]] = null;
                    var stringPos = CoordinateConvertor.IntToStringCoord(new Integer[]{IntsTo[0],IntsTo[1]+1});
                    int index = newBoard.blackPieces.indexOf(stringPos);
                    newBoard.blackPieces.remove(index);
                }
            }

        } else {
            var movingPiece = targetSquare.getPiece();
            if(movingPiece instanceof Pawn){
                if(IntsFrom[1] == 4 && IntsTo[1] == 5 && IntsFrom[0] != IntsTo[0] && newBoard.board[IntsTo[1]][IntsTo[0]] == null ){
                    var EnPassableSquare = newBoard.board[IntsTo[1]-1][IntsTo[0]];
                    assert (EnPassableSquare != null);
                    newBoard.board[IntsTo[1]-1][IntsTo[0]] = null;
                    var stringPos = CoordinateConvertor.IntToStringCoord(new Integer[]{IntsTo[0],IntsTo[1]-1});
                    int index = newBoard.blackPieces.indexOf(stringPos);
                    newBoard.blackPieces.remove(index);
                }
            }
        }
        board.coordLastPieceMoved = IntsTo;
        newBoard.board[IntsTo[1]][IntsTo[0]] = targetSquare;
        targetSquare.pos = CoordinateConvertor.IntToStringCoord(IntsTo);
        newBoard.board[IntsFrom[1]][IntsFrom[0]] = null;
        targetSquare.getPiece().moveCount++;
        newBoard.UpdatePieceLists(from, to, whiteTurn);
        return newBoard;
    }




}
