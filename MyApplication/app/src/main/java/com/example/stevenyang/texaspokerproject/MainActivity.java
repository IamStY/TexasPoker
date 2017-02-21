package com.example.stevenyang.texaspokerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    LinkedList<Cards> player1 = new LinkedList();
    LinkedList<Cards> player2 = new LinkedList();
    LinkedList<Cards> deck = new LinkedList();
    ArrayList<Cards> disposedCards = new ArrayList();
    Random ran = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1 = giveCardsToPlayer(2);
        player2 = giveCardsToPlayer(2);
        deck = giveCardsToPlayer(5);
        for (int i=0;i<player1.size();i++) {
            Log.i("player 1 cards" ,"color:"+player1.get(i).getColor()+" num:"+player1.get(i).getNumber());
        }
        for (int i=0;i<player2.size();i++) {
            Log.i("player 2 cards" ,"color:"+player2.get(i).getColor()+" num:"+player2.get(i).getNumber());
        }
        for (int i=0;i<deck.size();i++) {
            Log.i("deck cards" ,"color:"+deck.get(i).getColor()+" num:"+deck.get(i).getNumber());
        }
        for (int i=0;i<disposedCards.size();i++) {
            Log.i("disposedCards cards" ,"color:"+disposedCards.get(i).getColor()+" num:"+disposedCards.get(i).getNumber());
        }
    }

    private LinkedList<Cards> giveCardsToPlayer(int amount) {

        LinkedList<Cards> tempCards = new LinkedList<Cards>();

        while (tempCards.size()<amount){
           Cards card = createCards();
            boolean giveCard = true;
            for (int i=0 ; i<disposedCards.size();i++) {
                if(disposedCards.get(i).getColor()==card.getColor()&&disposedCards.get(i).getNumber()==card.getNumber()){
                    giveCard = false;
                    break;
                }

            }
            if(giveCard){
                tempCards.add(card);
                disposedCards.add(card);
            }

        }
        return    tempCards;

    }
    private Cards createCards(){
        int randNum =ran.nextInt(13)+1;
        int randColor = ran.nextInt(3);
        Cards cards = new Cards();
        cards.setColor(randColor);
        cards.setNumber(randNum);
        return cards;
    }

}

