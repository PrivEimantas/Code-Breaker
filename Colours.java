import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Controls each row within board
 */
public class Colours{

    // for first case use as many as example image (4), grid layout of row by col
    private JFrame frame = new JFrame();
    private GridLayout inputScoresLayout;
    private GridLayout scoreLayoutBlock = new GridLayout(2,1);
    private JPanel panel = new JPanel();
    private Picture emptyColour = new Picture("Empty.png");
    private JPanel panel2 = new JPanel();
    private Picture[] inputs; // instead of Jlabel use picture array, then just create new label for each rebuild
    private Picture[] scores;
    public int[] coloursArr;
    private int coloursArrPtr;
    private int Guesses;
    private boolean visited=false;
    private boolean allTrue=true;
    /**
     * Class builds the panel for the colours and controls behaviour of each row within board
     * @param guesses How many guesses the player will have represents how many white circles there are for a row
     */
    public Colours(int guesses){

        this.coloursArrPtr=0; //initializing
        this.coloursArr = new int[guesses];
        this.Guesses = guesses;
        this.inputs = new Picture[guesses];
        this.scores = new Picture[guesses];
        inputScoresLayout = new GridLayout(0,guesses+(guesses+1)/2 );
        scoreLayoutBlock = new GridLayout(2,0);
        frame.setContentPane(panel);
        frame.setSize(86*(guesses+1),86*(guesses + (guesses+1)/2  ));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        panel.setLayout(inputScoresLayout);
        panel2.setLayout(scoreLayoutBlock);

        for(int i=0;i<guesses;i++){ //  Add white circles to input area
            JLabel item = new JLabel(emptyColour);
            panel.add(item);
            this.inputs[i]=emptyColour;// Keep track of what labels are added
            //frame.pack();
            
        }

        for(int i=0;i<guesses/2;i++){ // Add 1 to calculate how many rows needed
            panel2 = new JPanel();
            panel2.setLayout(scoreLayoutBlock);
            panel2.add(new JLabel(emptyColour));
            panel2.add(new JLabel(emptyColour));
            this.scores[i] = emptyColour;
            this.scores[i+1] = emptyColour;
            panel.add(panel2);

        }

        if(guesses%2==1){// Add on final white circle score if number of guesses is odd
            panel2 = new JPanel();
            panel2.setLayout(scoreLayoutBlock);
            panel2.add(new JLabel(emptyColour));
            panel.add(panel2);
            this.scores[this.scores.length-1]=emptyColour;
 
        }
                
    }

    /**
     * Retrieves panel
     * @return Panel from colours to be used within board
     */
    public JPanel getPanel(){
        return(panel);
    }

    /**
     * 
     * @param correctAnswer The correct answer from Board class
     * @param pointer Points to which white circle input within a row
     * @param pic The picture to be updated fetched from Board
     */
    public void updateInputs(int correctAnswer[],int pointer,Picture pic){
       
        
        boolean fullyc = false;
        inputs[pointer] = pic;
        //testing to update first circle to different colour
        panel = new JPanel();
        frame.setContentPane(panel);
        inputScoresLayout = new GridLayout(0,Guesses+(Guesses+1)/2 );
        scoreLayoutBlock = new GridLayout(2,0);
        panel.setLayout(inputScoresLayout);
        panel2.setLayout(scoreLayoutBlock);

        for(int i=0;i<Guesses;i++){
            JLabel item = new JLabel(inputs[i]);
            panel.add(item);
        }

        if(pointer==Guesses-1){ // Rebuild the score since this is final output
            this.visited=true;
            int valuesChecked[] = new int[Guesses];
            int tempArrPtr=0;
            Picture tempArr[] = new Picture[Guesses];
            Picture fullyCorrect = new Picture("Score_0.png");
            Picture partialCorrect = new Picture("Score_1.png");
    
            //test array, 0 for perfect, 1 for partial, -1 for incorrect
            for(int i=0;i<Guesses;i++){ // initialize
                valuesChecked[i]=0;
                tempArr[i]=new Picture("Empty.png");
                
            }
            int allCorrect=0;
            //check for fully correct
            for(int i=0;i<Guesses;i++){

                if(this.coloursArr[i]==correctAnswer[i]){
                    tempArr[tempArrPtr]=fullyCorrect;
                    tempArrPtr= tempArrPtr+1;
                    valuesChecked[i]=1;
                    allCorrect+=1;
                }
                
            }
            if(allCorrect==Guesses){
                fullyc=true;
            }
            //Assuming alr checked if fully correct
            //check for partially correct
            for(int i=0;i<Guesses;i++){
                
                if(valuesChecked[i]==0){
                    //Assuming alr checked if fully correct
                    int countForiInAnswer=0;
                    int count=0;
                  
                    for(int y=0;y<Guesses;y++){
                        if(correctAnswer[y]==this.coloursArr[i] ){ // count how many of that in answer
                            countForiInAnswer+=1;

                        }

                        if(correctAnswer[y]==this.coloursArr[i] && valuesChecked[y]==1){
                            count+=1; // check if already been checked and have this as starting count
                        
                        }
                    }
                   
                    for(int y=i;y>=0;y--){
                        if(this.coloursArr[y]==this.coloursArr[i] && valuesChecked[y]==0 ){
                            count+=1; // for all values that are the same, checked backwards
                        }
                    }
                    
                    if(count>countForiInAnswer){ 
                        continue; //invalid so leave it as an empty circle
                    }

                    else{ // Else is partially correct so add on
                        tempArr[tempArrPtr]=partialCorrect;
                        tempArrPtr+=1;
                    }
                }
            }

 
            for(int i=0;i<Guesses;i++){ // Save in scores
                this.scores[i]=tempArr[i];
            }
        }        

        if(visited==false){ //If not final input
            Picture tempArr[] = new Picture[Guesses];

            for(int i=0;i<Guesses;i++){ // initialize
                tempArr[i]=new Picture("Empty.png");
                this.scores[i]=tempArr[i];
            }
            
        }
        
        for(int i=0;i<Guesses;i=i+2){ // increment by two to avoid  duplicates, building one by one so needs to be done this way
                panel2 = new JPanel();
                panel2.setLayout(scoreLayoutBlock);
                panel2.add(new JLabel(scores[i]));
                panel2.add(new JLabel(scores[i+1]));
                panel.add(panel2);
        }
       
        if(Guesses%2==1){// Add on final white circle score if number of guesses is odd
            panel2 = new JPanel();
            panel2.setLayout(scoreLayoutBlock);
            panel2.add(new JLabel(scores[Guesses-1]));
            panel.add(panel2);
        }
        
        if(fullyc==true){
            new endScreen("You win!");
        }
      
        frame.getContentPane().setBackground(Color.BLACK);
      
    }

   
    /**
     * 
     * @param colourFromBoard Updates colour array so can be used for comparison in building score
     */
    public void updateColourArr(int colourFromBoard){
        this.coloursArr[this.coloursArrPtr]=colourFromBoard;
        this.coloursArrPtr+=1;
    }

}