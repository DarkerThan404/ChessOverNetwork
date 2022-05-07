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
    public List<String> whitePieces;
    public List<String> blackPieces;
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

    /// pos is position of king
    public boolean IsCheck(String pos, boolean isWhiteSide){
        if(isWhiteSide){
            for(String bpos : blackPieces){
                var bCoord = CoordinateConvertor.StringToIntCoord(bpos);
                var availableMoves = board[bCoord[1]][bCoord[0]].getValidMoves(this);
                for(String move :availableMoves){
                    if(pos.equals(move)){
                        return true;
                    }
                }
            }
        } else {
            for (String wpos : whitePieces){
                var wCoord = CoordinateConvertor.StringToIntCoord(wpos);
                var availableMoves = board[wCoord[1]][wCoord[0]].getValidMoves(this);
                for(String move : availableMoves){
                    if(pos.equals(move)){
                        return true;
                    }
                }
            }
        }
        return false;
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
