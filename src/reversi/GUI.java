package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class deals with all the GUI interface
 * for the game.
 */
public class GUI {
    JFrame player1Frame, player2Frame;
    JPanel player1Panel, player2Panel;
    JLabel  title1, title2;
    JButton greedyAI1, greedyAI2;
    GameCell[][] player1Label = new GameCell[8][8];
    GameCell[][] player2Label = new GameCell[8][8];
    GameState state = new GameState();

    //Stores the only GUI object;
    GUI currentGUI;

    public GUI() {
        player1Frame = new JFrame("Player 1");
        player2Frame = new JFrame("Player 2");
        title1 = new JLabel();
        title2 = new JLabel();
        currentGUI = this;
        initialise(player1Frame, player1Panel, title1, greedyAI1, player1Label, 1);
        initialise(player2Frame, player2Panel, title2, greedyAI2, player2Label, -1);

    }


    /**
     * Method to initialise the Board UI
     *
     * @param frame  the frame that will be initialised
     * @param panel  the panel that will be added to the frame and will contain
     *               Gamecells
     * @param title  the panel which contains the title/player info
     * @param AI     the button which will allow the user to let the AI play the move
     * @param labels the array that will hold the references to the GameCell objects
     */
    private void initialise(JFrame frame, JPanel panel, JLabel title, JButton AI, GameCell[][] labels, int player) {
        panel = new JPanel();
        AI = new JButton("Greedy AI ("+(player==1?"White":"Black") +")" );
        AI.addActionListener(new AiButtonPressed(currentGUI));

        //setting up the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(title, BorderLayout.NORTH);
        frame.getContentPane().add(AI, BorderLayout.SOUTH);
        frame.setLocation(player==1 ? 435: 765, 200);

        //setting up the panels
        panel.setLayout(new GridLayout(8, 8));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //setting up the title
        if (player == 1)
            title.setText("White Player - click place to put piece");
        else
            title.setText("Black Player - not your turn yet");



        //setting up (initialising) the  the GameCell array
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (player == 1)
                    labels[i][j] = new GameCell(j, i, player);
                else
                    labels[i][j] = new GameCell(7 - j, 7 - i, player);
            }
        }

        //adding the new GameCells to the panel and setting their borders to the appropriate
        //width and position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 && j != 7)
                    labels[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
                else if (j == 7 && i == 0)
                    labels[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
                if (j == 0)
                    labels[i][j].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
                else
                    labels[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.black));
                panel.add(labels[i][j]);
            }
        }

        frame.pack();
        frame.setVisible(true);
    }

    //returns a array of the main frames
    public JFrame[] getFrames() {
        return new JFrame[]{player1Frame, player2Frame};
    }

    public GameState getGameState() {return this.state;}

    /**
     * Updates both of the main frames and the (x, y) co-ordinate
     * labels to reflect the move made.
     *
     * @param yPos      y position of the move made
     * @param xPos      x position of the move made
     * @param newStatus the new status that the the label will need
     *                  to reflect
     */
    public void updateFrame(int yPos, int xPos, int newStatus) {
        if (newStatus == 1) {
            title1.setText("White Player - not your turn yet");
            title2.setText("Black Player - click place to put piece");
            player1Label[xPos][yPos].update(newStatus);
            player2Label[7 - xPos][7 - yPos].update(newStatus);
        } else {
            title1.setText("White Player - click place to put piece");
            title2.setText("Black Player - not your turn yet");
            player2Label[7 - xPos][7 - yPos].update(newStatus);
            player1Label[xPos][yPos].update(newStatus);
        }

        player1Frame.repaint();
        player2Frame.repaint();

    }



    /**
     * Inner class for the cells of the game
     */
    public class GameCell extends JButton implements ActionListener {
        /**
         * The variable hold the information about the cell;
         * which player posses the cell if any.
         * <ul>
         * <li>1 - White player</li>
         * <li>0 - Empty Cell</li>
         * <li>-1 - Black Player</li>
         * </ul>
         */
        int status;

        /**
         * This member variable holds the player number which the cell belongs to
         */
        int frame;
        int xPos, yPos;

        /**
         * The number of pieces that will be captured in this cell
         */
        int score;

        /**
         * The parameterised constructor which initialises the GameCell
         *
         * @param yPos y position of the cell
         * @param xPos x position of the cell
         */
        public GameCell(int yPos, int xPos, int player) {
            this.xPos = xPos;
            this.yPos = yPos;
            this.frame = player;
            this.setPreferredSize(new Dimension(40, 40));
            this.setBackground(Color.green);
            this.setOpaque(true);
            this.status = state.gameState[xPos][yPos];
            this.addActionListener(new MoveMade(state));
        }

        /**
         * returns the GUI object that the cell is associated to
         * @return the current GUI object
         */
        public GUI getGUI() {
            return currentGUI;
        }

        public int getxPos(){
            return this.xPos;
        }

        public int getyPos(){
            return this.yPos;
        }

        /**
         * Gets the GameCell with the (x, y) co-ordinate
         * @param xPos x position of the GameCell to get
         * @param yPos y position of the GameCell to get
         * @return the GameCell if found else null
         */
        public GameCell[] getGameCell(int xPos, int yPos) {
            GameCell[] cell = new GameCell[2];
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++) {
                    if (player1Label[i][j].getxPos() == xPos && player1Label[i][j].getyPos() == yPos)
                        cell[0] = player1Label[i][j];

                    if (player2Label[i][j].getxPos() == xPos && player2Label[i][j].getyPos() == yPos)
                        cell[1] = player2Label[i][j];
                }
            }
            return cell;
        }



        /**
         * Updates this when a move is made to reflect the same
         *
         * @param newStatus the new status (the player that played the turn) to reflect the move made
         */
        public void update(int newStatus) {
            if (status != newStatus) {
                this.status = newStatus;
                this.paintComponent(getGraphics());

            }
            this.score = state.gameCaptureState[xPos][yPos][frame==1?1:0];
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (Game.currentPlayer == -1) {
                title1.setText("White Player - not your turn yet");
                title2.setText("Black Player - click place to put piece");
            } else {
                title1.setText("White Player - click place to put piece");
                title2.setText("Black Player - not your turn yet");
            }
            this.score = state.gameCaptureState[xPos][yPos][frame==1?1:0];
            if (this.status != 0) {
                int x = getWidth() / 2;
                int y = getHeight() / 2;
                int r = getWidth() - 15;
                if (this.status == -1) {
                    g.setColor(Color.WHITE);
                    g.fillOval(x - (r / 2), y - (r / 2), r, r);
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillOval(x - (r / 2), y - (r / 2), r, r);
                    g.setColor(Color.WHITE);
                }
                g.fillOval(x - ((r - 2) / 2), y - ((r - 2) / 2), r - 2, r - 2);
            }
            //this.setText("" + score);
        }

        public void updateAllCells(){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    player1Label[i][j].update(state.gameState[i][j]);
                    player2Label[7 - i][7 - j].update(state.gameState[i][j]);
                }
            }
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (frame == Game.currentPlayer) {
                this.update(Game.currentPlayer);
                state.update(xPos, yPos, Game.currentPlayer);
                //state.print();
                updateFrame(yPos, xPos, Game.currentPlayer);
                Game.currentPlayer = Game.currentPlayer == 1 ? -1 : 1;
            }
        }
    }
}

