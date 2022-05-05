package Piece;

import Game.ChessBoard;
import Player.Player;

public class Queen extends Piece{
    private char wqueen = '\u2655';
    private char bqueen = '\u265B';
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
    public boolean IsValidMove(ChessBoard board, Integer[] from, Integer[] to, boolean whiteTurn) {
        if(this.player.isWhiteSide() != whiteTurn){
            System.out.println("Mismatch colors");
            return false;
        }
        return true;
    }
}
