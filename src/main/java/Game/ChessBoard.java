package Game;

import Piece.King;
import Piece.Rook;
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
                //System.out.println(wpos);
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
            System.out.print(( 8 - x  ) % 9 + " ");
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
        var KingPosition = getKingPosition(isWhiteTurn);
        var KingCoord = CoordinateConvertor.StringToIntCoord(KingPosition);
        Square kingSquare = board[KingCoord[1]][KingCoord[0]];
        assert (kingSquare != null);
        assert (kingSquare.getPiece() instanceof King);

        if(this.IsCheck(kingSquare.pos, isWhiteTurn)){
            result = true;
        } else {
            result = false;
        }

        UndoMove(from, to);
        return result;
    }

    private void UndoMove(String from, String to){
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

    public boolean CanKingCastle(String from, String to){
        var fromCoord = CoordinateConvertor.StringToIntCoord(from);
        var toCoord = CoordinateConvertor.StringToIntCoord(to);
        var fromSquare = this.board[fromCoord[1]][fromCoord[0]];
        var toSquare = this.board[toCoord[1]][toCoord[0]];
        if(fromSquare == null || toSquare != null){
            return false;
        }
        var squarePiece = fromSquare.getPiece();
        if(!(squarePiece instanceof King)) return false;
        var isWhiteSide = squarePiece.player.isWhiteSide();
        if(squarePiece.moveCount != 0) return false;

        if(isWhiteSide){
            if(fromCoord[1] != 0 && fromCoord[0] != 4 && toCoord[1] != 0){
                return false;
            }
            if(toCoord[0] == 6){
                if(board[0][5] != null || board[0][6] != null){
                    return false;
                }
                var rookSquare = board[0][7];

                if(rookSquare == null) return false;

                var rookPiece = rookSquare.getPiece();

                //rook could have moved back and forth
                if(rookPiece.moveCount != 0) return false;

                var neighbourLeft = CoordinateConvertor.IntToStringCoord(new Integer[]{5,0});
                var neighbourLeftLeft = CoordinateConvertor.IntToStringCoord(new Integer[]{6,0});
                if(wouldBeKingInDanger(from, neighbourLeft, true)){
                    return false;
                }
                if(wouldBeKingInDanger(from, neighbourLeftLeft, true)){
                    return false;
                }
                return true;
            }
            if(toCoord[0] == 2){
                if(board[0][1] != null || board[0][2] != null || board[0][3] != null) return false;
                var rookSquare = board[0][0];
                if(rookSquare == null) return false;

                var rookPiece = rookSquare.getPiece();

                if(rookPiece.moveCount != 0) return false;

                var neighbourRight = CoordinateConvertor.IntToStringCoord(new Integer[]{3,0});
                var neighbourRightRight = CoordinateConvertor.IntToStringCoord(new Integer[]{2,0});
                if(wouldBeKingInDanger(from, neighbourRight, true)){
                    return false;
                }
                if(wouldBeKingInDanger(from, neighbourRightRight, true)){
                    return false;
                }
                return  true;
            }
            //default value
            return false;
        } else {
            if(fromCoord[1] != 7 || fromCoord[0] != 4 || toCoord[1] != 7){
                return false;
            }
            if(toCoord[0] == 6){
                if(board[7][5] != null || board[7][6] != null){
                    return false;
                }
                var rookSquare = board[7][7];

                if(rookSquare == null) return false;

                var rookPiece = rookSquare.getPiece();

                //rook could have moved back and forth
                if(rookPiece.moveCount != 0) return false;

                var neighbourLeft = CoordinateConvertor.IntToStringCoord(new Integer[]{5,7});
                var neighbourLeftLeft = CoordinateConvertor.IntToStringCoord(new Integer[]{6,7});
                if(wouldBeKingInDanger(from, neighbourLeft, false)){
                    return false;
                }
                if(wouldBeKingInDanger(from, neighbourLeftLeft, false)){
                    return false;
                }
                return true;
            }
            if(toCoord[0] == 2){
                if(board[7][1] != null || board[7][2] != null || board[7][3] != null) return false;
                var rookSquare = board[7][0];
                if(rookSquare == null) return false;

                var rookPiece = rookSquare.getPiece();

                if(rookPiece.moveCount != 0) return false;

                var neighbourRight = CoordinateConvertor.IntToStringCoord(new Integer[]{3,7});
                var neighbourRightRight = CoordinateConvertor.IntToStringCoord(new Integer[]{2,7});
                if(wouldBeKingInDanger(from, neighbourRight, false)){
                    return false;
                }
                if(wouldBeKingInDanger(from, neighbourRightRight, false)){
                    return false;
                }
                return  true;
            }
            return false;
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
        assert (AttackingPieces.size() != 0);

        for(String move : AttackingPieces){
            System.out.println("Attacking piece: " + move);
        }

        //there are more than 2 pieces attacking the king and king cannot move, checkmate
        if(AttackingPieces.size() > 1){
            return true;
        }
        //check if friendly piece can block check

        //check if attacking piece can be taken
        if(isWhiteSide){
            List<String> pieceCopy = this.whitePieces.stream().toList();
            for(String pos : pieceCopy){
                var CoordInt = CoordinateConvertor.StringToIntCoord(pos);
                var pieceSquare = board[CoordInt[1]][CoordInt[0]];
                assert (pieceSquare != null);
                for(String move : pieceSquare.getValidMoves(this)){
                    if(!wouldBeKingInDanger(pos,move,true)) return false;
                }
            }
        } else {
            List<String> pieceCopy = this.blackPieces.stream().toList();
            for(String pos : pieceCopy){
                var CoordInt = CoordinateConvertor.StringToIntCoord(pos);
                var pieceSquare = board[CoordInt[1]][CoordInt[0]];
                assert (pieceSquare != null);
                for(String move : pieceSquare.getValidMoves(this)){
                    if(!wouldBeKingInDanger(pos,move,false)) return false;
                }
            }
        }
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
