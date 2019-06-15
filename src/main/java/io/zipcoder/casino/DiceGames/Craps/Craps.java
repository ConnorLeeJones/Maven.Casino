package io.zipcoder.casino.DiceGames.Craps;

import io.zipcoder.casino.DiceGames.UtilitiesDice.Dice;
import io.zipcoder.casino.DiceGames.UtilitiesDice.DiceGame;
import io.zipcoder.casino.utilities.BasePlayer;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.GamblingGame;

public class Craps extends DiceGame implements GamblingGame {
    private Integer minBet;
    CrapsPlayer player;
    CrapsPlayer dealer;
    private Console console;
    private int startBet;
    private int winAmount;
    private Boolean win;
    private int rollCount = 1;
    private int lastRolledValue;

    public Craps(BasePlayer base, BasePlayer dealer){
        super();
        this.player = (CrapsPlayer) base;
        this.dealer = (CrapsPlayer) dealer;
        this.startBet = 0;
        this.minBet = 20;
    }

    public void initGame () {
        String Menu = "";
        do {
            Menu = console.getStringInput(printMenu());
            switch (Menu.toUpperCase()){
                case "RULES": printRules();
                break;
                case "Play": console.println(placeWager());
                if (startBet >=minBet ){
                    playGame();

                }
                break;
            }
        } while (!Menu.equals("quit"));

    }

    public void playGame(){
        int rolledValue = roll();
        if(rollCount ==1){
            if (rolledValue == 2 || rolledValue == 3 || rolledValue == 12){
                winAmount = 0;
                win = false;
            } else if (rolledValue == 7 || rolledValue == 11){
                winAmount = startBet * 2;
                win = true;
            } else {
                rollCount = rollCount+1;
                playGame();
                lastRolledValue =  rolledValue;
            }

        }else{
            if (rolledValue == 7 || rolledValue == 11){
                winAmount = 0;
                win = false;
            } else if (rolledValue == lastRolledValue){
                winAmount = startBet * 2;
                win = true;
            } else {
                rollCount = rollCount+1;
                playGame();
            }

        }

    }
    public Integer roll() {
        return super.rollDice();
    }



    public Boolean isWin() {
        return null;
    }

    public void switchTurns() {

    }

    @Override
    public void welcomeMessage() {

           console.println("WELCOME TO THE CRAPS TABLE");

    }


    public String placeWager() {
        int bet = 0;
        if (bet < minBet) {
        bet = console.getIntegerInput("Please input a wager(Min bet: $" + minBet + ")", bet);
            return null;
        }
        return null;
    }

    public Integer getMinBet() {
        return minBet;
    }

    public void setMinBet(Integer minBet) {
        this.minBet = minBet;
    }

    public CrapsPlayer getPlayer() {
        return player;
    }

    public void setPlayer(CrapsPlayer player) {
        this.player = player;
    }

    public CrapsPlayer getDealer() {
        return dealer;
    }

    public void setDealer(CrapsPlayer dealer) {
        this.dealer = dealer;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public int getStartBet() {
        return startBet;
    }

    public void setStartBet(int startBet) {
        this.startBet = startBet;
    }

    public int getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(int winAmount) {
        this.winAmount = winAmount;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public void printRules() {
        console.println("\nRULES" +
                "\n If you get 7 or 11 on your first roll, You Win\n" +
                "If you get 2 or 3 or 12 on your first roll, You Lose\n " +
                "If you get 4 or 5 or 6 or 8 or 9 or 10 on your first roll, You need to match that number to win but if you roll 7 or 11 before then, you Lose. \n" +
                "You place your initial wager to play the game and double the money");
    }

    public String printMenu(){

        return "\nPlease Type In An Option: " +
                "\nPlay:              [Play]" +
                "\nSee Rules:         [Rules]";
    }
    @Override
    public void increaseMinBet() {

    }

    @Override
    public void decreaseMinBet() {

    }



}