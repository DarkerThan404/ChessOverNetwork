package Piece;

import Game.ChessBoard;
import Player.Player;

public class NoPiece extends Piece{
    public NoPiece(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        System.out.print(" ");
    }

    @Override
    public boolean IsValidMove(ChessBoard board, Integer[] from, Integer[] to, boolean whiteTurn) {
        return false;
    }
}
