package Piece;

import Player.Player;

public class Queen extends Piece{
    private char wqueen = '\u2655';
    private char bqueen = '\u265B';
    public Queen(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wqueen);
        } else {
            System.out.print(bqueen);
        }
    }
}
