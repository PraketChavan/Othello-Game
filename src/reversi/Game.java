package reversi;

import javax.swing.*;

public class Game{

    JFrame[] arr = new JFrame[2];
    static int currentPlayer;
    GUI ob;
    boolean restart;
    GameState state;

    public Game() {
        restart = true;

        while(true) {
            if (restart) {
                initGame();
                restart = false;
            }
            if (ob.state.skipMove(Game.currentPlayer) && ob.state.skipMove(Game.currentPlayer == 1 ? -1 : 1)) {
                JOptionPane optionPane = new JOptionPane();
                optionPane.showMessageDialog(arr[0], state.countPieces());
                restart = true;
                arr[0].dispose();
                arr[1].dispose();
            } else if (ob.state.skipMove(Game.currentPlayer))
                Game.currentPlayer = Game.currentPlayer == 1 ? -1 : 1;
        }
    }

    public void initGame() {
        this.currentPlayer = 1;
        ob = new GUI();
        arr = ob.getFrames();
       // new Board(arr[0], arr[1]);
        state = ob.getGameState();
        for (JFrame frame : arr) {
            System.out.println(frame.toString());
        }
    }
}
