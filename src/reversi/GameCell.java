package reversi;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <h2>This class represents each cell of the game
 * it will hold the information for each cell.</h2>
 */
public class GameCell extends JButton {
    /**
     * The variable hold the information about the cell;
     * which player posses the cell if any.
     * <ul>
     * <li>1 - White player</li>
     * <li>0 - Empty Cell</li>
     * <li>-1 - Black Player</li>
     * </ul>
     */
    int status;

    int neighbour;
    int xPos, yPos;


    public GameCell(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.setPreferredSize(new Dimension(40, 40));
        this.setBackground(Color.green);
        this.setOpaque(true);
        this.status = 0;
    }

    public void update(int newStatus) {
        this.status = newStatus;
    }

    public void changePiece() {
        if (status == 1) {

        }
    }
}
