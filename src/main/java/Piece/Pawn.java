package Piece;

import Game.ChessBoard;
import Player.Player;

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
        if(this.moveCount == 0){}
        return false;
    }

    @Override
    public List<String> GenerateValidMoves() {
        return null;
    }


}
