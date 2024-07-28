package Carnival;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BalloonPop extends GameBooth{//inherits the gamebooth class
    //lines 6 to 9 are setting up the key properties of the game that will not change; that is why they are declared as final
    private static final double cost = 6.0;
    private static final String SmallPrize = "Small Monkey statue";
    private static final String LargePrize = "Large Pikachu Plush";
    private static final String GameName = "Balloon Pop";
    private String[][] BalloonsColours = new String[4][4];//records colours of balloons at specific places
    private int[][] BalloonsTickets = new int[4][4];//records value of the coloured balloons
    private String[] ColoursList= new String[]{"Purple", "Blue", "Red", "Yellow", "Orange"};//a pool of colours so that I can randomly assign each balloon a different colour each round
    private int[] TicketsListasArray = new int[]{0,0,5,5,4,4,4,3,3,3,2,2,2,1,1,1};//the pool of tickets, ensures that each game a random ticket value is assigned
    private ArrayList<Integer> TicketsArrayList = new ArrayList<>(16);//holds the mixed up tickets from ticketslistasarray as an arraylist
    private int TicketTotal = 0;//records the value of all the tickets chosen in each game session


    public BalloonPop() {
        super(cost, SmallPrize, LargePrize);//inheritance for Balloonpop as a gamebooth
        for (int i=0; i<TicketsListasArray.length; i++){//randomly distributes the tickets to the different spots
            TicketsArrayList.add(i, TicketsListasArray[i]);
        }

    }

    public static String BPGameName(){//global method to get the game name
        return (GameName);
    }
    public static double GetCost(){//global method to get cost of game
        return cost;
    }
    public void DisplayBalloonWall(String[][] WallofBalloons){
        RollingText.println("\n-------------");//This displays the balloon wall which contains only the beginning letter of each balloons colour
        for (int r=0; r<4; r++){
            RollingText.print("|");
            for (int c=0; c<4; c++){
                RollingText.print((WallofBalloons[r][c]).substring(0, 1)+"|");

            }
            RollingText.println("");
        }
        RollingText.println("-------------");

    }
    public void Play(Player Participant) throws InterruptedException{//the throws is used for the time spacing between each print line
        int ticketsusedcounter = 16;//counts how many tickets are used when assigning a ticket to each position in the array
        //next 12 lines are just declaring the games' rules
        RollingText.println("\nWelcome to Balloon Pop.  \n\nThere are 16 balloons on the wall, some of which contain tickets of varying values from 1 to 5.");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("\nYou have the opportunity to pop three balloons.\n ");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("\nIf you get a combined total of 5 or more, you win the small prize, which is a " + SmallPrize);
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("\nIf you gain a combined score of 8 or more, you win the large prize, which is a "+ LargePrize);
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("\nFinally, if you gain a combined score of 11 or more, you win both the small and large prizes! ");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("\nThe distribution of points changes each time you play, so don't think that you can game the system...\n");
        TimeUnit.SECONDS.sleep(1);
        for (int row=0; row<4; row++){//this purpose of this nested for loop is to assign a random ticket from the ticket pool to the balloon per se.  As well, it removes the used ticket from the ticket pool so that each game has exactly the same amount of points available
            for (int column=0; column<4; column++){
                BalloonsColours[row][column]=ColoursList[(int)(Math.random()*5)];
                int usedticketposition = (int)(Math.random()*ticketsusedcounter);
                int usedticket = TicketsArrayList.get(usedticketposition);
                BalloonsTickets[row][column]=usedticket;
                ticketsusedcounter-=1;
                TicketsArrayList.remove(usedticketposition);
            }
        }
        int numberoftries = 3;//sets how many tries player has left
        int ChosenRow = -1;
        int ChosenColumn = -1;

        while (numberoftries>0){//this runs the general instructions of the game
            RollingText.println("This is the Gameboard.\n");
            DisplayBalloonWall(BalloonsColours);
            TimeUnit.SECONDS.sleep(1);
            RollingText.println("");
            RollingText.println("You have " + numberoftries + " balloon pops left.  ");
            TimeUnit.SECONDS.sleep(1);
            RollingText.println("The Colours of the Balloons are as follows: \nB = blue\nY = Yellow\nR = Red\nO = Orange\nP = Purple\nN = Not available");
            TimeUnit.SECONDS.sleep(1);
            RollingText.println("You will now enter the column number and row you would like to pop. ");
            RollingText.println("An example input is: 4\nThis would pop a balloon in row 4. ");
            Scanner BalloonChoice = new Scanner(System.in);
            while (true){//this while loop is to ensure that the balloon chosen is not already popped
                while (true){//this while loop is to ensure that the row chosen is valid (i.e. not greater than 4 and not less than 1)
                    RollingText.print("Enter the ROW (horizontal) your chosen balloon is in: ");
                    ChosenRow = BalloonChoice.nextInt();
                    if (ChosenRow>0 && ChosenRow<5){
                        ChosenRow-=1;
                        break;
                    }
                    else{
                        RollingText.println("That is not an option.  Please try again. ");
                        TimeUnit.SECONDS.sleep(1);
                    }
                }
                RollingText.println("");
                while (true){//this while loop is to ensure that the column chosen is valid (i.e. not greater than 4 and not less than 1)
                    RollingText.print("Enter the COLUMN(Vertical) your chosen balloon is in: ");
                    ChosenColumn = BalloonChoice.nextInt();
                    if (ChosenColumn>0 && ChosenColumn<5){
                        ChosenColumn-=1;
                        break;
                    }
                    else{
                        RollingText.println("That is not an option.  Please try again. ");
                        TimeUnit.SECONDS.sleep(1);
                    }    
                }
                if (BalloonsColours[ChosenRow][ChosenColumn].equals("N/A")){//checks to make sure that the chosen balloon is not already popped
                    TimeUnit.SECONDS.sleep(1);
                    RollingText.println("That Balloon has already been popped.  Please Reselect your choices.\n");
                    TimeUnit.SECONDS.sleep(1);
                }
                else{
                    break;
                }
            }
            RollingText.println("");
            Scanner Confirmation = new Scanner(System.in);
            RollingText.print("Would you like to pop the " + BalloonsColours[ChosenRow][ChosenColumn] + " Balloon on row " + (ChosenRow+1) + " in column " + (ChosenColumn+1) + "? ");//confirms the users choice of balloon
            String FinalConfirmation = Confirmation.nextLine();
            if ((FinalConfirmation.equals("y")||FinalConfirmation.equals("Y")||FinalConfirmation.equals("yes"))||FinalConfirmation.equals("Yes")&&!(BalloonsColours[ChosenRow][ChosenColumn].equals("N/A"))){
                //this runs the game if the user inputs any variation of the word yes
                RollingText.println("\nInside the " + BalloonsColours[ChosenRow][ChosenColumn] + " Balloon was a " + BalloonsTickets[ChosenRow][ChosenColumn] + " point ticket. \n");
                numberoftries-=1;
                TimeUnit.SECONDS.sleep(1);
                TicketTotal+=BalloonsTickets[ChosenRow][ChosenColumn];//adds the ticket amount won to the players total for the instance of playing
                RollingText.println("You have "+ numberoftries + " balloons left to pop. \n");
                TimeUnit.SECONDS.sleep(1);
                BalloonsColours[ChosenRow][ChosenColumn] = "N/A";//changes the gameboard so that the balloon is now unavailable on the display menu
            }
            else{
                RollingText.println("Please reinput your preffered balloon selection then. ");//catches any inputs that are not yes and re routes them to picking the balloon they want to pop
            }  
        }
        //these remaining if and else if statements are used to assign prizes based on the players total ticket amount
        if (TicketTotal>=11){
            RollingText.println("You have won the grand prize of both a " + SmallPrize + " and a "+ LargePrize + "!");
            super.AddBoothPrizes(GameName, SmallPrize);
            super.AddBoothPrizes(GameName, LargePrize);
            Participant.addprize(SmallPrize);
            Participant.addprize(SmallPrize);
            
        }
        else if (TicketTotal>=8){
            RollingText.println("You have won a " + LargePrize + "!");
            super.AddBoothPrizes(GameName, LargePrize);
            Participant.addprize(LargePrize);
        }
        else if (TicketTotal>=5){
            RollingText.println("You have won a "+ SmallPrize + "!");
            super.AddBoothPrizes(GameName, SmallPrize);
            Participant.addprize(SmallPrize);
        }
        else{
            RollingText.println("Unfortunately you did not win.  Better luck next time!");
        }
    }

}