package Piece;

import Player.Player;

public abstract class Piece {
    public Player player;

    public Piece(Player player){
        this.player = player;
    }

    public abstract void draw();
}
