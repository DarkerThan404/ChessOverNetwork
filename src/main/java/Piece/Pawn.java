package Piece;

import Player.Player;

public class Pawn extends Piece{
    private char wpawn = '\u2659';
    private char bpawn = '\u265F';
    public Pawn(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wpawn);
        } else {
            System.out.print(bpawn);
        }
    }
}
