package Piece;

import Game.ChessBoard;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Template class for other types of pieces
 */
public abstract class Piece {
    public Player player;
    public int moveCount;
    public List<String> validMoves;

    public Piece(Player player){
        this.player = player;
        moveCount = 0;
        validMoves = new ArrayList<>();
    }

    /**
     * Draws itself
     */
    public abstract void draw();

    /**
     * Validates if input moves are valid used in controller.
     * @param board
     * @param from
     * @param to
     * @param whiteTurn
     * @return
     */
    public abstract boolean IsValidMove(ChessBoard board, Integer[] from, Integer[] to, boolean whiteTurn);

    /**
     * Returns all valid moves that piece can make
     * @param chessBoard
     * @param from
     * @return
     */
    public abstract List<String> getValidMoves(ChessBoard chessBoard, String from);
}
