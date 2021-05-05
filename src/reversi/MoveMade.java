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

    public boolean isMovePossible(){
        if (cell.score == 0)
            return false;
        return true;
    }

    public void updateCell(int x, int y, int player){
        GUI.GameCell cell[];
        cell = this.cell.getGameCell(x, y);
        cell[0].update(player);
        cell[1].update(player);
    }

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
            state.print(this.cell);
            Game.currentPlayer = Game.currentPlayer == 1 ? -1 : 1;
        }
    }
}
