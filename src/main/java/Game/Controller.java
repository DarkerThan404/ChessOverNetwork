package Game;

import Piece.*;
import Player.Player;
import Square.Square;

public class Controller {
    /**
     * Initial method to create chessboard
     * @return
     */
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

    /**
     * Gatekeeper method to validate input move
     * @param chessBoard
     * @param from
     * @param to
     * @param whiteTurn
     * @return
     */
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
            if(!isPosibleLogicalMove) { System.out.println("It is not legal move"); return false; }
            if(chessBoard.wouldBeKingInDanger(from, to, whiteTurn)){
                System.out.println("King would be in danger. Cannot move that piece!"); return false;
            }
            return true;
        } else {
            System.out.println("There is no piece on that square! Try again!");
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

    /**
     * Method that does intended move and updates position.
     * @param board
     * @param from
     * @param to
     * @param whiteTurn
     * @return
     */
    public static ChessBoard PerformMove(ChessBoard board, String from, String to, boolean whiteTurn){
        var newBoard = board;
        var IntsFrom = CoordinateConvertor.StringToIntCoord(from);
        var IntsTo = CoordinateConvertor.StringToIntCoord(to);
        var targetSquare = newBoard.board[IntsFrom[1]][IntsFrom[0]];
        assert (targetSquare != null);

        if(whiteTurn){

            var movingPiece = targetSquare.getPiece();
            if(movingPiece instanceof Pawn){
                //en passant
                if(IntsFrom[1] == 3 && IntsTo[1] == 2 && IntsFrom[0] != IntsTo[0] && newBoard.board[IntsTo[1]][IntsTo[0]] == null ){
                    var EnPassableSquare = newBoard.board[IntsTo[1]+1][IntsTo[0]];
                    assert (EnPassableSquare != null);
                    newBoard.board[IntsTo[1]+1][IntsTo[0]] = null;
                    var stringPos = CoordinateConvertor.IntToStringCoord(new Integer[]{IntsTo[0],IntsTo[1]+1});
                    int index = newBoard.blackPieces.indexOf(stringPos);
                    newBoard.blackPieces.remove(index);
                }

                //its last square promote to queen
                if(IntsTo[1] == 0){
                    targetSquare = new Square(to, new Queen(new Player(whiteTurn)));
                }
            }

            if(movingPiece instanceof King){

                if(IntsFrom[1] == 7 && IntsFrom[0] == 4 && IntsTo[1] == 7){
                    //move rook and update list
                    if(IntsTo[0] == 6){
                        var rookPos = CoordinateConvertor.IntToStringCoord(new Integer[]{7,7});
                        var rookSquareMoving = newBoard.board[7][7];
                        if(rookSquareMoving == null) {
                            System.out.println("Rook is not there. Validation was unsuccessful");
                        }
                        newBoard.board[7][5] = rookSquareMoving;
                        rookSquareMoving.pos = CoordinateConvertor.IntToStringCoord(new Integer[]{5,7});
                        rookSquareMoving.getPiece().moveCount++;
                        newBoard.board[7][7] = null;
                        newBoard.UpdatePieceLists(rookPos,rookSquareMoving.pos,whiteTurn);
                    }

                    if(IntsTo[0]==2){
                        var rookPos = CoordinateConvertor.IntToStringCoord(new Integer[]{0,7});
                        var rookSquareMoving = newBoard.board[7][0];
                        if(rookSquareMoving == null) {
                            System.out.println("Rook is not there. Validation was unsuccessful");
                        }
                        newBoard.board[7][3] = rookSquareMoving;
                        rookSquareMoving.pos = CoordinateConvertor.IntToStringCoord(new Integer[]{3,7});
                        rookSquareMoving.getPiece().moveCount++;
                        newBoard.board[7][0] = null;
                        newBoard.UpdatePieceLists(rookPos, rookSquareMoving.pos, whiteTurn);
                    }
                }
            }
        } else {
            var movingPiece = targetSquare.getPiece();
            if(movingPiece instanceof Pawn){
                //en passant
                if(IntsFrom[1] == 4 && IntsTo[1] == 5 && IntsFrom[0] != IntsTo[0] && newBoard.board[IntsTo[1]][IntsTo[0]] == null ){
                    var EnPassableSquare = newBoard.board[IntsTo[1]-1][IntsTo[0]];
                    assert (EnPassableSquare != null);
                    newBoard.board[IntsTo[1]-1][IntsTo[0]] = null;
                    var stringPos = CoordinateConvertor.IntToStringCoord(new Integer[]{IntsTo[0],IntsTo[1]-1});
                    int index = newBoard.whitePieces.indexOf(stringPos);
                    newBoard.whitePieces.remove(index);
                }

                if (IntsTo[1] == 7){
                    targetSquare = new Square(to, new Queen( new Player(whiteTurn)));
                }
            }

            if(movingPiece instanceof King){
                if(IntsFrom[1] == 0 && IntsFrom[0] == 4 && IntsTo[1] == 0){
                    //move rook and update list
                    if(IntsTo[0] == 6){
                        var rookPos = CoordinateConvertor.IntToStringCoord(new Integer[]{7,0});
                        var rookSquareMoving = newBoard.board[0][7];
                        if(rookSquareMoving == null) {
                            System.out.println("Rook is not there. Validation was unsuccessful");
                        }
                        newBoard.board[0][5] = rookSquareMoving;
                        rookSquareMoving.pos = CoordinateConvertor.IntToStringCoord(new Integer[]{5,0});
                        rookSquareMoving.getPiece().moveCount++;
                        newBoard.board[0][7] = null;
                        newBoard.UpdatePieceLists(rookPos,rookSquareMoving.pos,whiteTurn);
                    }

                    if(IntsTo[0]==2){
                        var rookPos = CoordinateConvertor.IntToStringCoord(new Integer[]{0,0});
                        var rookSquareMoving = newBoard.board[0][0];
                        if(rookSquareMoving == null) {
                            System.out.println("Rook is not there. Validation was unsuccessful");
                        }
                        newBoard.board[0][3] = rookSquareMoving;
                        rookSquareMoving.pos = CoordinateConvertor.IntToStringCoord(new Integer[]{3,0});
                        rookSquareMoving.getPiece().moveCount++;
                        newBoard.board[0][0] = null;
                        newBoard.UpdatePieceLists(rookPos, rookSquareMoving.pos, whiteTurn);
                    }
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

    /**
     * Dedicated method for pawn promotion to something else than Queen
     * @param chessBoard
     * @param from
     * @param to
     * @param isWhiteSide
     * @param promoTo
     * @return
     */
    public static ChessBoard ProcessPromotion(ChessBoard chessBoard, String from, String to, boolean isWhiteSide, String promoTo){
        var result = chessBoard;
        var IntsFrom = CoordinateConvertor.StringToIntCoord(from);
        var IntsTo = CoordinateConvertor.StringToIntCoord(to);
        var targetSquare = chessBoard.board[IntsFrom[1]][IntsFrom[0]];
        assert (targetSquare != null);
        if(isWhiteSide){
            if(IntsTo[1] != 0){
                throw new IllegalArgumentException("Pawn cannot promote if it is not on the last rank!");
            }
        } else {
            if(IntsTo[1] != 7){
                throw new IllegalArgumentException("Pawn cannot promote if it is not on the last rank!");
            }
        }
        switch (promoTo){
            case "N":
                targetSquare = new Square(to, new Knight(new Player(isWhiteSide)));
                break;
            case "B":
                targetSquare = new Square(to, new Bishop(new Player(isWhiteSide)));
                break;
            case "Q":
                targetSquare = new Square(to, new Queen(new Player(isWhiteSide)));
                break;
            case "R":
                targetSquare = new Square(to, new Rook(new Player(isWhiteSide)));
                break;
            default:
                throw new IllegalArgumentException("Unknown promotion token! Try again");
        }
        result.coordLastPieceMoved = IntsTo;
        result.board[IntsTo[1]][IntsTo[0]] = targetSquare;
        targetSquare.pos = CoordinateConvertor.IntToStringCoord(IntsTo);
        result.board[IntsFrom[1]][IntsFrom[0]] = null;
        targetSquare.getPiece().moveCount++;
        result.UpdatePieceLists(from, to, isWhiteSide);
        return result;
    }


}
