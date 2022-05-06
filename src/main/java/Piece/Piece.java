package Piece;

import Game.ChessBoard;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    public Player player;
    public int moveCount;
    public List<String> validMoves;

    public Piece(Player player){
        this.player = player;
        moveCount = 0;
        validMoves = new ArrayList<>();
    }

    public abstract void draw();
    public abstract boolean IsValidMove(ChessBoard board, Integer[] from, Integer[] to, boolean whiteTurn);
}
