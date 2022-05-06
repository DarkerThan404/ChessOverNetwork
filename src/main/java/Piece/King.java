package Piece;

import Game.ChessBoard;
import Player.Player;

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
    public boolean IsValidMove(ChessBoard board, Integer[] from, Integer[] to, boolean whiteTurn) {
        return false;
    }

    @Override
    public List<String> GenerateValidMoves() {
        return null;
    }
}
