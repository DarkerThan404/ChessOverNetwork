package Piece;

import Game.ChessBoard;
import Game.CoordinateConvertor;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    private String wking = "wK";
    private String bking = "bK";
    public King(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wking);
        } else {
            System.out.print(bking);
        }
    }

    @Override
    public boolean IsValidMove(ChessBoard chessBoard, Integer[] from, Integer[] to, boolean whiteTurn) {
        if(whiteTurn != this.player.isWhiteSide()){
            System.out.println("You cant play with this piece!");
            return false;
        }
        this.validMoves = new ArrayList<>();
        int xFrom = from[0];
        int yFrom = from[1];

        var board = chessBoard.board;
        for(int x = -1; x < 2;x++){
            for(int y = -1; y < 2; y++){
                if(x != 0 || y != 0){
                    int xTarget = xFrom + x;
                    int yTarget = yFrom + y;
                    if(xTarget < 8 && yTarget < 8 && xTarget >= 0 && yTarget >= 0){
                        var targetSquare = board[yTarget][xTarget];
                        if(targetSquare == null){
                            this.validMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{xTarget, yTarget}));
                        } else if (targetSquare.getPiece().player.isWhiteSide() != whiteTurn){
                            this.validMoves.add(CoordinateConvertor.IntToStringCoord(new Integer[]{xTarget, yTarget}));
                        }
                    }
                }
            }
        }

        chessBoard.allValidMoves.addAll(this.validMoves);
        if(this.validMoves.contains(CoordinateConvertor.IntToStringCoord(to))){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> getValidMoves(ChessBoard chessBoard, String from) {
        return null;
    }

}
