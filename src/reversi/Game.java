package reversi;

import javax.swing.*;

public class Game{

    JFrame[] arr = new JFrame[2];
    static int currentPlayer;
    GUI guiObject;
    boolean restart;

    public Game() {
        restart = true;

        while(true) {
            if (restart) {
                initGame();
                restart = false;
            }
            if (guiObject.state.skipMove(Game.currentPlayer) && guiObject.state.skipMove(Game.currentPlayer == 1 ? -1 : 1)) {
                JOptionPane optionPane = new JOptionPane();
                optionPane.showMessageDialog(arr[0], guiObject.getGameState().countPieces());
                restart = true;
                arr[0].dispose();
                arr[1].dispose();
            } else if (guiObject.state.skipMove(Game.currentPlayer))
                Game.currentPlayer = Game.currentPlayer == 1 ? -1 : 1;
        }
    }

    public void initGame() {
        this.currentPlayer = 1;
        guiObject = new GUI();
        arr = guiObject.getFrames();
        for (JFrame frame : arr) {
            System.out.println(frame.toString());
        }
    }
}
