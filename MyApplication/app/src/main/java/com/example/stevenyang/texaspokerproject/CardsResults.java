package com.example.stevenyang.texaspokerproject;

import java.util.List;

/**
 * Created by User on 2017/2/24.
 */

public class CardsResults {
    List<Cards> resultLeftCards;
    boolean isItThisType ;
    int highCard;

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
}
