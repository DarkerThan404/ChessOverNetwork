package Piece;

import Player.Player;

public class NoPiece extends Piece{
    public NoPiece(Player player) {
        super(player);
    }

    @Override
    public void draw() {
        System.out.print(" ");
    }
}
