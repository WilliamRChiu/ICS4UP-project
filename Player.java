package Carnival;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
/*
 * this entire class is made to set up the players' basic data
 */
public class Player {
    
    private double spendingMoney = 0;//this is setting up the instance variables such as players money and the list of their prizes
    private ArrayList<String> prizesWon = new ArrayList<>();
    private boolean eligibletoplay = true;

    public Player (double Money){//this is the constructor to initialize players' money
        spendingMoney = Money;
    }
    public double getMoney(){//this is used to find the player's remaining budget at any given point
        return spendingMoney;
    }
    public boolean canPlay(double cost){//this is used to check if the player has enough to play the game
        return ((spendingMoney-cost)>=0);
    }

    public void changeMoney (double Money){//this is used to charge the player for any game they play
        spendingMoney -= Money;
    }

    public void addprize (String prize){//this is to add a prize to the players arraylist of prizes won
        prizesWon.add(prize);
    }
    public void play(double cost, String prize) {
        if (spendingMoney >= cost) {
            spendingMoney -= cost;
            prizesWon.add(prize);
        } else {
            RollingText.println("Sorry, you don't have enough money to play.");
            eligibletoplay = false;
        }
    }
    public void getPrizes() throws InterruptedException{//this is used to check the unique names of all prizes the player has won during their session
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("You have " + spendingMoney + " dollars remaining");
        TimeUnit.SECONDS.sleep(1);
        RollingText.println("Your prizes are: ");
        int l = 0;
        for (String i: prizesWon){//iterates through the arraylist of won prizes and then prints them out
            RollingText.print(i);
            TimeUnit.MILLISECONDS.sleep(100);
            RollingText.print("    ");
            l+=1;
            if (l==3){
                RollingText.println("");//ensures that the page of prizes never gets too long
                l=0;
            }

        }
        RollingText.println("");
    }

    
}
