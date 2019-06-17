package io.zipcoder.casino.CardGames.Blackjack;

import io.zipcoder.casino.CardGames.UtilitiesCards.Card;
import io.zipcoder.casino.CardGames.UtilitiesCards.CardGame;
import io.zipcoder.casino.CardGames.UtilitiesCards.Hand;
import io.zipcoder.casino.utilities.*;

import java.util.ArrayList;
import java.util.List;

public class Blackjack extends CardGame implements GamblingGame {
    private Integer minBet;
    private BlackjackPlayer player;
    private BlackjackPlayer dealer;



    private Integer tester;


    private Integer betAmount;



    private Boolean currentGame = true;
    private Integer dealerHandValue;
    boolean isRunning = true;



    private String playerAnswer;



    private boolean playerTurn = true;
    private boolean playerBusted = false;

    public Blackjack() {
    }

    public Blackjack(BlackjackPlayer player, BlackjackPlayer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    public Blackjack(BasePlayer base, BasePlayer BJdealer, Console console) {


            this.player = new BlackjackPlayer(base);
            this.dealer = new BlackjackPlayer(BJdealer);
        while (isRunning) {
            gameReset();
            String readyToPlay = console.getStringInput("Ready to play y or n");
            if(readyToPlay.toUpperCase().equals("N")){
                new Lobby(new Console(System.in, System.out), this.player, new BasePlayer());
            }
            betAmount = console.getIntegerInput("Please enter your wager");
            while (currentGame) {
                console.println(placeWager(betAmount));
                setupBoard(console);
                while (checkPlayerAnswer(console)) {
                    //checkPlayerAnswer(console);
                    if(playerBustCheck()){ break;}

                    console.print(playerHandValue().toString());
                }
                    if(playerBustCheck()){ break;}

                    dealerTurn(console);
                    if(dealerBustCheck()){break;}
                    //console.println(whoWins());
                    //console.print(Hand.showHand((ArrayList<Card>) dealer.hand));
                    //console.println(test.toString());

            }

            whoWon(console);

        }
        }
        public void whoWon(Console console){
            if(dealerBustCheck()){
                console.println("You won " + betAmount*2);
                player.addToWallet(betAmount*2);
                tester = 1;
            }else if(playerBustCheck()){
                console.println("You lost " + betAmount);
                tester = 2;

            }else if(playerHandValue() > dealerHandValue()){
                console.println("You won " + betAmount*2);
                player.addToWallet(betAmount*2);
                tester=3;

            }else if(playerHandValue() < dealerHandValue()){
                console.println("You lost " + betAmount);
                tester = 4;

            }else if(playerHandValue().equals(dealerHandValue())){
                player.addToWallet(betAmount);
                console.println("It was a push");
                tester =5;
            }


        }
        public void gameReset(){
        currentGame = true;
        playerTurn = true;
        betAmount = 0;
        }
        public Boolean playerBustCheck() {
            Integer num = 0;

        if (playerHandValue() > 21) {

                return true;
            } else {

                return false;
            }
        }


        public Boolean dealerBustCheck(){
            Integer number = 0;
            Integer bustValue = 21;
            if(isAPresent(dealer.hand) > 0 && dealerHandValue()>21){
                number = 10 * isAPresent(dealer.hand);
            }
        if (dealerHandValue()-number > 21) {
                return true;
            } else {

                return false;
            }
        }
        public Integer isAPresent(List<Card> hand){
                Integer numofA = 0;
            for (int i = 0; i <hand.size(); i++) {

                if(hand.get(i).getFaceValue().getRankValue()== 1){

                numofA++;}

            }
            return numofA;
        }



        public void  dealerTurn(Console console){


        while(dealerHandValue() < 17){
            dealer.hand.add(draw());
            dealer.setHandValue(dealerHandValue());
            if(dealerBustCheck()){
                break;


            }

        }
            currentGame= false;
            console.print("Dealer hand: " + Hand.showHand((ArrayList<Card>) dealer.hand));
        }
        public boolean checkPlayerAnswer(Console console){
        playerAnswer = console.getStringInput("\nWould you like to hit or stay?");
        if(playerAnswer.toUpperCase().equals("HIT")){

            player.hand.add(draw());
            //player.setHandValue(playerHandValue());
            console.print(Hand.showHand((ArrayList<Card>) player.hand));
            //console.print(player.getHandValue().toString());
            //if(playerBustCheck()){
              //  console.println("You busted "+ betAmount + " has been lost");
              //  playerBusted= true;
               //  return false;

           // }
            return true;

        }else if(playerAnswer.toUpperCase().equals("STAY")){
            return false;

        }
        return true;
        }
        public void setupBoard(Console console){
            playerBusted = false;
            player.setHand(deal(2));
            dealer.setHand(deal(1));
            console.println("Dealer is dealt");
            console.print(Hand.showHand((ArrayList<Card>) dealer.hand));
            console.println("\nPlayer is dealt");
            console.print(Hand.showHand((ArrayList<Card>) player.hand));
           // player.setHandValue(playerHandValue());
            //dealer.setHandValue(dealerHandValue());

        }



        public Integer playerHandValue(){
        Integer num = 0;
        Integer playerHandValue= 0;
        for (int i = 0; i <player.hand.size(); i++) {

                playerHandValue += player.hand.get(i).getFaceValue().getBlackJackValue();

            }
            Integer temp = isAPresent(player.hand);
            if(temp  > 0 && playerHandValue> 21){
                num = 10 * temp;
            }

        return playerHandValue - num;
        }
    public Integer dealerHandValue() {
        dealerHandValue = 0;
        for (int j = 0; j < dealer.hand.size(); j++) {

            dealerHandValue += dealer.hand.get(j).getFaceValue().getBlackJackValue();

        }
        Integer num2 = 0;
        //Integer temp = isAPresent(dealer.hand);
        if(isAPresent(dealer.hand) > 0 && dealerHandValue> 21){
            num2 = 10 * isAPresent(dealer.hand);
        }
            return dealerHandValue-num2;

    }
        public Boolean isWin(){
            return null;
        }

        public void switchTurns(){
            BasePlayer temp = new BasePlayer("test");
        }


        public void welcomeMessage(){

        }
        public String placeWager(){
        return null;
        }

    @Override
    public void increaseMinBet() {

    }

    @Override
    public void decreaseMinBet() {

    }

    public String placeWager(Integer betAmount){
            String answer;
            this.betAmount = betAmount;
            if (player.getPlayer().removeFromWallet(betAmount)) {
                answer = "Bet Amount of " + betAmount + " Confirmed";

            } else {
                answer = "You currently do not have enough money to make that wager";
            }
            return answer;

        }

    public Boolean getCurrentGame() {
        return currentGame;
    }
    public Boolean getPlayerTurn(){return playerTurn;}
    public String getPlayerAnswer() {
        return playerAnswer;
    }
    public void setPlayerAnswer(String playerAnswer) {
        this.playerAnswer = playerAnswer;
    }
    public void setBetAmount(Integer betAmount) {
        this.betAmount = betAmount;
    }
    public Integer getTester() {
        return tester;
    }

    }


