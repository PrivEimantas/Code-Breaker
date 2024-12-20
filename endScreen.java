import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.*;

public class endScreen{
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private GridLayout layout = new GridLayout(0,1);

    /***
     * Constructor for main menu, sets up the initial start screen allowing user to choose 
     * The difficulty of the game
     */
    public endScreen(String end){

        frame.setBackground(Color.WHITE);
        frame.setSize(250,250);
        //set colours class content with frame of board
        frame.setContentPane(panel);
        frame.setTitle("End screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        panel.setLayout(layout);
        JLabel label = new JLabel(end);
        panel.add(label);
        frame.setVisible(true);

    }
}