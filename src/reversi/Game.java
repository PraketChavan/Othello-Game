package reversi;

import javax.swing.*;

public class Game {
    JFrame[] arr = new JFrame[2];
    public Game(){
        GUI ob = new GUI();
        arr = ob.getFrames();
        new Board(arr[0], arr[1]);
    }
}
