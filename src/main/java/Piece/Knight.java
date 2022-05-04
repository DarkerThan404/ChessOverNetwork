package Piece;

import Player.Player;

public class Knight extends Piece{
    private char wknight = '\u265E';
    private char bknight = '\u2658';
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
