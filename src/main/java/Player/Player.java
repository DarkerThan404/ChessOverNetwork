package Player;

/**
 * Keep track of who player is
 */
public class Player {
    private boolean isWhiteside;
    public Player(boolean isWhiteside){
        this.isWhiteside = isWhiteside;
    }

    /**
     * Get the color of the player
     * @return True, if it's white's turn, false if it's black's turn
     */
    public boolean isWhiteSide(){return isWhiteside;}

}
