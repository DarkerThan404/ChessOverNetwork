package Piece;

import Game.ChessBoard;
import Game.CoordinateConvertor;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    private String wbishop = "wB";
    private String bbishop = "bB";
    public Bishop(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wbishop);
        } else {
            System.out.print(bbishop);
        }
    }

    @Override
    public boolean IsValidMove(ChessBoard chessBoard, Integer[] from, Integer[] to, boolean whiteTurn) {
        this.validMoves = new ArrayList<>();
        int xFrom = from[0];
        int yFrom = from[1];
        int[] directionXList = new int[]{1,-1, 1,-1};
        int[] directionYList = new int[]{1, 1,-1,-1};
        int directionSize = 4;
        for(int directionIndex = 0; directionIndex < directionSize; directionIndex++){
            int directionX = directionXList[directionIndex];
            int directionY = directionYList[directionIndex];
            for(int step = 1; step < 8; step++){
                int targetX = xFrom + (directionX * step);
                int targetY = yFrom + (directionY * step);
                if(targetX < 8 && targetY < 8 && targetX >= 0 && targetY >= 0){ //is in bounds
                    var board = chessBoard.board;
                    var targetSquare = board[targetY][targetX];
                    if(targetSquare == null){ //free square
                        validMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{targetX, targetY}));
                    } else {
                        var piece = targetSquare.getPiece();
                        if(piece.player.isWhiteSide() != this.player.isWhiteSide()){ //has different color
                            validMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{targetX, targetY}));
                            break;
                        } else {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        chessBoard.allValidMoves.addAll(this.validMoves);
        for(String move : this.validMoves){
            System.out.println(move);
        }
        if(this.validMoves.contains(CoordinateConvertor.IntToStringCoord(to))){
            System.out.println("its possible to move to");
            return true;
        } else {
            return false;
        }

    }

}
