package com.example.stevenyang.texaspokerproject;

import java.util.List;

/**
 * Created by User on 2017/2/24.
 */

public class CardsResults {
    List<Cards> resultLeftCards;
    boolean isItThisType ;
    int highCard;
    int lowerPairCard; // for two pair or full house
    int largePair;// for two pair
    CardComboType cardComboType;

    public CardComboType getCardComboType() {
        return cardComboType;
    }

    public void setCardComboType(CardComboType cardComboType) {
        this.cardComboType = cardComboType;
    }

    public int getLargePair() {
        return largePair;
    }

    public void setLargePair(int largePair) {
        this.largePair = largePair;
    }

    public List<Cards> getResultLeftCards() {
        return resultLeftCards;
    }

    public void setResultLeftCards(List<Cards> resultLeftCards) {
        this.resultLeftCards = resultLeftCards;
    }

    public boolean isItThisType() {
        return isItThisType;
    }

    public void setItThisType(boolean itThisType) {
        isItThisType = itThisType;
    }

    public int getHighCard() {
        return highCard;
    }

    public void setHighCard(int highCard) {
        this.highCard = highCard;
    }

    public int getLowerPairCard() {
        return lowerPairCard;
    }

    public void setLowerPairCard(int lowerPairCard) {
        this.lowerPairCard = lowerPairCard;
    }
}
