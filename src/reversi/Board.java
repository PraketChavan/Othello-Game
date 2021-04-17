package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <h2>Class representing the current status of the
 * game. Includes information regarding the status of
 * each cell and the current score.</h2>
 */
public class Board extends MouseAdapter {
    /**
     * Holds the info of the current status of the game
     */
    int[][] gameState = new int[8][8];

    /**player1 and player2 are the object references to the
     * two board gui */
    JFrame player1, player2;

    /**
     * The constructor for initialising the gameState and
     * the player1 and player2 boards.
     *
     * @param player1 the object reference to the first gui
     * @param player2 the object reference to the second gui
     */
    public Board(JFrame player1, JFrame player2){
        this.player1 = player1;
        this.player2 = player2;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                this.gameState[i][j] = 0;
        }
        Component arr [] = player1.getComponents();
        System.out.println(arr);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameCell cell = (GameCell)e.getSource();
        System.out.println(cell.xPos + " " + cell.yPos);
    }

    public void update(int moveX, int moveY, int player){

    }

}
