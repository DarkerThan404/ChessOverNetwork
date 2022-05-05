package Piece;

import Game.ChessBoard;
import Player.Player;

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
    public boolean IsValidMove(ChessBoard board, Integer[] from, Integer[] to, boolean whiteTurn) {
        return false;
    }
}
