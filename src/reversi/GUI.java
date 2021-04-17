package reversi;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

/**
 * This class deals with all the GUI interface
 * for the game.
 */
public class GUI {
    JFrame player1Frame, player2Frame;
    JFrame [] arr = new JFrame[2];
    JPanel player1Panel, player2Panel, title1, title2;
    JButton greedyAI1, greedyAI2;
    GameCell[] player1Label = new GameCell[64];
    GameCell[] player2Label =  new GameCell[64];

    public GUI(){
        player1Frame = new JFrame("Player 1");
        player2Frame = new JFrame("Player 2");
        initialise(player1Frame, player1Panel, title1, greedyAI1, player1Label);
        initialise(player2Frame, player2Panel, title2, greedyAI2, player2Label);
    }

    /**
     * Method to initialise the Board UI
     * @param frame the frame that will be initialised
     * @param panel the panel that will be added to the frame and will contain
     *              Gamecells
     * @param title the panel which contains the title/player info
     * @param AI the button which will allow the user to let the AI play the move
     * @param labels the array that will hold the references to the GameCell objects
     *
     */
    private void initialise(JFrame frame, JPanel panel, JPanel title, JButton AI,  GameCell[] labels){
        panel = new JPanel();
        title = new JPanel();
        AI = new JButton("Greedy AI");
        frame.setDefaultCloseOperation(3);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(title, BorderLayout.NORTH);
        frame.getContentPane().add(AI, BorderLayout.SOUTH);
        title.setLayout(new FlowLayout());
        panel.setLayout(new GridLayout(8, 8));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //setting up (initialising) the  the GameCell array
        for (int i = 0; i < 64 ; i++) {
            labels[i] = new GameCell(i / 8, i % 8);
        }

        //adding the new GameCells to the panel and setting their borders to the appropriate
        //width and position
        for (int i = 0; i < 64; i++) {
            if(i < 7)
                labels[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
            else if (i == 7)
                labels[i].setBorder(BorderFactory.createMatteBorder(0, 0,0,0, Color.black));
                if(i % 8 == 0)
                labels[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
            else
                labels[i].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.black));
            panel.add(labels[i]);
        }

        frame.pack();
        frame.setVisible(true);
    }

    public JFrame[] getFrames(){
        JFrame[] arr = {player1Frame, player2Frame};
        return arr;
    }
}

