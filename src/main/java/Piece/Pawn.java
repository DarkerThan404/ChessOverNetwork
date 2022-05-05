package Piece;

import Game.ChessBoard;
import Player.Player;

public class Pawn extends Piece{
    private char wpawn = '\u2659';
    private char bpawn = '\u265F';
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
