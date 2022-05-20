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
     * @param chessBoard chessboard that keeps state of game
     * @param from Position before move would have been taken
     * @param to Position after move would have been taken
     * @param whiteTurn True, if it's white's turn, false if it's black's turn
     * @return
     */
    public abstract boolean IsValidMove(ChessBoard chessBoard, Integer[] from, Integer[] to, boolean whiteTurn);

    /**
     * Returns all valid moves that piece can make
     * @param chessBoard chessboard that keeps state of game
     * @param from Position of piece getting valid moves from
     * @return
     */
    public abstract List<String> getValidMoves(ChessBoard chessBoard, String from);
}
