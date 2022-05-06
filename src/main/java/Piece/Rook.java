package Piece;

import Game.ChessBoard;
import Player.Player;

import java.util.List;

public class Rook extends Piece{
    private String wrook = "wR";
    private String brook = "bR";
    public Rook(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wrook);
        } else {
            System.out.print(brook);
        }
    }

    @Override
    public boolean IsValidMove(ChessBoard board, Integer[] from, Integer[] to, boolean whiteTurn) {
        return false;
    }

    @Override
    public List<String> GenerateValidMoves() {
        return null;
    }
}
