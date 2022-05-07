package Piece;

import Game.ChessBoard;
import Game.CoordinateConvertor;
import Player.Player;

import java.util.ArrayList;

public class Queen extends Piece{
    private String wqueen = "wQ";
    private String bqueen = "bQ";
    public Queen(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wqueen);
        } else {
            System.out.print(bqueen);
        }
    }

    @Override
    public boolean IsValidMove(ChessBoard chessBoard, Integer[] from, Integer[] to, boolean whiteTurn) {
        this.validMoves = new ArrayList<>();
        int xFrom = from[0];
        int yFrom = from[1];
        int xTo = to[0];
        int yTo = to[1];
        int[] directionXList = new int[]{1,0,-1,0,1,-1,1,-1};
        int[] directionYList = new int[]{0,1,0,-1,1,1,-1,-1};
        int directionSize = 8;
        var board = chessBoard.board;
        for(int directionIndex = 0; directionIndex < directionSize; directionIndex++){
            for(int step = 1 ; step < 8; step++){
                int targetX = xFrom + directionXList[directionIndex] * step;
                int targetY = yFrom + directionYList[directionIndex] * step;
                if(targetX < 8 && targetY < 8 && targetX >= 0 && targetY >= 0){ //is in bounds
                    var targetSquare = board[targetY][targetX];
                    if(targetSquare == null){//free space
                        validMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{targetX, targetY}));
                    } else {
                        var piece = targetSquare.getPiece();
                        if(piece.player.isWhiteSide() != this.player.isWhiteSide()){
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
        System.out.println("All piece valid moves");
        for(String move: validMoves){
            System.out.println(move);
        }
        chessBoard.allValidMoves.addAll(this.validMoves);
        if(validMoves.contains(CoordinateConvertor.IntToStringCoord(to))){
            System.out.println("Is valid Queen move");
            return true;
        } else {
            System.out.println("Is invalid rook move");
            return false;
        }
    }
}
