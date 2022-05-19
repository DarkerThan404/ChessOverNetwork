package Piece;

import Game.ChessBoard;
import Game.CoordinateConvertor;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    private String wknight = "wN";
    private String bknight = "bN";
    public Knight(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wknight);
        } else {
            System.out.print(bknight);
        }
    }

    @Override
    public boolean IsValidMove(ChessBoard chessBoard, Integer[] from, Integer[] to, boolean whiteTurn) {
        if(whiteTurn != this.player.isWhiteSide()){
            System.out.println("You cant play with opposite color piece!");
            return false;
        }
        this.validMoves = this.getValidMoves(chessBoard,CoordinateConvertor.IntToStringCoord(from));

        if(validMoves.contains(CoordinateConvertor.IntToStringCoord(to))){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> getValidMoves(ChessBoard chessBoard, String from) {
        var result = new ArrayList<String>();
        var IntCoord = CoordinateConvertor.StringToIntCoord(from);
        int xFrom = IntCoord[0];
        int yFrom = IntCoord[1];

        int[] directionXList = new int[]{2,2,-2,-2,1,1,-1,-1};
        int[] directionYList = new int[]{1,-1,1,-1,2,-2,2,-2};
        int directionListSize = 8;
        var board = chessBoard.board;
        for(int directionIndex = 0; directionIndex < directionListSize; directionIndex++){
            int xTarget = xFrom + directionXList[directionIndex];
            int yTarget = yFrom + directionYList[directionIndex];
            if(xTarget < 8 && yTarget < 8 && xTarget >= 0 && yTarget >= 0){
                var square = board[yTarget][xTarget];
                if(square == null){
                    result.add(CoordinateConvertor.IntToStringCoord(new Integer[]{xTarget, yTarget}));
                } else if(square.getPiece().player.isWhiteSide() != this.player.isWhiteSide()){
                    result.add(CoordinateConvertor.IntToStringCoord(new Integer[]{xTarget, yTarget}));
                }
            }
        }
        return result;
    }
}
