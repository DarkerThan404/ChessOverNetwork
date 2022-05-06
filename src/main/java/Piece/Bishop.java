package Piece;

import Game.ChessBoard;
import Player.Player;

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
    public boolean IsValidMove(ChessBoard board, Integer[] from, Integer[] to, boolean whiteTurn) {
        return false;
    }

    @Override
    public List<String> GenerateValidMoves() {
        return null;
    }
}
