package reversi;

import javax.swing.*;

/**
 * Class to handle the main game loop and restart the game
 * if need be. This also displays the messages after the end of each
 * game.
 */
public class Game{
    JFrame[] arr = new JFrame[2];
    static int currentPlayer;
    GUI guiObject;
    boolean restart;

    public Game() {
        restart = true;

        // main game loop which runs till the program is ended
        while(true) {
            //restarts the game if the criteria are met
            if (restart) {
                initGame();
                restart = false;
            }
            //checks to see if the any move is possible by both players. if not then the game ends with the
            //appropriate message
            if (guiObject.state.skipMove(Game.currentPlayer) && guiObject.state.skipMove(Game.currentPlayer == 1 ? -1 : 1)) {
                JOptionPane optionPane = new JOptionPane();
                JOptionPane.showMessageDialog(arr[0], guiObject.getGameState().countPieces());
                restart = true;
                arr[0].dispose();
                arr[1].dispose();
            } else if (guiObject.state.skipMove(Game.currentPlayer)) //checks to see if the current player makes the move or not
                Game.currentPlayer = Game.currentPlayer == 1 ? -1 : 1;
        }
    }

    /**
     * Initialises all the necessary attributes to the correct
     * state and starts a new game.
     */
    public void initGame() {
        currentPlayer = 1;
        guiObject = new GUI();
        arr = guiObject.getFrames();
    }
}
