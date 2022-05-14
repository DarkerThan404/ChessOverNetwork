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
                //System.out.println(bpos);
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
                System.out.println(wpos);
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
            int otherIndex = this.blackPieces.indexOf(to);
            if(otherIndex != -1){
                this.blackPieces.remove(otherIndex);
            }

        } else {
            int index = this.blackPieces.indexOf(from);
            this.blackPieces.remove(index);
            this.blackPieces.add(to);
            int otherIndex = this.whitePieces.indexOf(to);
            if(otherIndex != -1){
                this.whitePieces.remove(otherIndex);
            }
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

    // this assumes that piece can make that move
    public boolean wouldBeKingInDanger( String from, String to, boolean isWhiteTurn){
        var result = false;
        var IntFrom = CoordinateConvertor.StringToIntCoord(from);
        var IntTo = CoordinateConvertor.StringToIntCoord(to);
        var temp = this.board[IntFrom[1]][IntFrom[0]];
        this.lastPieceTaken = this.board[IntTo[1]][IntTo[0]];
        if(this.lastPieceTaken != null){
            this.RemovePieceFromList(this.lastPieceTaken.pos, this.lastPieceTaken.getPiece().player.isWhiteSide());
        }
        this.board[IntFrom[1]][IntFrom[0]] = null;
        this.board[IntTo[1]][IntTo[0]] = temp;
        temp.pos = to;
        this.UpdatePieceLists(from, to, isWhiteTurn);
        Square kingSquare = null;
        if(isWhiteTurn){
            for(String wpos : this.whitePieces){
                var coords = CoordinateConvertor.StringToIntCoord(wpos);
                var square = this.board[coords[1]][coords[0]];
                assert (square != null);
                if(square.getPiece() instanceof King){
                    kingSquare = square;
                    break;
                }
            }

            if(this.IsCheck(kingSquare.pos, isWhiteTurn)){
                result = true;
            } else {
                result = false;
            }

        } else {
            for(String pos : this.blackPieces){
                var coords = CoordinateConvertor.StringToIntCoord(pos);
                var square = this.board[coords[1]][coords[0]];
                assert (square != null);
                if(square.getPiece() instanceof King){
                    kingSquare = square;
                    break;
                }
            }

            if(this.IsCheck(kingSquare.pos, isWhiteTurn)){
                result = true;
            } else {
                result = false;
            }
        }
        UndoMove(from, to);
        return result;
    }

    public void UndoMove( String from, String to){
        var IntFrom = CoordinateConvertor.StringToIntCoord(from);
        var IntTo = CoordinateConvertor.StringToIntCoord(to);
        var temp = this.board[IntTo[1]][IntTo[0]];

        this.board[IntTo[1]][IntTo[0]] = this.lastPieceTaken;
        this.board[IntFrom[1]][IntFrom[0]] = temp;
        temp.pos = from;
        this.UpdatePieceLists(to, from, temp.getPiece().player.isWhiteSide());
        if(this.lastPieceTaken != null){
            this.AddPieceToList(this.lastPieceTaken.pos, this.lastPieceTaken.getPiece().player.isWhiteSide());
        }
    }

    public boolean IsCheckMate(boolean isWhiteSide){
        //check if the king is in check
        if(!IsKingInCheck(isWhiteSide)){
            return false;
        }
        //check if king can move out of check
        String kingPosition = getKingPosition(isWhiteSide);
        var IntCoord = CoordinateConvertor.StringToIntCoord(kingPosition);
        var KingSquare = board[IntCoord[1]][IntCoord[0]];
        for(String move : KingSquare.getValidMoves(this)){
            if(!wouldBeKingInDanger(kingPosition, move, isWhiteSide)) {
                return false;
            }
        }

        var AttackingPieces = getAttackingPiece(kingPosition, isWhiteSide);

        for(String move : AttackingPieces){
            System.out.println(move);
        }

        //check if friendly piece can block check

        //check if attacking piece can be taken
        return true;
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

    private List<String> getAttackingPiece(String attackedPos, boolean isWhiteSide){
        var result = new ArrayList<String>();
        if(isWhiteSide){
            for(String bPos : blackPieces){
                var coord = CoordinateConvertor.StringToIntCoord(bPos);
                var targetSquare = board[coord[1]][coord[0]];
                assert (targetSquare != null);
                List<String> pieceMoves = targetSquare.getValidMoves(this);
                for(String pieceMove : pieceMoves){
                    if(pieceMove.equals(attackedPos)){
                        result.add(bPos);
                    }
                }
            }
        } else {
            for(String wPos : whitePieces){
                var coord = CoordinateConvertor.StringToIntCoord(wPos);
                var targetSquare = board[coord[1]][coord[0]];
                assert (targetSquare != null);
                List<String> pieceMoves = targetSquare.getValidMoves(this);
                for(String pieceMove : pieceMoves){
                    if(pieceMove.equals(attackedPos)){
                        result.add(wPos);
                    }
                }
            }
        }
        return result;
    }
}
