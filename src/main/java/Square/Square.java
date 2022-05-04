package Square;

import Piece.Piece;

public class Square {
    //private int x,y;
    private Piece piece;

    public Square(/*int x, int y,*/ Piece piece){
        //this.x = x;
        //this.y = y;
        this.piece = piece;
    }


    public Piece getPiece(){
        return piece;
    }

}
