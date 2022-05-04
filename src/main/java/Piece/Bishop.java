package Piece;

import Player.Player;

public class Bishop extends Piece{
    private char wbishop = '\u2657';
    private char bbishop = '\u265D';
    public Bishop(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wbishop);
        } else {
            System.out.print(bbishop);
        }
    }
}
