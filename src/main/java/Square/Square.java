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
     * Return piece that is standing on this square
     * @return
     */
    public Piece getPiece(){
        return piece;
    }

    /**
     * Return valid moves with respect to current chessboard situation.
     * @param chessBoard
     * @return
     */
    public List<String> getValidMoves(ChessBoard chessBoard){
        return piece.getValidMoves(chessBoard, pos);
    }
}
