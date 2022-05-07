package Piece;

import Game.ChessBoard;
import Game.CoordinateConvertor;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    private String wpawn = "wP";
    private String bpawn = "bP";
    public Pawn(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wpawn);
        } else {
            System.out.print(bpawn);
        }
    }

    @Override
    public boolean IsValidMove(ChessBoard chessBoard, Integer[] from, Integer[] to, boolean whiteTurn) {

        List<String> allValidPieceMoves = new ArrayList<>();
        Integer xFrom = from[0];
        Integer yFrom = from[1];
        var board = chessBoard.board;
        if(whiteTurn != this.player.isWhiteSide()){
            System.out.println("You cant play with this piece!");
            return false;
        }
        if(this.player.isWhiteSide() == true){

            if(this.moveCount == 0 && board[yFrom - 1][xFrom] == null && board[yFrom - 2][xFrom] == null){ //its first move

                var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom , (yFrom - 2)});

                allValidPieceMoves.add(strCoord);
            }

            if(board[yFrom - 1][xFrom] == null){
                var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom, yFrom - 1 });

                allValidPieceMoves.add(strCoord);
            }

            if(xFrom - 1 >= 0){
                var diagonalPiece = board[yFrom - 1][xFrom - 1];
                if(diagonalPiece != null){
                    if(diagonalPiece.getPiece().player.isWhiteSide() == false){ //false = black
                        var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom - 1, yFrom - 1});

                        allValidPieceMoves.add(strCoord);
                    }
                }
            }

            if(xFrom + 1 < 8){
                var diagonalPiece = board[yFrom - 1][xFrom + 1];
                if(diagonalPiece != null){
                    if(diagonalPiece.getPiece().player.isWhiteSide() == false){
                        var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom + 1, yFrom - 1});

                        allValidPieceMoves.add(strCoord);
                    }
                }
            }

            if(yFrom == 3){ //en passent
                var lastMovedPos = chessBoard.coordLastPieceMoved;
                var lastMovedSquare = board[lastMovedPos[1]][lastMovedPos[0]];
                if(lastMovedSquare != null){
                    var lastMovedPiece = lastMovedSquare.getPiece();
                    if(lastMovedPiece instanceof Pawn && lastMovedPiece.moveCount == 1){
                        if(lastMovedPos[1] == yFrom){
                            //System.out.println("Should not be executed");
                            if(lastMovedPos[0] == xFrom + 1){
                                allValidPieceMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom + 1, yFrom - 1}));
                            }

                            if(lastMovedPos[0] == xFrom - 1){
                                allValidPieceMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom - 1, yFrom - 1}));
                            }
                        }
                    }
                }
            }
            this.validMoves = allValidPieceMoves;
            chessBoard.allValidMoves.addAll(allValidPieceMoves);
            if(allValidPieceMoves.contains(CoordinateConvertor.IntToStringCoord(to))){
                return true;
            } else {
                return false;
            }

        } else {
            System.out.println("Black side is playing");
            if(this.moveCount == 0 && board[yFrom + 1][xFrom] == null && board[yFrom + 2][xFrom] == null){ //its first move

                var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom , (yFrom + 2)});

                allValidPieceMoves.add(strCoord);
            }

            if(board[yFrom + 1][xFrom] == null){
                var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom, yFrom + 1 });

                allValidPieceMoves.add(strCoord);
            }

            if(xFrom - 1 >= 0){
                var diagonalPiece = board[yFrom + 1][xFrom - 1];
                if(diagonalPiece != null){
                    if(diagonalPiece.getPiece().player.isWhiteSide() == true){
                        var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom - 1, yFrom + 1});

                        allValidPieceMoves.add(strCoord);
                    }
                }
            }

            if(xFrom + 1 < 8){
                var diagonalPiece = board[yFrom + 1][xFrom + 1];
                if(diagonalPiece != null){
                    if(diagonalPiece.getPiece().player.isWhiteSide() == true){
                        var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom + 1, yFrom + 1});

                        allValidPieceMoves.add(strCoord);
                    }
                }
            }

            if(yFrom == 4){ //en passent

                var lastMovedPos = chessBoard.coordLastPieceMoved;

                var lastMovedSquare = board[lastMovedPos[1]][lastMovedPos[0]];
                if(lastMovedSquare != null){

                    var lastMovedPiece = lastMovedSquare.getPiece();
                    if(lastMovedPiece instanceof Pawn && lastMovedPiece.moveCount == 1){
                        if(lastMovedPos[1] == yFrom){

                            if(lastMovedPos[0] == xFrom + 1){
                                allValidPieceMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom + 1, yFrom + 1}));
                            }

                            if(lastMovedPos[0] == xFrom - 1){
                                allValidPieceMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom - 1, yFrom + 1}));
                            }
                        }
                    }
                }
            }
            this.validMoves = allValidPieceMoves;
            chessBoard.allValidMoves.addAll(allValidPieceMoves);
            if(allValidPieceMoves.contains(CoordinateConvertor.IntToStringCoord(to))){
                return true;
            } else {
                return false;
            }
        }
    }



}
