package Carnival;
import java.util.*;

/*
 * This is the class for game 3, roulette spinner.  
 * The player has the option of gambling either 5 or 10 dollars to win different levels of prizes
 * The game is exactly like the game of roulette in the sense that a wheel spins and you get prizes based on matching the colour or number
 */
public class RouletteSpinner extends GameBooth{


    private static double cost = 0;//this is not final as the cost changes depending on how much is gambled
    private static final String $5Prize = "Small Naruto Statue";//these are final variables as they won't change
    private static final String $10Prize = "Large Charizard Statue";
    private static final String GameName = "Roulette Spinner";
    private Dictionary<Integer, String> RouletteWheel = new Hashtable<Integer,String>(37);//use hastables here as they are perfect for assigning the numbers on a roulette wheel a colour
    private String[] colourslist = new String[]{"red", "black", "green"};
   


    public RouletteSpinner(double UserPayment){
        super (cost, $5Prize, $10Prize);
        cost = UserPayment;
        for (int i=0; i<36; i=i+2){//This set of nested for loop is to set up the roulette wheel for the red and black colours
            for (int j=0; j<2; j++){
                RouletteWheel.put(i+j, colourslist[j]);
            }
        }
        RouletteWheel.put(36, colourslist[2]);//this is to set up the one green square
    }
    
    public static String RSGameName(){//this is a global method for getting the games' name
        return (GameName);
    }

    public void play (Player participant){//this is the main code for the game
        int BallOnSquare = (int)(Math.random()*37);//this determines what square the ball will land on
        boolean ContinueRouletteChoice = true;

        while (ContinueRouletteChoice){            
            Scanner RouletteChoices = new Scanner(System.in);//this checks what number the user wants to gamble on
            RollingText.print("\nEven numbers from 0 to 34 inclusive are Red.  \n\nOdd numbers from 1 to 35 inclusive are black. \n\nThe 36 square is green.\n\nWhat number would you like to gamble on?  ");
            int GambleNumber = RouletteChoices.nextInt();

            if (GambleNumber>=0 && GambleNumber<=36){//this ensures that the number chosen is on the table
                Scanner Confirmation = new Scanner(System.in);
                RollingText.print("The colour of square " + GambleNumber + " is " + RouletteWheel.get(GambleNumber) + ".  Is that alright? ");//this is used to check if the user is sure they chose the right number and colour
                String ConfirmationOfChoice = Confirmation.nextLine();
                RollingText.println("");
                if (ConfirmationOfChoice.equals("y")||ConfirmationOfChoice.equals("yes")||ConfirmationOfChoice.equals("Y")||ConfirmationOfChoice.equals("Yes")){//checks for all variations of yes
                    //the rest of the code in this nested if statement is just checking what matches between the chosen square and the winning square
                    participant.changeMoney(cost);
                    RollingText.println("\nStarting the game...");
                    RollingText.println("\n...\n");
                    RollingText.println("The winning square is square number " + BallOnSquare + ".  It is the colour "+ RouletteWheel.get(BallOnSquare));
                    if (GambleNumber == BallOnSquare){//case for if they get the exact colour and number
                        RollingText.println("You have bet on the exact winning number and colour!");
                        RollingText.println("For this amazing achievement, your reward has been upgraded to the next tier!");
                        if (cost==5){//grandprize for gamblng 5 dollars
                            RollingText.println("You have won the $10 prize which is a " + $10Prize + "!");
                            participant.addprize($10Prize);
                            super.AddBoothPrizes(GameName, $10Prize);
                            ContinueRouletteChoice=false;
                        }
                        else if (cost==10){//grand prize for gambling 10 dollars
                            RollingText.println("You have won both the " + $5Prize + " and the "+ $10Prize +"!");
                            participant.addprize($5Prize);
                            participant.addprize($10Prize);
                            super.AddBoothPrizes(GameName, $10Prize);
                            super.AddBoothPrizes(GameName, $5Prize);
                            ContinueRouletteChoice=false;
                        }
                    }
                    else if (RouletteWheel.get(GambleNumber).equals(RouletteWheel.get(BallOnSquare))){//what happens if they only get the right colour
                        RollingText.println("\nCongratulations, you have guessed the correct colour!\n");
                        if (cost==5){//prize for guessing the right colour at 5 dollar bet
                            RollingText.println("You have won a "+ $5Prize + "!\n");
                            participant.addprize($5Prize);
                            super.AddBoothPrizes(GameName, $5Prize);
                            ContinueRouletteChoice=false;
                        }
                        else{//prize for guessing the right colour at 10 dollar bet
                            RollingText.println("You have won a "+$10Prize + "!\n" );
                            participant.addprize($10Prize);
                            super.AddBoothPrizes(GameName, $10Prize);
                            ContinueRouletteChoice=false;
                        }
                    }
                    else{//catch statement for if the player did not guess anything right
                        RollingText.println("Unfortunately you did not win.  \nGood luck next time!");
                        ContinueRouletteChoice=false;
                    }
                }
                else{//catch statement for if the player wants to change their guess
                    RollingText.println("Okay, please decide what square you would like to bet on. \n");
                }
            }
            else{//catch statement for if the player inputs a invalid number/number not on the board
                RollingText.println("Please input a valid number from 0 to 36");
            }
          
        }

    }
}
