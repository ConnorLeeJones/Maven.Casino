package io.zipcoder.casino.CardGames.UtilitiesCards;

public class Card implements Comparable<Card> {
    private Suit suit;
    private Rank faceValue;
    private char imageValue;
    private Boolean isFaceUp;
    //can define as enum

    public Card(Suit suit, Rank faceValue, char imageValue) {
        this.faceValue = faceValue;
        this.suit = suit;
        this.imageValue = imageValue;
    }

    public Suit getSuite() {
        return this.suit;
    }

    public void setSuite(Suit suit) {
        this.suit = suit;
    }

    public Rank getFaceValue() {
        return this.faceValue;
    }

    public void setFaceValue(Rank faceValue) {
        this.faceValue = faceValue;
    }

    public char getImageValue() {
        return imageValue;
    }

    public String printCard(){
        return this.faceValue.getRankString() + " of " + this.suit.getSuitText();
    }


    @Override
    public int compareTo(Card card) {
        return (Integer.compare(this.getFaceValue().getRankValue(), card.getFaceValue().getRankValue()));
    }
}