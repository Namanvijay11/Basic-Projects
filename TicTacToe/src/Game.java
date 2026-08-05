import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Game extends JFrame implements ActionListener
{

    private JLabel heading , status;
    private JButton restart;
    private JButton[] buttons = new JButton[9];
    private JPanel buttonContainer;
    private Font fontHeading  = new Font("",Font.BOLD,20);


//    for game logic

    boolean isWinner = false;
    int currentPlayer = 0;
    int gameState[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int winingConditions[][] = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };


//    constructor
    Game(String title){

        setTitle(title);
        createComponent();
        handleEvent();
        startGame();
//        pack();
        setSize(500,400);
        setLocationRelativeTo(null); //to display screen on center
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void startGame() {
        status.setText("Player 0 Chance");
        for(int i=0;i<gameState.length;i++){
            gameState[i] = -1;
        }
        for(JButton button : buttons){
            button.setText("");
            button.setIcon(null);
        }
        currentPlayer =0;
        isWinner = false;
    }

    private void createComponent() {

        heading = new JLabel("Tic Tac Toe");
        heading.setFont(fontHeading);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        for(int i=0;i<buttons.length;i++){
            buttons[i]  = new JButton();
            buttons[i].setPreferredSize(new Dimension(100,60));
            buttons[i].setFont(fontHeading);
            buttons[i].setName(i+"");
            buttons[i].addActionListener(this);
        }

        status = new JLabel("Status");
        status.setHorizontalAlignment(JLabel.CENTER);
        status.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        restart = new JButton("Restart Game");

        JPanel statusPanel = new JPanel();
        statusPanel.add(status);
        statusPanel.add(restart);




//        creating button with panel
        buttonContainer = new JPanel();
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        buttonContainer.setLayout(new GridLayout(3,3));
        for(int i=0;i<buttons.length;i++){
            buttonContainer.add(buttons[i]);
        }


//        adding component to frame
        this.add(heading,BorderLayout.NORTH);
        this.add(statusPanel,BorderLayout.SOUTH);
        this.add(buttonContainer);


    }

    private void handleEvent(){

        restart.addActionListener((event)->{
            startGame();
        });
    }


//    button Click
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton sourceButton = (JButton)e.getSource();
        int currentPosition = Integer.parseInt(sourceButton.getName());

//        check for position
        if(gameState[currentPosition] == -1){

//            current position is vacant

            JButton currentButton = buttons[currentPosition];
            setCurrentPositionValue(currentPosition);



            int winner = checkForWinner();
            if(winner>=0){
                JOptionPane.showMessageDialog(this, winner+" has won the game");
                int x = JOptionPane.showConfirmDialog(this, "Restart the game");
                if(x==0){
                    startGame();
                }
            }
            boolean result = checkForDraw();
//            System.out.println(result);
            if(result) {
                JOptionPane.showMessageDialog(this, "Match Draw ! ");
                int x = JOptionPane.showConfirmDialog(this, "Restart the game");
                if(x==0){
                    startGame();
                }

                return;
            }
        }

        else {
            JOptionPane.showMessageDialog(this,"Current Position is not empty");
        }


    }

//    Match draw
    private boolean checkForDraw() {

        boolean isAnyFieldLeft = false;
        for(int state: gameState){
            if(state == -1){
                isAnyFieldLeft = true;
            }
        }
        if(!isAnyFieldLeft && !isWinner){
            return true;
        }
        else {
            return false;
        }
    }

    private int checkForWinner() {
        int winner = -1;

        for (int winnerArray[]:winingConditions){
            if((gameState[winnerArray[0]] == gameState[winnerArray[1]] && gameState[winnerArray[1]] == gameState[winnerArray[2]] ) && gameState[winnerArray[0]]!=-1){
                isWinner = true;
                winner = gameState[winnerArray[0]];
                break;
            }
        }

        return winner;
    }

    private void setCurrentPositionValue(int currentPosition) {

        JButton currentButton = buttons[currentPosition];
        if(currentPlayer==0){
//            currentButton.setText("0");
            currentButton.setIcon(new ImageIcon("src\\Images\\zero.png"));
            gameState[currentPosition] = 0;
            status.setText("Player 1 Chance");
            currentPlayer = 1;
        }
        else{
//            currentButton.setText("1");
            currentButton.setIcon(new ImageIcon("src\\Images\\one.png"));
            gameState[currentPosition] = 1;
            status.setText("Player 0 Chance");
            currentPlayer = 0;
        }
//        System.out.println(Arrays.toString(gameState));
    }
}
