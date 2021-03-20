package reversi;

import javax.swing.*;

public class Game {
    GameCell[] arr = new GameCell[64];

    public Game(){
        for(int i = 0; i < 64; i++) {
            arr[i] = new GameCell();
            arr[i].setVisible(true);
        }
    }


    //checks if a given space is valid or not for a move
    public boolean is_valid(int x, int y){
        return false;
    }


}
