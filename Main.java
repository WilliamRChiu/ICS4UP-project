package Carnival;
import java.util.*;
import java.util.concurrent.TimeUnit;
/*
 * This is the main.  It is where all the different classes come together and where the program runs from.
 */

public class Main {
  public static void main(String[] args) throws InterruptedException {
    boolean continueselection = true;//used to ensure that selection loop kepps repeating until user wants to quit
    boolean continuemenu = true;//used to ensure the menu keeps looping until the user inputs a positive value
    double masterbalance = 0;//variable for players budget

    while (continuemenu){
        RollingText.println("Welcome to the Carnival!");
        TimeUnit.SECONDS.sleep(1);
        Scanner moneyinput = new Scanner(System.in);
        RollingText.print("Enter your starting balance: ");
        double balance = moneyinput.nextDouble();
        if (balance>0){
            TimeUnit.SECONDS.sleep(1);
            RollingText.println("...");
            TimeUnit.SECONDS.sleep(2);
            RollingText.println("Your balance has been updated.");
            TimeUnit.MILLISECONDS.sleep(500);
            RollingText.println("May luck be with you.");
            RollingText.println("---------------------------------------");
            TimeUnit.MILLISECONDS.sleep(1500);
            continuemenu = false;
            masterbalance = balance;
        }
        else{
            RollingText.println("Please input a valid amount! ");
            RollingText.println("---------------------------------------");
            TimeUnit.SECONDS.sleep(1);
        }
    }
    Player participant = new Player(masterbalance);//initializes player


    while (continueselection){//this is the code for the enter screen.  It takes in the action choice and loops if an invalid is inputted
        RollingText.println("");
        GameBooth.EnterScreen();
        TimeUnit.SECONDS.sleep(1);
        Scanner actionchoice = new Scanner(System.in);
        RollingText.print("Enter your choice: ");
        String chosenaction = actionchoice.nextLine();
        RollingText.println("\n");
        TimeUnit.SECONDS.sleep(1);


        if (chosenaction.equals("G") || chosenaction.equals("g")){//if statement for all games
            GameBooth.GameMenu();//displays possible games
            TimeUnit.SECONDS.sleep(1);
            Scanner GameChoice = new Scanner(System.in);
            RollingText.print("Enter your choice: ");
            String ChosenGame = GameChoice.nextLine();
            TimeUnit.SECONDS.sleep(1);


            if (ChosenGame.equals("1")||ChosenGame.equals(redblack.RBGameName())){//this is for red or black game
                redblack gamesim = new redblack();//registers the redblack game as the object gamesim
                if (participant.canPlay(gamesim.getCost())){//ensures that participant has enough money.  This is coded without hard coding values so that it is future proof (Modular)
                    RollingText.println("\n\n");
                    gamesim.play(participant);
                    RollingText.println("\n");
                }
                else{
                    RollingText.println("\n\nSorry, you do not have enough to play this game. ");
                }

            }
            else if (ChosenGame.equals("2")||ChosenGame.equals(PennyToss.PTGameName())){//this is for pennytoss
                PennyToss gamesim = new PennyToss();//initializes the pennytoss game as the object gamesim
                if (participant.canPlay(gamesim.getCost())){//checks if the player can afford to play
                    RollingText.println("\n\n");
                    gamesim.Play(participant); 
                    RollingText.println("\n");
                }
                else{
                    RollingText.println("\n\nSorry, you do not have enough to play this game. ");
                }
            }
            else if (ChosenGame.equals("3")||ChosenGame.equals(RouletteSpinner.RSGameName())){//this is for roulette
                if (participant.canPlay(5)){//I used the minimum amount a user can spend as the check for if the player can afford to play.  
                    boolean GamblingInput = true;//values I want reset each time a new game of roulette is played. Is used to ensure that if player picks the more expensive option, they have enough to do so otherwise reloop the gambling amount
                    int MasterGambleAmount = 0;
                    while (GamblingInput){
                        GameBooth.RoulettePrizes();//prints the roulette prizes
                        Scanner GambleChoice = new Scanner(System.in);
                        RollingText.print("Enter your gambling amount choice without a dollar sign: ");
                        int GambleAmount = GambleChoice.nextInt();
                        MasterGambleAmount = GambleAmount;
                        if ((GambleAmount==5||GambleAmount==10)&&participant.canPlay(GambleAmount)){//this is to check if the participant can play the more expensive version of roulette
                            RouletteSpinner GameSim = new RouletteSpinner(MasterGambleAmount);//creates the game after confirming that the player has 10 or more dollars
                            GameSim.play(participant);
                            GamblingInput = false;
                        }
                        else if ((GambleAmount==10)&&!participant.canPlay(GambleAmount)){//checks if the player can only afford the 5$ option
                            RollingText.println("\nYou do not have enough to play this game.  Please try the cheaper option.");
                        }   
                        else{
                            RollingText.println("That is not a valid amount.  Please try again. \n");//catch all for if the user inputs a integer that is not 5 or 10
                        }
                    }    
                }
                else{
                    RollingText.println("\n\nSorry, you do not have enough to play this game. ");
                }

            }
            else if (ChosenGame.equals("4")||ChosenGame.equals(BalloonPop.BPGameName())){//This is for the game balloon pop.  As a lot of the backend work is done within the class, not much code is needed here
                BalloonPop GameSim = new BalloonPop();//initializes the gamesim for balloonpop
                if (!participant.canPlay( BalloonPop.GetCost())){//checks if participant can play
                    RollingText.println("You do not have enough to play this game. ");

                }
                else{
                    participant.changeMoney(BalloonPop.GetCost());
                    GameSim.Play(participant);
                }

            }
            else{//catch all for inputs that are invalid
                RollingText.println("That is not a valid input.\n");
            }
        }
        /*
         * This segment is for information specific to the player.  
         */
        else if (chosenaction.equals("W") || chosenaction.equals("w")){//Checking players wallet
            RollingText.println("---------------------------------------");
            RollingText.println("you have " + participant.getMoney() + " dollars left.");
        }
        else if (chosenaction.equals("P") || chosenaction.equals("p")){//Checking for prizes the player has won
            GameBooth.PrizesMenu();//shows options on how to view the prizes
            Scanner prizeschoice = new Scanner(System.in);
            RollingText.print("Enter your choice: ");
            int prizesaction = actionchoice.nextInt();
            RollingText.println("---------------------------------------\n");
            if (prizesaction==1){
                participant.getPrizes();//this shows the prizes' individual names
            }
            else if (prizesaction==2){
                GameBooth.PrintPrizesBreakdown();//this shows where the prizes came from as well as the size of the prizes
            }
            else{
                RollingText.println("That is not an option.");//for ensuring invalid inputs have a response
            }
        }
        /*
         * This segment is used for when the player wants to quit.  The while loop is broken here
         */
        else if (chosenaction.equals("Q") || chosenaction.equals("q")){
            RollingText.println("---------------------------------------");
            RollingText.println("We hope that you had a good time here at the carnival.");
            participant.getPrizes();
            RollingText.println("Have a great rest of your night.");
            continueselection=false;
            RollingText.println("---------------------------------------");
        }
        else{//catch all for when the player inputs a wrong value in the selection menu
            RollingText.println("---------------------------------------");
            RollingText.println("That is not a valid option");
        }
        RollingText.println("\n-----------------------------------\n");


    }


    }

  }  

