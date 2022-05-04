package Piece;

import Player.Player;

public class King extends Piece{
    private char wking = '\u2654';
    private char bking = '\u265A';
    public King(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wking);
        } else {
            System.out.print(bking);
        }
    }
}
