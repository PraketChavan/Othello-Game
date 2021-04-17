package reversi;

/**
 * This class hold the current gameState info
 * such as which player holds a position if any
 */
public class GameState {
    int[][] gameState = new int[8][8];

    /**
     * Method to update the gameState when a  move is made
     * @param x the x position of the play
     * @param y the y position of the play
     * @param player the current player that is playing
     */
    public void update (int x, int y, int player){
        gameState[x][y] = player;
        return;
    }

    /**
     * Prints the current board onto the stdout
     * Mostly used for debugging
     */
    public void print() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print(gameState[i][j] + "\t");
            System.out.println();
        }
    }


}
