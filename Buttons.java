import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * Class represents the buttons to be pressed by user during board and control their behaviour
 */
public class Buttons{

    private JFrame frame = new JFrame();
    private GridLayout layout= new GridLayout(0,7);
    private JPanel panel = new JPanel();
    private JButton[] buttons = new JButton[7]; // 7 buttons so is constant
                                
    /**
     * Creates the panel for the buttons on board
     */
    public Buttons(){
        for(int i=0;i<7;i++){ // Initialize the array
            this.buttons[i]=new JButton(new Picture("Colour_"+i+".png"));
        }
        frame.setContentPane(panel);
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);
        panel.setLayout(layout);

        for(int i=0;i<7;i++){
            panel.add(buttons[i]);
        }
        
    }
    
    /**
     * 
     * @return returns panel of buttons
     */
    public JPanel getPanel(){
        return(panel);
    }
    
    /**
     * @return returns buttons
     */
    public JButton[] getButtons(){
        return(buttons);
    }
}