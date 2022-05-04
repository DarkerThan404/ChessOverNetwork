package Piece;

import Player.Player;

public class Knight extends Piece{
    private char wknight = '\u2658';
    private char bknight = '\u265E';
    public Knight(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        if(this.player.isWhiteSide()){
            System.out.print(wknight);
        } else {
            System.out.print(bknight);
        }
    }
}