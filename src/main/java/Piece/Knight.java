package Piece;

import Game.ChessBoard;
import Player.Player;

import java.util.List;

public class Knight extends Piece{
    private String wknight = "wN";
    private String bknight = "bN";
    public Knight(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wknight);
        } else {
            System.out.print(bknight);
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
