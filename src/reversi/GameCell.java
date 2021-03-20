package reversi;

import javax.swing.*;
import java.awt.*;

public class GameCell extends JLabel {
    //Represents the colour of the piece in the cell
    private int color; // -1 = Black, 0 = Empty, 1 = White

    public GameCell(){
        super();
        this.setText("H");
        this.color = 0;
        this.setSize(new Dimension(10,10));
        this.setOpaque(true);
        this.setBackground(Color.GREEN);
    }

    public void setColour(int color){
        this.color = color;
        return;
    }

    public int getColor(){
        return this.color;
    }
}
