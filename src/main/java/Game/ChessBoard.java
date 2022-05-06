package Game;

import Piece.Piece;
import Square.Square;

import java.util.List;

public class ChessBoard {
    private int size;
    public Square[][] board;
    //public Piece lastPieceMoved;
    public Integer[] coordLastPieceMoved;
    public List<String> allValidMoves;
    public ChessBoard(Square[][] board, int size){
        this.size = size;
        this.board = board;
    }

    public void ResetMoves(){
        allValidMoves.clear();
    }

    public Piece GetSquereOfLastPiece(){
        var square = this.board[coordLastPieceMoved[1]][coordLastPieceMoved[0]];
        if(square == null){
            return null;
        }

        return square.getPiece();
    }

    public void draw(){
        for(int x = 0; x < size; x++){
            System.out.print((8 - x  )% 9 + " ");
            for(int y = 0; y < size; y++){
                if(board[x][y] != null){
                    board[x][y].getPiece().draw();
                    System.out.print(" ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }
}
