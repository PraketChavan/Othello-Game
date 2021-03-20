package reversi;

import javax.swing.*;
import java.awt.*;

public class Board {
    JFrame board1, board2;
    JPanel player1, player2;
    Game game;


    /**
     * Parameterised constructor that creates the Board and associates
     * a Game object to it
     * @param game Game object that stores the data of the current game
     */
    public Board(Game game){
        board1 = new JFrame();
        board2 = new JFrame();
        player1 = new JPanel();
        player2 = new JPanel();
        this.game = game;
        this.initGui(board1, player1, "Hello");
        this.initGui(board2, player2, "World");
        board1.setVisible(true);
        board2.setVisible(true);
    }

    /**
     * Helper function to initialise the settings of the frame
     * and the panel to meet the program requirements.
     */
    private void initGui(JFrame board, JPanel panel, String text){
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.getContentPane().setLayout(new BorderLayout());
        board.getContentPane().add(new JLabel(text), BorderLayout.NORTH);
        board.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setBackground(Color.BLUE);
        panel.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 64; i++)
            panel.add(game.arr[i]);
        board.pack();
        panel.validate();
        panel.setVisible(true);
    }

    public void addCells(GameCell[] cell, JPanel panel){

        return;
    }
}
