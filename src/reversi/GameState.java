package reversi;

/**
 * This class hold the current gameState info
 * such as which player holds a position if any
 */
public class GameState {
    int[][] gameState = new int[8][8];

    /**
     * This array will hold the number of pieces
     * that will be captured once the move is made
     * in that place
     * The third dimension will represent the player
     * i.e player 1 or 2 and will contain different scores/
     */
    int[][][] gameCaptureState = new int[8][8][2];

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
     * Default constructor to initialise the game
     * board according to the game requirement
     */
    public GameState(){
        gameState[3][3] = -1;
        gameState[3][4] = 1;
        gameState[4][3] = 1;
        gameState[4][4] = -1;
    }

    /**
     * Will search the neighbouring cells in the gameState
     * array
     * @param x the i position of the cell to search around
     * @param y the j position of the cell to search around
     * @param player the player which will be making the move
     * @return return true if the neighbouring cells contains at least
     *         one opponent piece else false
     */
    public boolean searchNeighbour(int x, int y, int player){
        for (int i = -1; i <= 1 ; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (x + i >= 8 || x + i < 0 || y + j >= 8 || y + j < 0)
                    continue;
                if (gameState[x + i][y + j] != player && gameState[x + i][y + j] != 0 && gameState[x][y] == 0)
                    return true;
            }
        }
            return false;
    }

    public void searchForPieces(int i, int j, int player){
        boolean flag;
        int xPos, yPos;
        for (int ioffset = -1; ioffset < 2; ioffset++) {
            for (int joffset = -1; joffset < 2 ; joffset++) {
                if (ioffset == 0 && joffset == 0)
                    continue;

                flag = false;
                xPos = i;
                yPos = j;

                while(true) {
                    //adds the offset to the current position
                    xPos += ioffset;
                    yPos += joffset;

                    if (xPos >= 8 || xPos < 0 || yPos >= 8 || yPos < 0)
                        break;

                    //checks if the new position is not empty and has
                    //the opposite players piece
                    if (gameState[xPos][yPos] == 0 || (gameState[xPos][yPos] == player && !flag))
                        break;

                    if (gameState[xPos][yPos] == player && flag) {
                        gameCaptureState[i][j][player==-1?0:1] = capturePieces(i, j, xPos, yPos, player);
                        break;
                    }

                    if (gameState[xPos][yPos] != player) {
                        flag = true;
                    }
                }
            }
        }
    }

    private int capturePieces(int i, int j, int iPos, int jPos, int player) {
        int capturedPieces = 0; //Stores the number of pieces that were captured

        if (i - iPos == 0){
            while (j != jPos){
                //gameState[iPos][jPos] = player;
                jPos -= (jPos-j)/Math.abs(jPos-j);
                capturedPieces++;
            }
        }
        else if (j - jPos == 0){
            while (i != iPos){
               // gameState[iPos][jPos] = player;
                iPos -= (iPos-i)/Math.abs(iPos-i);
                capturedPieces++;
            }

        }
        else {
            while (i != iPos && j != jPos){
                iPos -= (iPos-i)/Math.abs(iPos-i);
                jPos -= (jPos-j)/Math.abs(jPos-j);
                capturedPieces++;
            }
        }

        return capturedPieces - 1;
    }

    /**
     * Updates the gameCaptureState array to reflect the current status
     * of the game board.
     */
    public void updateCaptureState(){
        for (int k = -1; k <= 1; k += 2) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (searchNeighbour(i, j, k))
                        searchForPieces(i, j, k);
                    else
                        gameCaptureState[i][j][k==-1?0:1] = 0;
                }
            }
        }
    }

    public static void main(String[] args){
        GameState ob = new GameState();
        ob.updateCaptureState();
        ob.print();
        ob.gameState[2][4] = 1;
        ob.updateCaptureState();
        ob.print();
    }

    /**
     * Prints the current board onto the stdout
     * Mostly used for debugging
     */
    public void print() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print(gameState[i][j] + "\t");
            System.out.print("\t\t\t");

            for (int j = 0; j < 8; j++)
                System.out.print(gameCaptureState[i][j][0] + "\t");
                System.out.print("\t\t\t");

            for (int j = 0; j < 8; j++)
                System.out.print(gameCaptureState[i][j][1] +"\t");

            System.out.println();
        }
        System.out.println();
    }


}
