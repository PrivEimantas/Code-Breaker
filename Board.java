import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.*;


/**
 * Board class for the entire game board
 */
public class Board implements ActionListener{
    
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private GridLayout layout = new GridLayout(0,1);
    private Colours colour;
    private Buttons buttons = new Buttons();
    private JPanel panelMain = new JPanel();
    private int rowPointer=0;
    private int columnPointer=0;
    private int Guesses;
    private int correctAnswer[];
    private int lives;

    public Colours row[]; //testing with size of 4

    /**
     * @param guesses The amount of guesses available for each row
     * @param lives The amount of rows which is the amount of lives
     */
    //first recreate board to be same as image
    public Board(int guesses,int lives){

        this.lives = lives;
        this.Guesses = guesses;
        this.row = new Colours[lives];
        this.correctAnswer = new int[guesses];
        frame.setSize(86*(guesses+1),86*(guesses + (guesses+1)/2  ));
        //set colours class content with frame of board
        frame.setContentPane(panel);
        frame.setTitle("Code Breaker!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        panel.setLayout(layout);
        panelMain.setLayout(layout);

        for(int i=0;i<guesses;i++){
            Random rand = new Random();
            int n= rand.nextInt(7);
            this.correctAnswer[i]=n;
        }
        
        for(int i=0;i<lives;i++){ // Add each colour on board  
            colour = new Colours(guesses);
            panel.add(colour.getPanel() );
            this.row[i]=colour;// treat each row as an object
        }
       
        panel.add(buttons.getPanel() );
        JButton[] b = buttons.getButtons();
        for(int i=0;i<7;i++){ // add functionality for buttons to have events when presed                         
            b[i].addActionListener(this);
        }
        frame.setVisible(true);
        // from colours object, add content from it to the layout here
    }

    /**
     * On pressing a button, will add in that coloured circle to the board
     * 
     */
    public void actionPerformed(ActionEvent e){
        JButton[] button = buttons.getButtons();
        
        for(int i=0;i<7;i++){
            if(e.getSource()==button[i]){
               
                if(columnPointer==Guesses){ 
                    columnPointer=0;
                    rowPointer+=1;
                }
                if(rowPointer<lives && columnPointer<Guesses){ // check if valid
                    row[rowPointer].updateColourArr(i);
                    row[rowPointer].updateInputs(correctAnswer,columnPointer,new Picture("Colour_"+i+".png"));
                    updateBoard();
                    columnPointer= columnPointer + 1;
                }
                if(rowPointer==lives){
                    frame.setVisible(false);
                    new endScreen("game over");
                }
            }
        }
    }
    /**
     * 
     * @return correct answer
     */
    public int[] fetchAnswer(){
        return(this.correctAnswer);
    }

    /**
     * updates board with new values
     */
    public void updateBoard(){
        // row is each row on board
        panel = new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(layout);
        panelMain.setLayout(layout);

        for(int i=0;i<this.lives;i++){
            panel.add(row[i].getPanel() );
        }

        panel.add(buttons.getPanel());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);

    }
}