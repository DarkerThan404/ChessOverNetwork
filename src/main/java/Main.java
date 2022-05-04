import Game.*;

public class Main {
    public static void main(String[] args){
        int[][] lol = new int[8][8];
        for(int i = 0; i < 8; i++){
            lol[1][i] = 4;
            lol[6][i] = 3;
        }

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(lol[i][j] + " ");
            }
            System.out.println();
        }
        Game game = new Game();
        game.run();
        System.out.println("Hello world");
        System.out.println("\u2657");
        System.out.println("\u265D");
    }
}
