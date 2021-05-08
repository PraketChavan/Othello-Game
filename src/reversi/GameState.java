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
     *
     * @param x      the x position of the play
     * @param y      the y position of the play
     * @param player the current player that is playing
     */
    public void update(int x, int y, int player) {
        gameState[x][y] = player;
        return;
    }

    /**
     * Default constructor to initialise the game
     * board according to the game requirement
     */
    public GameState() {
        gameState[3][3] = 1;
        gameState[3][4] = -1;
        gameState[4][3] = -1;
        gameState[4][4] = 1;
        updateCaptureState();
    }

    /**
     * Will search the neighbouring cells in the gameState
     * array
     *
     * @param x      the i position of the cell to search around
     * @param y      the j position of the cell to search around
     * @param player the player which will be making the move
     * @return return true if the neighbouring cells contains at least
     * one opponent piece else false
     */
    public boolean searchNeighbour(int x, int y, int player) {
        if (gameState[x][y] != 0)
            return false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (x + i >= 8 || x + i < 0 || y + j >= 8 || y + j < 0)
                    continue;
                if (gameState[x + i][y + j] != player && gameState[x + i][y + j] != 0)
                    return true;
            }
        }
        return false;
    }

    public int searchForPieces(int i, int j, int player, boolean capture) {
        boolean flag;
        int captured = 0;
        int xPos, yPos;
        for (int ioffset = -1; ioffset < 2; ioffset++) {
            for (int joffset = -1; joffset < 2; joffset++) {
                if (ioffset == 0 && joffset == 0)
                    continue;

                flag = false;
                xPos = i;
                yPos = j;

                while (true) {
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
                        captured += countPieces(i, j, xPos, yPos, player, capture);
                        break;
                    }

                    if (gameState[xPos][yPos] != player) {
                        flag = true;
                    }
                }
            }
        }
        gameCaptureState[i][j][player == -1 ? 0 : 1] = captured;

        return captured;
    }

    /**
     * Utility function to count the number of pieces that will be captured
     * by the player from i, j till iPos, jPos
     *
     * @param i      the i index of the move to be / made
     * @param j      the j index of the move to be / made
     * @param iPos   the i index of the pieces to capture to
     * @param jPos   the j index of the pieces to capture to
     * @param player the current player playing the move
     * @param flag   set this to be true if you want the gameState to be changed
     *               set this to false if you want to just count the number of pieces that will be captured
     * @return the count of how many pieces were / will be captured
     */
    public int countPieces(int i, int j, int iPos, int jPos, int player, boolean flag) {
        int capturedPieces = 0; //Stores the number of pieces that will be captured

        if (flag) {
            if (i - iPos == 0) {
                while (j != jPos) {
                    gameState[iPos][jPos] = player;
                    jPos -= (jPos - j) / Math.abs(jPos - j);
                    capturedPieces++;
                }
            } else if (j - jPos == 0) {
                while (i != iPos) {
                    gameState[iPos][jPos] = player;
                    iPos -= (iPos - i) / Math.abs(iPos - i);
                    capturedPieces++;
                }

            } else {
                while (i != iPos && j != jPos) {
                    gameState[iPos][jPos] = player;
                    iPos -= (iPos - i) / Math.abs(iPos - i);
                    jPos -= (jPos - j) / Math.abs(jPos - j);
                    capturedPieces++;
                }
            }
        } else {
            if (i == iPos)
                capturedPieces = Math.abs(j - jPos);
            else
                capturedPieces = Math.abs(i - iPos);
        }
        return capturedPieces - 1;
    }

    /**
     * Updates the gameCaptureState array to reflect the current status
     * of the game board.
     */
    public void updateCaptureState () {
        for (int k = -1; k <= 1; k += 2) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (searchNeighbour(i, j, k))
                        searchForPieces(i, j, k, false);
                    else
                        gameCaptureState[i][j][k == -1 ? 0 : 1] = 0;
                }
            }
        }
    }

    public String countPieces(){
        int white = 0;
        int black = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameState[i][j] == 1)
                    white++;
                else if (gameState[i][j] == -1)
                    black++;
            }
        }
        if (black > white)
            return "Black Wins: "+white+":"+black;
        else if (black == white)
            return "Draw: "+white+":"+black;
        else
            return "White Wins: "+white+":"+black;
    }

    public boolean skipMove(int player){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameCaptureState[i][j][player==-1?0:1] != 0)
                    return false;
            }
        }

        return true;
    }

    /**
     * Prints the current board onto the stdout
     * Mostly used for debugging
     */
    public void print (GUI.GameCell cell) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print(gameState[i][j] + "\t");
            System.out.print("\t\t\t");

            for (int j = 0; j < 8; j++)
                System.out.print(gameCaptureState[7 - i][7 - j][0] + "\t");
            System.out.print("\t\t\t");

            for (int j = 0; j < 8; j++)
                System.out.print(gameCaptureState[i][j][1] + "\t");



            System.out.println();
        }
        System.out.println("\t\t\t " + cell.score);
        System.out.println();
    }
}


