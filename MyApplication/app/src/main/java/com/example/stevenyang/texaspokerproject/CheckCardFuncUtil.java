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
    public static CardsResults royalFlush(List<Cards> mycards){

        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);
        CardsResults sameColorResults =checkSameColor(mycards);
        CardsResults flushResults =checkFlush(mycards);
        if(sameColorResults.isItThisType&&flushResults.isItThisType){
            cardsResults.setItThisType(true);
            cardsResults.setHighCard(flushResults.getHighCard());
            return cardsResults;
        }
        return cardsResults;
    }

    //同花的Result只需要看 isThisType , ResultLeftCards
    public static CardsResults checkSameColor(List<Cards> mycards) {
        List <Cards> newerCardList = new ArrayList<>();
        for (int check1=0 ; check1<mycards.size();check1++){
            if(mycards.get(check1).getNumber()==1){
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
                    if(newerCardList.get(i).getNumber()>tempHigh)
                        tempHigh = newerCardList.get(i).getNumber();

                }

            }
            if(count>=5){
                cardsResults.setItThisType(true);
                cardsResults.setHighCard(tempHigh);
                cardsResults.setResultLeftCards(tempYesList);
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
    public static CardsResults checkFlush(List<Cards> myCards){
        List <Cards> newerCardList = new ArrayList<>();

        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);
        for (int check1=0 ; check1<myCards.size();check1++){
            if(myCards.get(check1).getNumber()==1){

                    myCards.get(check1).setNumber(14);
                }

        }
        newerCardList.addAll(myCards);
        Collections.sort(newerCardList);
        int maxCount = 0;
        int maxNumber = 0;
        for(int i=0 ; i<newerCardList.size()-1;i++){
            if(newerCardList.get(i).getNumber()+1==newerCardList.get(i+1).getNumber()){
                maxCount++;
                maxNumber = newerCardList.get(i).getNumber()+1;
            }
        }
        if(maxCount>=5){
            cardsResults.setHighCard(maxNumber);
            cardsResults.setItThisType(true);

            return cardsResults;
        }
        return cardsResults;

    }
    //只需要看 isThisType , HighCard
    public static CardsResults checkFourOfAKind(List<Cards> myCards){
        List <Cards> newerCardList = new ArrayList<>();

        CardsResults cardsResults = new CardsResults();
        cardsResults.setItThisType(false);
        for (int check1=0 ; check1<myCards.size();check1++){
            if(myCards.get(check1).getNumber()==1){
                myCards.get(check1).setNumber(14);
            }
        }
        newerCardList.addAll(myCards);
        Collections.sort(newerCardList);

        for(int i=0 ; i<4 ; i++){
            if(newerCardList.get(i).getNumber()==newerCardList.get(i+1).getNumber()&&newerCardList.get(i).getNumber()==newerCardList.get(i+2).getNumber()&&newerCardList.get(i).getNumber()==newerCardList.get(i+3).getNumber()){
               int highest = 0;

                for(int j=0 ; j<newerCardList.size() ; j++){
                    if(newerCardList.get(j).getNumber()!=newerCardList.get(i).getNumber()){
                        if(newerCardList.get(j).getNumber()>highest)
                            highest = newerCardList.get(j).getNumber();

                    }
                }
                cardsResults.setItThisType(true);
                cardsResults.setHighCard(highest);
                return cardsResults;

            }
        }
        return cardsResults;
    }
//    public static CardsResults checkThreeOfAKind(List<Cards> myCards){
//        List <Cards> newerCardList = new ArrayList<>();
//
//        CardsResults cardsResults = new CardsResults();
//        cardsResults.setItThisType(false);
//        for (int check1=0 ; check1<myCards.size();check1++){
//            if(myCards.get(check1).getNumber()==1){
//                myCards.get(check1).setNumber(14);
//            }
//        }
//        newerCardList.addAll(myCards);
//        Collections.sort(newerCardList);
//
//        for(int i=0 ; i<5 ; i++){
//            if(newerCardList.get(i).getNumber()==newerCardList.get(i+1).getNumber()&&newerCardList.get(i).getNumber()==newerCardList.get(i+2).getNumber()){
//                int highest = 0;
//
//                for(int j=0 ; j<newerCardList.size() ; j++){
//                    if(newerCardList.get(j).getNumber()!=newerCardList.get(i).getNumber()){
//                        if(newerCardList.get(j).getNumber()>highest)
//                            highest = newerCardList.get(j).getNumber();
//
//                    }
//                }
//                cardsResults.setItThisType(true);
//                cardsResults.setHighCard(highest);
//                return cardsResults;
//
//            }
//        }
//        return cardsResults;
//    }
}
