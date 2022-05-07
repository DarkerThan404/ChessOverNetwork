package Square;

import Game.ChessBoard;
import Piece.Piece;

import java.util.List;

public class Square {
    public String pos;
    private Piece piece;

    public Square(String pos, Piece piece){
        this.pos = pos;
        this.piece = piece;
    }


    public Piece getPiece(){
        return piece;
    }
    public List<String> getValidMoves(ChessBoard chessBoard){
        return null;
    }

}
