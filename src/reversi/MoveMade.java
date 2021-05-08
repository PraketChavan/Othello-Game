package reversi;

import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

/**
 * Class to handle the all the move made by the player.
 */

public class MoveMade implements ActionListener {

    private GameState state;
    private GUI.GameCell cell;


    public MoveMade(GameState state){
        this.state = state;
    }

    /**
     * Checks to see if the current move is possible
     * i.e if it is a legal move
     *
     * @return true if yes else false
     */
    public boolean isMovePossible(){
        if (cell.score == 0)
            return false;
        return true;
    }

    /**
     * Updates the gameState, gameCaptureState and the cells when the move made
     * is legal and the move is made by the current player.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.cell = (GUI.GameCell)e.getSource();
        state.updateCaptureState();
        if (cell.frame == Game.currentPlayer && isMovePossible()) {
            cell.update(Game.currentPlayer);
            state.update(cell.getxPos(), cell.getyPos(), Game.currentPlayer);
            state.searchForPieces(cell.getxPos(), cell.getyPos(), Game.currentPlayer, true);
            state.updateCaptureState();
            cell.getGUI().updateFrame(cell.getyPos(), cell.getxPos(), Game.currentPlayer);
            cell.updateAllCells();
            state.print();
            Game.currentPlayer = Game.currentPlayer == 1 ? -1 : 1;
        }
    }
}
