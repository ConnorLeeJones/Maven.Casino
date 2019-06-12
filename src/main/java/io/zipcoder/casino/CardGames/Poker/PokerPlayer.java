package io.zipcoder.casino.CardGames.Poker;

import io.zipcoder.casino.utilities.BasePlayer;
import io.zipcoder.casino.CardGames.UtilitiesCards.Card;

import java.util.List;

public class PokerPlayer extends BasePlayer {
    private BasePlayer player;
    private Boolean stand;
    List<Card> hand;

    public PokerPlayer(BasePlayer player) {
        this.player = player;
    }

    public void raise(){

    }
    public void call(){

    }
    public void check(){

    }

    public BasePlayer getPlayer() {
        return player;
    }

    public void setPlayer(BasePlayer player) {
        this.player = player;
    }

    public Boolean getStand() {
        return stand;
    }

    public void setStand(Boolean stand) {
        this.stand = stand;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
}