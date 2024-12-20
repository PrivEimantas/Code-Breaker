import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.*;


/***
 * Class MainMenu used when first starting the game, allowing user to select difficulty
 */
public class MainMenu implements ActionListener{

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton hard,medium,easy;
    private GridLayout layout = new GridLayout(0,3);

    /***
     * Constructor for main menu, sets up the initial start screen allowing user to choose 
     * The difficulty of the game
     */
    public MainMenu(){
        frame.setSize(250,250);
        //set colours class content with frame of board
        //frame.setVisible(true);
        frame.setContentPane(panel);
        frame.setTitle("Start screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        panel.setLayout(layout);
        hard = new JButton("Hard");
        medium = new JButton("Mid");
        easy = new JButton("Easy");
        hard.addActionListener(this);
        medium.addActionListener(this);
        easy.addActionListener(this);
        panel.add(hard);
        panel.add(medium);
        panel.add(easy);
        frame.setVisible(true);
    }

    /**
     * Class which listens for which button pressed and sets up board based on which difficulty is selected
     */
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==easy){
            frame.setVisible(false);
            new Board(4,4);
        }
        
        if(e.getSource()==medium){
            frame.setVisible(false);
            new Board(6,6);
        }

        if(e.getSource()==hard){
            frame.setVisible(false);
            new Board(8,8);
        }

    }
    
}