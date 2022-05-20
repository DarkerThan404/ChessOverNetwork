package Square;

import Game.ChessBoard;
import Piece.Piece;

import java.util.List;

/**
 * Keep track of piece and position on chessboard
 */
public class Square {
    public String pos;
    private Piece piece;

    public Square(String pos, Piece piece){
        this.pos = pos;
        this.piece = piece;
    }

    /**
     * Getter for piece on square
     * @return Piece
     */
    public Piece getPiece(){
        return piece;
    }

    /**
     * Return valid moves with respect to current chessboard situation.
     * @param chessBoard chessboard that keeps state of game
     * @return All moves that piece can take with given chessboard situation
     */
    public List<String> getValidMoves(ChessBoard chessBoard){
        return piece.getValidMoves(chessBoard, pos);
    }
}
