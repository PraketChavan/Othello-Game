package reversi;

import javax.swing.*;

public class Game{
    JFrame[] arr = new JFrame[2];
    static int currentPlayer;

    public Game(){
        currentPlayer = 1;
        GUI ob = new GUI();
        arr = ob.getFrames();
        new Board(arr[0], arr[1]);
    }

    public void changePlayer(int player){
       currentPlayer = player==1 ? -1 : 1;
    }
}
