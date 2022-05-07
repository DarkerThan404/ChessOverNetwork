package Square;

import Piece.Piece;

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

}
