package Carnival;
import java.util.*;
import java.util.concurrent.TimeUnit;
/*
 * this is the redblackg game.  It randomly creates a set of coins, takes out 3 coins, and then checks their colours
 */

public class redblack extends GameBooth {//inheriting the gamebooth
    private ArrayList<String> bag = new ArrayList<String>();
    private ArrayList<String> drawn_chips = new ArrayList<String>();
    private static final String gamename = "Red or Black";
    private static final double cost = 1.50;
    private static final String smallPrize = "Keychain";
    private static final String largePrize = "Plush Fish";
    public redblack(){
        super (cost, smallPrize, largePrize);//setting up superclass for the redblack data in the parent class
        for (int i=0; i<11; i++){//this sets up the bag so that there are equal amounts of red and black balls
            //there are 11 of each colour so that there is a higher probability of getting 3 of one colour as the chips are removed
            bag.add("Red");
            bag.add("Black");
        }
    }
    public static String RBGameName(){//global method for getting game name of red and black
        return (gamename);
    }
    public double getCost(){//global method for getting the cost of the game
        return cost;
    }

    

    public void play(Player participant) throws InterruptedException{//main game method
        participant.changeMoney(super.getCost());//charges the player for the cost of the game
        //this is general text instructions for the player
        RollingText.println("The cost for Red or Black is " + super.getCost() + " dollars.  You will draw three tokens. ");
        RollingText.println("");
        TimeUnit.MILLISECONDS.sleep(750);
        RollingText.println("If you draw 3 Red tokens or 3 Black tokens you win a Plush Fish.  Otherwise you win a keychain.");
        TimeUnit.MILLISECONDS.sleep(750);
        RollingText.println("");
        RollingText.println("Your draws are: \n");
        int j = 22;//this is to find the number of chips in the bag for the math.random command
        for (int i=3; i>0; i--){
            String draw = bag.get((int)(Math.random()*j));//this gives a random number from 0 to the max number of chips
            j--;
            TimeUnit.MILLISECONDS.sleep(750);
            RollingText.println(draw);//prints out the draws
            RollingText.println("...");
            drawn_chips.add(draw);//collects the draws
        }
        //The rest of this code is for distributing prizes
        if (drawn_chips.get(0).equals(drawn_chips.get(1))&&drawn_chips.get(0).equals(drawn_chips.get(2))){
            participant.addprize("Plush Fish");
            super.AddBoothPrizes(gamename, largePrize);
            RollingText.println ("You won a Plush Fish!");
        }
        else{
            participant.addprize("keychain");
            super.AddBoothPrizes(gamename, smallPrize);
            RollingText.println ("You won a Keychain!");
        }
    }
}
