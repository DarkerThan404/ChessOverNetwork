package Game;

import Square.Square;

public class ChessBoard {
    private int size;
    private Square[][] board;
    public ChessBoard(Square[][] board, int size){
        this.size = size;
        this.board = board;
    }

    public void draw(){
        for(int x = 0; x < size; x++){
            System.out.print((8 - x  )% 9 + " ");
            for(int y = 0; y < size; y++){
                if(board[x][y] != null){
                    board[x][y].getPiece().draw();
                    System.out.print(" ");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("  a  b c  d  e f  g  h");
    }
}
