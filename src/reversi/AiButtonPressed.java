package reversi;

import javax.swing.*;
import java.awt.event.*;

/**
 * Handles the logic for the Greedy AI
 */
public class AiButtonPressed implements ActionListener {

    GUI gui;

    public AiButtonPressed(GUI gui){
        this.gui = gui;
    }

    /**
     * Searches the gameState to find the move where
     * the highest number of cell will be captured.
     *
     * @param player the current player
     * @return in array containing the i, j indexes of the move.
     */
    private int[] searchCaptureState(int player){
        int maxPos[] = new int[2];
        int max = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
               if(gui.getGameState().gameCaptureState[i][j][player==-1?0:1] > max){
                   max = gui.getGameState().gameCaptureState[i][j][player==-1?0:1];
                   maxPos[0] = i;
                   maxPos[1] = j;
               }
            }
        }
        return maxPos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        GUI.GameCell temp =  gui.new GameCell(0, 0,0);
        int[] move;
        if (button.getText().compareTo("Greedy AI (White)")==0) {
            move = searchCaptureState(1);
            temp.getGameCell(move[0], move[1])[0].doClick();
        }
        else {
            move = searchCaptureState(-1);
            temp.getGameCell(move[0], move[1])[1].doClick();
        }

    }
}
