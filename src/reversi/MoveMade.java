package reversi;

import java.awt.event.*;
import java.util.Scanner;

/**
 * Class to handle the all the move made by the player.
 */

public class MoveMade implements ActionListener {

    private GameState state;
    private GUI.GameCell cell;

    public MoveMade(GameState state){
        this.state = state;
    }

    public boolean isValidMove(int xPos, int yPos, int player){return false;}

    public int findPiece(int xPos, int yPos, int player){
        int captured = 0;
        int orgX = xPos, orgY = yPos;
        boolean flag; //the flag will represents if a opponents piece is found or not
        for (int xoffset = -1; xoffset < 2   ; xoffset++) {
            for (int yoffset = -1; yoffset < 2; yoffset++) {
                if (xoffset == 0 && yoffset == 0)
                    continue;

                flag = false;
                xPos = orgX;
                yPos = orgY;

                while(true) {
                    //adds the offset to the current position
                    xPos += xoffset;
                    yPos += yoffset;

                    if (xPos >= 8 || xPos < 0 || yPos >= 8 || yPos < 0)
                        break;

                    //checks if the new position is not empty and has
                    //the opposite players piece
                    if (state.gameState[xPos][yPos] == 0 || (state.gameState[xPos][yPos] == player && !flag))
                        break;

                    if (state.gameState[xPos][yPos] == player && flag)
                        capturePieces(xPos, yPos, orgX, orgY, player);

                    if (state.gameState[xPos][yPos] != player)
                        flag = true;
                    captured++;
                }
            }
        }
        System.out.println(captured);
        return captured;
    }

    /**
     * Captures all the pieces from x and y co-ordinate till
     * the xOp and yOp co-ordinate
     * @param x x position of the piece to capture from
     * @param y y position of the piece to capture from
     * @param xOp x position of the piece to capture to
     * @param yOp y position of the piece to capture to
     * @param player the player capturing the pieces
     */
    private void capturePieces(int x, int y, int xOp, int yOp, int player){
        if (x - xOp == 0){
            while (y != yOp){
                state.gameState[xOp][yOp] = player;
                yOp -= (yOp-y)/Math.abs(yOp-y);
                updateCell(xOp, yOp, player);
            }
        }
        else if (y - yOp == 0){
            while (x != xOp){
                state.gameState[xOp][yOp] = player;
                xOp -= (xOp-x)/Math.abs(xOp-x);
                updateCell(xOp, yOp, player);

            }

        }
        else {
            while (x != xOp && y != yOp){
                state.gameState[xOp][yOp] = player;
                xOp -= (xOp-x)/Math.abs(xOp-x);
                yOp -= (yOp-y)/Math.abs(yOp-y);
                updateCell(xOp, yOp, player);

            }

        }
    }

    public boolean isMovePossible(int player){
        return false;
    }

    public void updateCell(int x, int y, int player){
        GUI.GameCell cell[] = new GUI.GameCell[2];
        cell = this.cell.getGameCell(x, y);
        cell[0].update(player);
        cell[1].update(player);
    }

    public static void main(String[] args) {
        GameState state = new GameState();
        MoveMade ob = new MoveMade(state);
        ob.findPiece(4, 5, 1);
        Scanner sc = new Scanner(System.in);
        while (true){
            state.print();
            ob.findPiece(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.cell = (GUI.GameCell)e.getSource();
        if (cell.frame == Game.currentPlayer) {
            cell.update(Game.currentPlayer);
            GUI.state.update(cell.getxPos(), cell.getyPos(), Game.currentPlayer);
            this.findPiece(cell.getxPos(), cell.getyPos(), Game.currentPlayer);
            GUI.state.print();
            cell.getGUI().updateFrame(cell.getyPos(), cell.getxPos(), Game.currentPlayer);
            Game.currentPlayer = Game.currentPlayer == 1 ? -1 : 1;
        }
    }
}
