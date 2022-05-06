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
        System.out.println("Piece moving from, x:" + from[0] + ", y: " + from[1]);
        System.out.println("Piece moving to, x:" + to[0] + ", y: " + to[1]);
        System.out.println("Converted from: " + CoordinateConvertor.IntToStringCoord(from));
        System.out.println("Converted to: " + CoordinateConvertor.IntToStringCoord(to));
        List<String> allValidPieceMoves = new ArrayList<>();
        Integer xFrom = from[0];
        Integer yFrom = from[1];
        var board = chessBoard.board;
        if(whiteTurn != this.player.isWhiteSide()){
            System.out.println("Incorrect color to move from");
            return false;
        }
        if(this.player.isWhiteSide() == true){
            //System.out.println("White side is playing");
            if(this.moveCount == 0 && board[yFrom - 1][xFrom] == null && board[yFrom - 2][xFrom] == null){ //its first move
                //System.out.println("Y: " + yFrom + ",X: " +xFrom);
                var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom , (yFrom - 2)});
                //System.out.println("First move of two: " + strCoord);
                allValidPieceMoves.add(strCoord);
            }

            if(board[yFrom - 1][xFrom] == null){
                var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom, yFrom - 1 });
                //System.out.println("Just a first move: " + strCoord);
                allValidPieceMoves.add(strCoord);
            }

            if(xFrom - 1 >= 0){
                var diagonalPiece = board[yFrom - 1][xFrom - 1];
                if(diagonalPiece != null){
                    if(diagonalPiece.getPiece().player.isWhiteSide() == false){ //false = black
                        var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom, yFrom - 1});
                        //System.out.println("Can take left: " + strCoord);
                        allValidPieceMoves.add(strCoord);
                    }
                }
            }

            if(xFrom + 1 < 8){
                var diagonalPiece = board[yFrom - 1][xFrom + 1];
                if(diagonalPiece != null){
                    if(diagonalPiece.getPiece().player.isWhiteSide() == false){
                        var strCoord = CoordinateConvertor.IntToStringCoord(new Integer[]{xFrom - 1, yFrom + 1});
                        System.out.println("Can take right: " + strCoord);
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
                            //System.out.println("Should not be executed");
                            if(lastMovedPos[0] == xFrom + 1){
                                allValidPieceMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{yFrom - 1, xFrom + 1}));
                            }

                            if(lastMovedPos[0] == xFrom - 1){
                                allValidPieceMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{yFrom - 1, xFrom - 1}));
                            }
                        }
                    }
                }
            }
            System.out.println("All moves: ");
            for (String move: allValidPieceMoves){
                System.out.println(move);
            }
            if(allValidPieceMoves.contains(CoordinateConvertor.IntToStringCoord(to))){
                chessBoard.allValidMoves.addAll(allValidPieceMoves);
                System.out.println("Lol");
            } else {
                System.out.println("Omegalul");
            }

        } else {
            System.out.println("Black side is playing");
        }
        return false;
    }



}
