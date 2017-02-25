package com.example.stevenyang.texaspokerproject;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 2017/2/24.
 */

public class CheckCardFuncUtil {
    int cardColor = 3;// 0 , 1 , 2 , 3

    //只需要看 isThisType , ResultLeftCards
    public static CardsResults royalFlush(List<Cards> mycards) {

        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);
        CardsResults sameColorResults = checkSameColor(mycards);
        CardsResults flushResults = checkFlush(mycards);
        if (sameColorResults.isItThisType && flushResults.isItThisType) {
            cardsResults.setItThisType(true);
            cardsResults.setHighCard(flushResults.getHighCard());
            cardsResults.setCardComboType(CardComboType.ROYAL_FLUSH);
            return cardsResults;
        }
        return cardsResults;
    }

    //同花的Result只需要看 isThisType , ResultLeftCards
    public static CardsResults checkSameColor(List<Cards> mycards) {
        List<Cards> newerCardList = new ArrayList<>();
        for (int check1 = 0; check1 < mycards.size(); check1++) {
            if (mycards.get(check1).getNumber() == 1) {
                mycards.get(check1).setNumber(14);
            }
        }
        newerCardList.addAll(mycards);


        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);

        List<Cards> tempYesList = new ArrayList();
//        List<Cards> leftList = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            int count = 0;
            tempYesList.clear();
            int tempHigh = 0;
            for (int i = 0; i < newerCardList.size(); i++) {


                if (newerCardList.get(i).getColor() == j) {
                    count++;
                    tempYesList.add(newerCardList.get(i));
                    if (newerCardList.get(i).getNumber() > tempHigh)
                        tempHigh = newerCardList.get(i).getNumber();

                }

            }
            if (count >= 5) {
                cardsResults.setItThisType(true);
                cardsResults.setHighCard(tempHigh);
                cardsResults.setResultLeftCards(tempYesList);
                cardsResults.setCardComboType(CardComboType.SAME_COLOR);
//                for (int t=0 ; t<tempYesList.size();t++){
//                    if(!mycards.contains(tempYesList.get(t))){
//                        leftList.add(tempYesList.get(t));
//                    }
//
//                }
                return cardsResults;

            }
        }
        return cardsResults;
    }

    //t只需要看 isThisType , HighCard
    public static CardsResults checkFlush(List<Cards> myCards) {
        List<Cards> newerCardList = new ArrayList<>();

        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);
        for (int check1 = 0; check1 < myCards.size(); check1++) {
            if (myCards.get(check1).getNumber() == 1) {

                myCards.get(check1).setNumber(14);
            }

        }
        newerCardList.addAll(myCards);
        Collections.sort(newerCardList);
        int maxCount = 0;
        int maxNumber = 0;
        for (int i = 0; i < newerCardList.size() - 1; i++) {
            if (newerCardList.get(i).getNumber() + 1 == newerCardList.get(i + 1).getNumber()) {
                maxCount++;
                maxNumber = newerCardList.get(i).getNumber() + 1;
            }
        }
        if (maxCount >= 5) {
            cardsResults.setHighCard(maxNumber);
            cardsResults.setItThisType(true);
            cardsResults.setCardComboType(CardComboType.FLUSH);
            return cardsResults;
        }
        return cardsResults;

    }

    //只需要看 isThisType , HighCard
    public static CardsResults checkFourOfAKind(List<Cards> myCards) {
        List<Cards> newerCardList = new ArrayList<>();

        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);
        for (int check1 = 0; check1 < myCards.size(); check1++) {
            if (myCards.get(check1).getNumber() == 1) {
                myCards.get(check1).setNumber(14);
            }
        }
        newerCardList.addAll(myCards);
        Collections.sort(newerCardList);

        for (int i = 0; i < 4; i++) {
            if (newerCardList.get(i).getNumber() == newerCardList.get(i + 1).getNumber() && newerCardList.get(i).getNumber() == newerCardList.get(i + 2).getNumber() && newerCardList.get(i).getNumber() == newerCardList.get(i + 3).getNumber()) {
                int highest = 0;

                for (int j = 0; j < newerCardList.size(); j++) {
                    if (newerCardList.get(j).getNumber() != newerCardList.get(i).getNumber()) {
                        if (newerCardList.get(j).getNumber() > highest)
                            highest = newerCardList.get(j).getNumber();

                    }
                }
                cardsResults.setItThisType(true);
                cardsResults.setHighCard(highest);
                cardsResults.setCardComboType(CardComboType.FOUR_OF_SAME);
                return cardsResults;

            }
        }
        return cardsResults;
    }

    //只要用到highest 與 leftCard List 與 isType
    public static CardsResults checkThreeOfAKind(List<Cards> myCards) {
        List<Cards> newerCardList = new ArrayList<>();
        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);
        for (int check1 = 0; check1 < myCards.size(); check1++) {
            if (myCards.get(check1).getNumber() == 1) {
                myCards.get(check1).setNumber(14);
            }
        }
        newerCardList.addAll(myCards);
        Collections.sort(newerCardList);

        for (int i = 0; i < 5; i++) {
            if (newerCardList.get(i).getNumber() == newerCardList.get(i + 1).getNumber() && newerCardList.get(i).getNumber() == newerCardList.get(i + 2).getNumber()) {
                int highest;

                highest = newerCardList.get(i).getNumber();
                newerCardList.remove(i);
                newerCardList.remove(i);
                newerCardList.remove(i);


//                for(int j=0 ; j<newerCardList.size() ; j++){
//                    if(newerCardList.get(j).getNumber()!=newerCardList.get(i).getNumber()){
//                        if(newerCardList.get(j).getNumber()>highest)
//                            highest = newerCardList.get(j).getNumber();
//
//                    }
//                }
                cardsResults.setItThisType(true);
                cardsResults.setHighCard(highest);
                cardsResults.setResultLeftCards(newerCardList);
                cardsResults.setCardComboType(CardComboType.THREE_OF_SAME);
                return cardsResults;

            }
        }
        return cardsResults;
    }

    public static CardsResults checkFullHouse(List<Cards> myCards) {
        List<Cards> newerCardList = new ArrayList<>();
        int highest = 0;
        int withPairs = 0;


        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);
        for (int check1 = 0; check1 < myCards.size(); check1++) {
            if (myCards.get(check1).getNumber() == 1) {
                myCards.get(check1).setNumber(14);
            }
        }
        newerCardList.addAll(myCards);
        Collections.sort(newerCardList);
        for (Cards cards : newerCardList) {
            Log.i("cards list sorted", cards.getNumber() + "");
        }
        for (int i = 0; i < 5; i++) {
            if (newerCardList.get(i).getNumber() == newerCardList.get(i + 1).getNumber() && newerCardList.get(i).getNumber() == newerCardList.get(i + 2).getNumber()) {
                highest = newerCardList.get(i).getNumber();
                newerCardList.remove(i);
                newerCardList.remove(i);
                newerCardList.remove(i);
                break;
            }
        }
        for (int i = 0; i < 2; i++) {
            if (newerCardList.get(i).getNumber() == newerCardList.get(i + 1).getNumber()) {
                withPairs = newerCardList.get(i).getNumber();
                newerCardList.remove(i);
                newerCardList.remove(i);
                break;
            }
        }

        if (newerCardList.get(0).getNumber() == newerCardList.get(1).getNumber()) {
            if (withPairs < newerCardList.get(0).getNumber()) {
                withPairs = newerCardList.get(0).getNumber();
            }
        }
        if (highest != 0 && withPairs != 0) {
            cardsResults.setItThisType(true);
            cardsResults.setHighCard(highest);
            cardsResults.setLowerPairCard(withPairs);
            cardsResults.setCardComboType(CardComboType.FULL_HOUSE);

        }

        return cardsResults;
    }

    public static CardsResults checkTwoPairs(List<Cards> myCards) {
        List<Cards> newerCardList = new ArrayList<>();
        List<Cards> tempCardList = new ArrayList<>();

        int highest = 0;
        int withPairs = 0;
        int highHandCard = 0;


        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);
        for (int check1 = 0; check1 < myCards.size(); check1++) {
            if (myCards.get(check1).getNumber() == 1) {
                myCards.get(check1).setNumber(14);
            }
        }
        newerCardList.addAll(myCards);
        Collections.sort(newerCardList);

        tempCardList.addAll(newerCardList);

        for (Cards cards : newerCardList) {
            Log.i("cards list sorted", cards.getNumber() + "");
        }
        for (int i = 0; i < 6; i++) {
            if (newerCardList.get(i).getNumber() == newerCardList.get(i + 1).getNumber()) {
                highest = newerCardList.get(i).getNumber();
                newerCardList.remove(i);
                newerCardList.remove(i);
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (newerCardList.get(i).getNumber() == newerCardList.get(i + 1).getNumber()) {
                if (newerCardList.get(i).getNumber() > highest) {
                    withPairs = highest;
                    highest = newerCardList.get(i).getNumber();
                }
                newerCardList.remove(i);
                newerCardList.remove(i);
                break;
            }
        }

        for (int i = 0; i < 2; i++) {
            if (newerCardList.get(i).getNumber() == newerCardList.get(i + 1).getNumber()) {
                if (newerCardList.get(i).getNumber() > highest) {
                    withPairs = highest;
                    highest = newerCardList.get(i).getNumber();
                } else if (newerCardList.get(i).getNumber() > withPairs) {
                    withPairs = newerCardList.get(i).getNumber();
                }
                newerCardList.remove(i);
                newerCardList.remove(i);
                break;
            }
        }
        for (int i = 0; i < tempCardList.size(); i++) {
            if (tempCardList.get(i).getNumber() != highest && tempCardList.get(i).getNumber() != withPairs) {
                if (tempCardList.get(i).getNumber() > highHandCard) {
                    highHandCard = tempCardList.get(i).getNumber();
                }
            }

        }

        if (highest != 0 && withPairs != 0) {
            cardsResults.setItThisType(true);
            cardsResults.setLargePair(highest);
            cardsResults.setLowerPairCard(withPairs);
            cardsResults.setHighCard(highHandCard);
            cardsResults.setCardComboType(CardComboType.TWO_PAIRS);

        }

        return cardsResults;
    }

    public static CardsResults checkPairs(List<Cards> myCards) {
        List<Cards> newerCardList = new ArrayList<>();

        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);
        for (int check1 = 0; check1 < myCards.size(); check1++) {
            if (myCards.get(check1).getNumber() == 1) {
                myCards.get(check1).setNumber(14);
            }
        }
        newerCardList.addAll(myCards);
        Collections.sort(newerCardList);

        for (int i = 0; i < newerCardList.size()-1; i++) {
            if (newerCardList.get(i).getNumber() == newerCardList.get(i + 1).getNumber()) {
                cardsResults.setItThisType(true);
                cardsResults.setLargePair(newerCardList.get(i).getNumber());

//                cardsResults.setLowerPairCard(withPairs);

                newerCardList.remove(i);
                newerCardList.remove(i);

                cardsResults.setResultLeftCards(newerCardList);
                cardsResults.setCardComboType(CardComboType.PAIR);

                return cardsResults;
            }
        }
        return cardsResults;
    }
    public static FinalResults compair(List<Cards> player1Cards , List<Cards> player2Cards){

        FinalResults finalResults = new FinalResults();


        //royalFlush
        CardsResults player1Results = royalFlush(player1Cards);
        CardsResults player2Results = royalFlush(player2Cards);
        if(player1Results.isItThisType&&player2Results.isItThisType){
               // same type rule
            if(player1Results.getHighCard()>player2Results.getHighCard()){
                finalResults.setFlag(1);
                finalResults.setResultString(player1Results.getCardComboType());

            }else if(player1Results.getHighCard()<player2Results.getHighCard()){
                finalResults.setFlag(2);
                finalResults.setResultString(player2Results.getCardComboType());

            }else{
                finalResults.setFlag(0);

            }
            return finalResults;

        }else if(player1Results.isItThisType){
            finalResults.setFlag(1);
            finalResults.setResultString(player1Results.getCardComboType());
            return finalResults;
        }else if(player2Results.isItThisType){
            finalResults.setFlag(2);
            finalResults.setResultString(player2Results.getCardComboType());
            return finalResults;
        }
        //FourOfAKind
        player1Results = checkFourOfAKind(player1Cards);
        player2Results = checkFourOfAKind(player2Cards);
        if(player1Results.isItThisType&&player2Results.isItThisType){
            // same type rule
            if(player1Results.getHighCard()>player2Results.getHighCard()){
                finalResults.setFlag(1);
                finalResults.setResultString(player1Results.getCardComboType());

            }else if(player1Results.getHighCard()<player2Results.getHighCard()){
                finalResults.setFlag(2);
                finalResults.setResultString(player2Results.getCardComboType());

            }else{
                finalResults.setFlag(0);

            }

            return finalResults;
        }else if(player1Results.isItThisType){
            finalResults.setFlag(1);
            finalResults.setResultString(player1Results.getCardComboType());
            return finalResults;

        }else if(player2Results.isItThisType){
            finalResults.setFlag(2);
            finalResults.setResultString(player2Results.getCardComboType());
            return finalResults;

        }
//fullHouse
        player1Results = checkFullHouse(player1Cards);
        player2Results = checkFullHouse(player2Cards);
        if(player1Results.isItThisType&&player2Results.isItThisType){
            // same type rule
            if(player1Results.getHighCard()>player2Results.getHighCard()){
                finalResults.setFlag(1);
                finalResults.setResultString(player1Results.getCardComboType());

            }else if(player1Results.getHighCard()<player2Results.getHighCard()){
                finalResults.setFlag(2);
                finalResults.setResultString(player2Results.getCardComboType());

            }else{
                if(player1Results.getLowerPairCard()>player2Results.getLowerPairCard()){
                    finalResults.setFlag(1);
                    finalResults.setResultString(player1Results.getCardComboType());

                }else if(player1Results.getLowerPairCard()<player2Results.getLowerPairCard()){
                    finalResults.setFlag(2);
                    finalResults.setResultString(player2Results.getCardComboType());

                }else{
                    finalResults.setFlag(0);

                }


            }
            return finalResults;

        }else if(player1Results.isItThisType){
            finalResults.setFlag(1);
            finalResults.setResultString(player1Results.getCardComboType());
            return finalResults;

        }else if(player2Results.isItThisType){
            finalResults.setFlag(2);
            finalResults.setResultString(player2Results.getCardComboType());
            return finalResults;

        }


        //sameColor
        player1Results = checkSameColor(player1Cards);
        player2Results = checkSameColor(player2Cards);
        if(player1Results.isItThisType&&player2Results.isItThisType){
            List<Cards> sorted1 = new ArrayList<Cards>();
            List<Cards> sorted2 = new ArrayList<Cards>();
            sorted1.addAll(player1Results.getResultLeftCards());
            sorted2.addAll(player2Results.getResultLeftCards());
            Collections.sort(sorted1);
            Collections.sort(sorted2);
            for (int i = sorted1.size() - 1; i >= 0; i--) {
                if(sorted1.get(i).getNumber()>sorted2.get(i).getNumber()){
                    finalResults.setFlag(1);
                    finalResults.setResultString(player1Results.getCardComboType());
                    return finalResults;
                }else if(sorted1.get(i).getNumber()<sorted2.get(i).getNumber()){
                    finalResults.setFlag(2);
                    finalResults.setResultString(player2Results.getCardComboType());
                    return finalResults;
                }
            }
            finalResults.setFlag(0);
            return finalResults;

        }else if(player1Results.isItThisType){
            finalResults.setFlag(1);
            finalResults.setResultString(player1Results.getCardComboType());
            return finalResults;

        }else if(player2Results.isItThisType){
            finalResults.setFlag(2);
            finalResults.setResultString(player2Results.getCardComboType());
            return finalResults;

        }


        //flush
        player1Results = checkFlush(player1Cards);
        player2Results = checkFlush(player2Cards);
        if(player1Results.isItThisType&&player2Results.isItThisType){
            // same type rule
            if(player1Results.getHighCard()>player2Results.getHighCard()){
                finalResults.setFlag(1);
                finalResults.setResultString(player1Results.getCardComboType());

            }else if(player1Results.getHighCard()<player2Results.getHighCard()){
                finalResults.setFlag(2);
                finalResults.setResultString(player2Results.getCardComboType());

            }else{
                finalResults.setFlag(0);

            }

            return finalResults;
        }else if(player1Results.isItThisType){
            finalResults.setFlag(1);
            finalResults.setResultString(player1Results.getCardComboType());
            return finalResults;

        }else if(player2Results.isItThisType){
            finalResults.setFlag(2);
            finalResults.setResultString(player2Results.getCardComboType());
            return finalResults;

        }


        //checkThreeOfAKind
        player1Results = checkThreeOfAKind(player1Cards);
        player2Results = checkThreeOfAKind(player2Cards);
        if(player1Results.isItThisType&&player2Results.isItThisType){
            List<Cards> sorted1 = new ArrayList<Cards>();
            List<Cards> sorted2 = new ArrayList<Cards>();
            sorted1.addAll(player1Results.getResultLeftCards());
            sorted2.addAll(player2Results.getResultLeftCards());
            Collections.sort(sorted1);
            Collections.sort(sorted2);
            for (int i = sorted1.size() - 1; i >= 0; i--) {
                if(sorted1.get(i).getNumber()>sorted2.get(i).getNumber()){
                    finalResults.setFlag(1);
                    finalResults.setResultString(player1Results.getCardComboType());
                    return finalResults;
                }else if(sorted1.get(i).getNumber()<sorted2.get(i).getNumber()){
                    finalResults.setFlag(2);
                    finalResults.setResultString(player2Results.getCardComboType());
                    return finalResults;
                }
            }
            finalResults.setFlag(0);
            return finalResults;

        }else if(player1Results.isItThisType){
            finalResults.setFlag(1);
            finalResults.setResultString(player1Results.getCardComboType());
            return finalResults;

        }else if(player2Results.isItThisType){
            finalResults.setFlag(2);
            finalResults.setResultString(player2Results.getCardComboType());
            return finalResults;

        }

        //two pairs
        player1Results = checkTwoPairs(player1Cards);
        player2Results = checkTwoPairs(player2Cards);
        if(player1Results.isItThisType&&player2Results.isItThisType){
            // same type rule
            if(player1Results.getLargePair()>player2Results.getLargePair()){
                finalResults.setFlag(1);
                finalResults.setResultString(player1Results.getCardComboType());

            }else if(player1Results.getLargePair()<player2Results.getLargePair()){
                finalResults.setFlag(2);
                finalResults.setResultString(player2Results.getCardComboType());

            }else{
                if(player1Results.getLowerPairCard()>player2Results.getLowerPairCard()){
                    finalResults.setFlag(1);
                    finalResults.setResultString(player1Results.getCardComboType());

                }else if(player1Results.getLowerPairCard()<player2Results.getLowerPairCard()){
                    finalResults.setFlag(2);
                    finalResults.setResultString(player2Results.getCardComboType());

                }else{
                    if(player1Results.getHighCard()>player2Results.getHighCard()){
                        finalResults.setFlag(1);
                        finalResults.setResultString(player1Results.getCardComboType());

                    }else if(player1Results.getHighCard()<player2Results.getHighCard()){
                        finalResults.setFlag(2);
                        finalResults.setResultString(player2Results.getCardComboType());

                    }else{
                        finalResults.setFlag(0);

                    }

                }

            }

            return finalResults;
        }else if(player1Results.isItThisType){
            finalResults.setFlag(1);
            finalResults.setResultString(player1Results.getCardComboType());
            return finalResults;

        }else if(player2Results.isItThisType){
            finalResults.setFlag(2);
            finalResults.setResultString(player2Results.getCardComboType());
            return finalResults;

        }

        //checkPair
        player1Results = checkPairs(player1Cards);
        player2Results = checkPairs(player2Cards);
        if(player1Results.isItThisType&&player2Results.isItThisType){

            for (int i = player1Results.getResultLeftCards().size() - 1; i >= 0; i--) {
                if(player1Results.getResultLeftCards().get(i).getNumber()>player2Results.getResultLeftCards().get(i).getNumber()){
                    finalResults.setFlag(1);
                    finalResults.setResultString(player1Results.getCardComboType());
                    return finalResults;
                }else if(player1Results.getResultLeftCards().get(i).getNumber()<player2Results.getResultLeftCards().get(i).getNumber()){
                    finalResults.setFlag(2);
                    finalResults.setResultString(player2Results.getCardComboType());
                    return finalResults;
                }
            }
            finalResults.setFlag(0);
            return finalResults;

        }else if(player1Results.isItThisType){
            finalResults.setFlag(1);
            finalResults.setResultString(player1Results.getCardComboType());
            return finalResults;

        }else if(player2Results.isItThisType){
            finalResults.setFlag(2);
            finalResults.setResultString(player2Results.getCardComboType());
            return finalResults;

        }

        //finally
        for (int i = player1Cards.size() - 1; i >= 0; i--) {
            if(player1Cards.get(i).getNumber()>player2Cards.get(i).getNumber()){
                finalResults.setFlag(1);
                finalResults.setResultString(CardComboType.HIGH);
            }else if (player1Cards.get(i).getNumber()<player2Cards.get(i).getNumber()){
                finalResults.setFlag(2);
                finalResults.setResultString(CardComboType.HIGH);
            }
        }
        finalResults.setFlag(0);
        return finalResults;

    }

}
