package Carnival;

import java.util.*;
import java.util.concurrent.TimeUnit;
/*
 * This is the game for pennytoss.  It throws three pennys onto the board and returns if the player has gotten 3 types of the same prize
 */
public class PennyToss extends GameBooth{

    private static double cost = 2;
    private static final String smallPrize = "Poster";//initializing constants to the game that will not change
    private static final String largePrize = "Plush Tiger";
    private static final String GameName = "Penny Toss";
    private String [][] GameBoard = new String[4][4];
    private String[] PossiblePrizes = new String[]{"Empty", smallPrize, smallPrize, largePrize, largePrize};//added two sets of large and small prizes to make the probability of winning a prize higher
    private String[] PickedSquares = new String[3];//will contain what squares were chosen
    

    public PennyToss(){
        super(cost, smallPrize,largePrize);
        for (int i = 0; i<4; i++){
            for (int j=0; j<4; j++){
                GameBoard[i][j] = PossiblePrizes[((int)(Math.random()*5))];//initializes the gamboard.  Ensures that the gameboard is randomized for each instance the player plays.  
                //I ensured that the player still has good odds of winning by making the chances for smallprize and largeprize squares occuring higher
            }
        }

    }
    public static String PTGameName(){//made so that other classes can access the gamename
        return (GameName);
    }

    /*
     * This is the main code for the Penny Toss game.  It encompasses all functions apart from setting the gameboard
     */
    public void Play(Player participant) throws InterruptedException{
        participant.changeMoney(cost);//charges the player for the cost of the game
        RollingText.println("\n The cost for playing Penny Toss is " + super.getCost() + ". You have three throws\n");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("Land on three " + smallPrize + " squares to win a " + smallPrize + ", or land on three " + largePrize + " squares to win a " + largePrize + "!\n");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("This is the gameboard: \n");
        for (int i=0; i<4; i++){//this set of nested for loops prints out the game board and its squares
            RollingText.println("\n");
            for (int j=0; j<4; j++){
                RollingText.print(GameBoard[i][j]);
                RollingText.print("    ");
            }
        }
        /*
         * This section is where the player tosses the three pennies onto the board
         */
        RollingText.println("\n");
        TimeUnit.SECONDS.sleep(1);
        String pennyinput = "";
        Scanner TossedPenny = new Scanner(System.in);//this is the intialization of my scanner
        for (int tossing = 1; tossing<=3; tossing++){
            RollingText.print("\nPress y to toss penny number " + tossing + ": ");//telling the participant what penny they are throwing
            pennyinput = TossedPenny.next();
            if (pennyinput.equals("y")||pennyinput.equals("Y")){//this checks to ensure the user has inputted the right value
                RollingText.println("");
                boolean continuetoss = true;
                while (continuetoss){
                    int row = (int)(Math.random()*4);
                    int column = (int)(Math.random()*4);
                    if (!GameBoard[row][column].equals("N/A")){//this is an extra layer of complexity as it ensures that no two pennies land on the same square
                        RollingText.println("\n *Ping* ");
                        TimeUnit.SECONDS.sleep(1);
                        PickedSquares[tossing-1]= GameBoard[row][column];
                        GameBoard[row][column] = "N/A";
                        continuetoss=false;

                    }
                }
            }
            else{//this is a catch all in case the user does not say yes to throwing a penny in
                RollingText.println("\nThat is not a valid input.  \n");
            }
        }
        RollingText.println("\nThe squares you landed on are: ");//this segment is used to tell the player what squares they landed on
        for (int counter = 1; counter<=3; counter++){
            RollingText.println(counter + ") " + PickedSquares[counter-1]);
            TimeUnit.SECONDS.sleep(1);

        }
        /*
         * This last segment is used to differentiate what prize the user won through a set of if and else if statements
         */
        if (PickedSquares[0].equals(smallPrize)&&PickedSquares[0].equals(PickedSquares[1])&&PickedSquares[0].equals(PickedSquares[2])){
            RollingText.println("Congratulations, you have won a " + smallPrize + "!\n");
            participant.addprize(smallPrize);
            super.AddBoothPrizes(GameName, smallPrize);
        }
        else if (PickedSquares[0].equals(largePrize)&&PickedSquares[0].equals(PickedSquares[1])&&PickedSquares[0].equals(PickedSquares[2])){
            RollingText.println("Congratulations, you have won a " + largePrize + "!\n");
            participant.addprize(largePrize);
            super.AddBoothPrizes(GameName, largePrize);
        }
        else{
            RollingText.println("No prize, better luck next time!");
        }


    }
    
}
