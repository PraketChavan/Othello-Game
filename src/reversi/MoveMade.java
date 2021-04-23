package reversi;

import java.awt.event.*;

/**
 * Class to handle the all the move made by the player.
 */

public class MoveMade implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GUI.GameCell cell = (GUI.GameCell)e.getSource();
        if (cell.frame == Game.currentPlayer) {
            cell.update(Game.currentPlayer);
            GUI.state.update(cell.xPos, cell.yPos, Game.currentPlayer);
            GUI.state.print();
            cell.getGUI().updateFrame(cell.yPos, cell.xPos, Game.currentPlayer);
            Game.currentPlayer = Game.currentPlayer == 1 ? -1 : 1;
        }
    }
}
