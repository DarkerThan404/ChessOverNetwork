package Game;

import Piece.Piece;
import Square.Square;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private int size;
    public Square[][] board;

    public Integer[] coordLastPieceMoved;
    public List<String> allValidMoves;
    public List<Square> whitePieces;
    public List<Square> blackPieces;
    public ChessBoard(Square[][] board, int size){
        this.size = size;
        this.board = board;
        allValidMoves = new ArrayList<>();
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
    }

    public void ResetMoves(){
        allValidMoves.clear();
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
