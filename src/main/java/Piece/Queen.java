package Piece;

import Game.ChessBoard;
import Game.CoordinateConvertor;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

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
        if(whiteTurn != this.player.isWhiteSide()){
            System.out.println("You cant play with opposite color piece!");
            return false;
        }
        this.validMoves = this.getValidMoves(chessBoard, CoordinateConvertor.IntToStringCoord(from));

        if(validMoves.contains(CoordinateConvertor.IntToStringCoord(to))){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> getValidMoves(ChessBoard chessBoard, String from) {
        var IntFrom = CoordinateConvertor.StringToIntCoord(from);
        var result = new ArrayList<String>();
        int xFrom = IntFrom[0];
        int yFrom = IntFrom[1];

        int[] directionXList = new int[]{1,0,-1,0,1,-1,1,-1};
        int[] directionYList = new int[]{0,1,0,-1,1,1,-1,-1};
        int directionListSize = 8;
        var board = chessBoard.board;
        for(int directionIndex = 0; directionIndex < directionListSize; directionIndex++){
            for(int step = 1 ; step < 8; step++){
                int targetX = xFrom + directionXList[directionIndex] * step;
                int targetY = yFrom + directionYList[directionIndex] * step;
                if(targetX < 8 && targetY < 8 && targetX >= 0 && targetY >= 0){ //is in bounds
                    var targetSquare = board[targetY][targetX];
                    if(targetSquare == null){//free space
                        result.add(CoordinateConvertor.IntToStringCoord(new Integer[]{targetX, targetY}));
                    } else {
                        var piece = targetSquare.getPiece();
                        if(piece.player.isWhiteSide() != this.player.isWhiteSide()){
                            result.add(CoordinateConvertor.IntToStringCoord(new Integer[]{targetX, targetY}));
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
        return result;
    }
}
