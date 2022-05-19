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
        if(whiteTurn != this.player.isWhiteSide()){
            System.out.println("You cant play opposite color piece!");
            return false;
        }
        this.validMoves = this.getValidMoves(chessBoard,CoordinateConvertor.IntToStringCoord(from));

        if(this.validMoves.contains(CoordinateConvertor.IntToStringCoord(to))){
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<String> getValidMoves(ChessBoard chessBoard, String from) {
        var result = new ArrayList<String>();
        var IntFrom = CoordinateConvertor.StringToIntCoord(from);
        int xFrom = IntFrom[0];
        int yFrom = IntFrom[1];
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
                        result.add(CoordinateConvertor.IntToStringCoord(new Integer[]{targetX, targetY}));
                    } else {
                        var piece = targetSquare.getPiece();
                        if(piece.player.isWhiteSide() != this.player.isWhiteSide()){ //has different color
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
