package Carnival;
import java.util.*;
import java.util.concurrent.TimeUnit;
/* This class is the parent class of all games so it is kind of like the generic hub for commands.
 * It holds the large text methods that would otherwise clutter the workspace 
 */
public class GameBooth{//instance variables. 
    private double cost;
    private String smallPrize;
    private String largePrize;
    private static Dictionary<String, Integer> LargePrizesList = new Hashtable<String,Integer>(4);//Hashtables are used to help categorize specific key data with changing variables.  In specific this is for prize tracking across all games
    private static Dictionary<String, Integer> SmallPrizesList = new Hashtable<String,Integer>(4);
    private static String[] AllGameNames = new String[] {redblack.RBGameName(), PennyToss.PTGameName(),RouletteSpinner.RSGameName(), BalloonPop.BPGameName()}; 
    //made a list of game names in the case that if one game is switched in terms of naming conventions, the program will still work (I.e. it is very modular)

    public GameBooth(double cost, String smallPrize, String largePrize){
        this.cost = cost;//assigning instance variables values
        this.smallPrize = smallPrize;
        this.largePrize = largePrize;
    }
    public double getCost(){//used to get the cost of games
        return cost;
    }
    public static void GameMenu() throws InterruptedException{//Game Menu, made it into a method so that code is easier to look at through the main
        RollingText.println("What game would you like to play? ");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println( "(1) Red or Black" );
        RollingText.println( "(2) Penny Throw");
        RollingText.println("(3) Roulette Spinner");
        RollingText.println("(4) Balloon Pop");
    }
    public static void EnterScreen() throws InterruptedException{//Game options.  Again, made a static method in gamebooth so that the Main class is easier to read
        RollingText.println("Choose one of the menu options: ");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("(G) Play a Game ");
        RollingText.println("(P) See Prizes");
        RollingText.println("(W) See How Much Money You Have");
        RollingText.println( "(Q) Quit");
    }
    public static void RoulettePrizes() throws InterruptedException{//Made this a method to de clutter the roulette game class
        RollingText.println("You can gamble either $5 or $10.\n");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("The possible Gambling prizes are: \n");
        TimeUnit.MILLISECONDS.sleep(500);
        RollingText.println("($5) Small Naruto Statue\n");
        TimeUnit.MILLISECONDS.sleep(500);
        RollingText.println("($10) Large Charizard Statue\n");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("If you get only the right colour, you win the set prize.");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("\nIf you get the right number and colour, you recieve an upgraded prize.");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("\nOtherwise, you get nothing.\n\n");
        TimeUnit.SECONDS.sleep(1);
    }
    public static void PrizesMenu() throws InterruptedException{//Prizes menu, displays the different ways a player can view their prizes
        RollingText.println("Choose which option you want: ");
        TimeUnit.MILLISECONDS.sleep(750);
        RollingText.println("(1) View All Prizes");
        RollingText.println("(2) View number of small and large prizes for each game");
        TimeUnit.MILLISECONDS.sleep(750);
    }

    public void AddBoothPrizes(String game, String prizename){//This is a catch all method for adding the type of prizes to their respective game origin
        if (prizename.equals(smallPrize)&&((Hashtable<String, Integer>) SmallPrizesList).containsKey(game)){//This uses the specific attributes of a Hashtable to find if a game is part of the hashtables keys
            ((Hashtable<String, Integer>) SmallPrizesList).replace(game,SmallPrizesList.get(game), (SmallPrizesList.get(game)+1));
            //if the hastable does have the key, it adds one to the smallprizes in the corresponding values of smallprizeslist
        }
        //This else if statement is if the small prize has not been ever recorded in the hashtable.  If true, then it adds the game to boht the small and large prizes lists and adds how many prizes of each type were won
        else if (prizename.equals(smallPrize)&&!(((Hashtable<String, Integer>) SmallPrizesList).containsKey(game))){
            ((Hashtable<String, Integer>) SmallPrizesList).putIfAbsent(game,1);
            ((Hashtable<String, Integer>) LargePrizesList).putIfAbsent(game,0);
        }//These next two else if statements are here to repeat the same actions as above, but only if the prize is a large prize instead of a small prize
        else if (prizename.equals(largePrize)&&((Hashtable<String, Integer>) LargePrizesList).containsKey(game)){
            ((Hashtable<String, Integer>) LargePrizesList).replace(game,LargePrizesList.get(game), (LargePrizesList.get(game)+1));

        }
        else if (prizename.equals(largePrize)&&!(((Hashtable<String, Integer>) LargePrizesList).containsKey(game))){
            ((Hashtable<String, Integer>) LargePrizesList).putIfAbsent(game,1);
            ((Hashtable<String, Integer>) SmallPrizesList).putIfAbsent(game,0);
        }
    }
    //This method works to print out the different keys in the hashtable (I.e. the game names) and the corresponding winnings found from each game type
    public static void PrintPrizesBreakdown(){
        for (String key:((Hashtable<String, Integer>) SmallPrizesList).keySet()){
            RollingText.println(key + ": " + SmallPrizesList.get(key) + " small Prizes, " + LargePrizesList.get(key) + " Large Prizes " );
        }
    }
}
