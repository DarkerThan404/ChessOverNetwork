package Game;

import Piece.King;
import Square.Square;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private int size;
    public Square[][] board;

    public Integer[] coordLastPieceMoved;
    public Square lastPieceTaken;
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
                System.out.println(bpos);
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

    public void UpdatePieceLists(String from, String to, boolean isWhiteTurn){
        if(isWhiteTurn){
            int index = this.whitePieces.indexOf(from);
            this.whitePieces.remove(index);
            this.whitePieces.add(to);

        } else {
            int index = this.blackPieces.indexOf(from);
            this.blackPieces.remove(index);
            this.blackPieces.add(to);
        }
    }

    public void RemovePieceFromList(String target, boolean isWhiteTurn){
        if(isWhiteTurn){
            int index = this.whitePieces.indexOf(target);
            assert (index != -1);
            this.whitePieces.remove(index);
        } else {
            int index = this.blackPieces.indexOf(target);
            assert (index != 1);
            this.blackPieces.remove(index);
        }
    }

    public void AddPieceToList(String target, boolean isWhiteTurn){
        if(isWhiteTurn){
            this.whitePieces.add(target);
        } else {
            this.blackPieces.add(target);
        }
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

    public boolean IsKingInCheck(boolean isWhiteSide){
        var targetPos = "";
        if(isWhiteSide){
            for(String pos : whitePieces){
                var coord = CoordinateConvertor.StringToIntCoord(pos);
                var targetSquare = board[coord[1]][coord[0]];
                assert (targetSquare != null);
                if(targetSquare.getPiece() instanceof King){
                    targetPos = pos;
                    break;
                }
            }
        } else {
            for(String pos : blackPieces){
                var coord = CoordinateConvertor.StringToIntCoord(pos);
                var targetSquare = board[coord[1]][coord[0]];
                assert (targetSquare != null);
                if(targetSquare.getPiece() instanceof King){
                    targetPos = pos;
                }
            }
        }
        return this.IsCheck(targetPos, isWhiteSide);
    }

    public boolean IsCheckMate(boolean isWhiteSide){
        //check if the king is in check
        if(!IsKingInCheck(isWhiteSide)){
            return false;
        }
        //check if king can move out of check
        String pos = getKingPosition(isWhiteSide);
        var IntCoord = CoordinateConvertor.StringToIntCoord(pos);
        var KingSquare = board[IntCoord[1]][IntCoord[0]];
        for(String move : KingSquare.getValidMoves(this)){
            if(!IsCheck(move,isWhiteSide)) return false;
        }

        //check if friendly piece can block check

        //check if attacking piece can be taken
        return false;
    }

    private String getKingPosition(boolean isWhiteSide){
        var result = "";
        if(isWhiteSide){
            for(String pos : whitePieces){
                var coord = CoordinateConvertor.StringToIntCoord(pos);
                var targetSquare = board[coord[1]][coord[0]];
                assert (targetSquare != null);
                if(targetSquare.getPiece() instanceof King){
                    result = pos;
                    break;
                }
            }
        } else {
            for(String pos : blackPieces){
                var coord = CoordinateConvertor.StringToIntCoord(pos);
                var targetSquare = board[coord[1]][coord[0]];
                assert (targetSquare != null);
                if(targetSquare.getPiece() instanceof King){
                    result = pos;
                    break;
                }
            }
        }
        return result;
    }
}
