package com.example.stevenyang.texaspokerproject;

import android.animation.Animator;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    List<Cards> player1 = new LinkedList();
    List<Cards> player2 = new LinkedList();
    List<Cards> deck = new LinkedList();
    List<Cards> disposedCards = new ArrayList();
    Animation scaleAnimation;
    RelativeLayout baserlay;
    ImageView player1_1, player2_1, player1_2, player2_2, deck1, deck2, deck3, deck4, deck5;
    Random ran = new Random();
    Button btnCall, btnRaise, btnLook , refresh;
    //    int heightPixel;
//    int widthPixel;
//    float dpHeight;
    int step = 0;

    //    float dpWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1.clear();
        player2.clear();
        deck.clear();

        player1 = giveCardsToPlayer(2);
        player2 = giveCardsToPlayer(2);
        deck = giveCardsToPlayer(5);

        initView();

//        for (int i = 0; i < player1.size(); i++) {
//            Log.i("player 1 cards", "color:" + player1.get(i).getColor() + " num:" + player1.get(i).getNumber());
//        }
//        for (int i = 0; i < player2.size(); i++) {
//            Log.i("player 2 cards", "color:" + player2.get(i).getColor() + " num:" + player2.get(i).getNumber());
//        }
//        for (int i = 0; i < deck.size(); i++) {
//            Log.i("deck cards", "color:" + deck.get(i).getColor() + " num:" + deck.get(i).getNumber());
//        }
//        for (int i = 0; i < disposedCards.size(); i++) {
//            Log.i("disposedCards cards", "color:" + disposedCards.get(i).getColor() + " num:" + disposedCards.get(i).getNumber());
//        }
    }

    private void initView() {
        btnCall = (Button) this.findViewById(R.id.btnCall);
        btnLook = (Button) this.findViewById(R.id.btnLook);
        refresh = (Button)this.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             recreate();
            }
        });
//        btnLook.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
//                    showCard(player1_1,player1.get(0));
//                    showCard(player1_2,player1.get(1));
//                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
//                    closeCard(player1_1);
//                    closeCard(player1_2);
//                }
//                return true;
//        }});
        btnLook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showCard(player1_1, player1.get(0));
                showCard(player1_2, player1.get(1));
                showCard(player2_1, player2.get(0));
                showCard(player2_2, player2.get(1));

//                Cards cards1 = new Cards();
//                cards1.setColor(0);
//                cards1.setNumber(1);
//                Cards cards2 = new Cards();
//                cards2.setColor(1);
//                cards2.setNumber(1);
//                Cards cards3 = new Cards();
//                cards3.setColor(2);
//                cards3.setNumber(1);
//                Cards cards4 = new Cards();
//                cards4.setColor(3);
//                cards4.setNumber(1);
//                deck.set(0,cards1);
//                deck.set(1,cards2);
//                deck.set(2,cards3);
//                deck.set(3,cards4);

                showCard(deck1, deck.get(0));
                showCard(deck2, deck.get(1));
                showCard(deck3, deck.get(2));
                showCard(deck4, deck.get(3));
                showCard(deck5, deck.get(4));


                player1.addAll(deck);
//
//                for (Cards cards : player1) {
//                    Log.i("算之前card" , cards.getNumber()+"");
//                }
                CardsResults cardsResults =CheckCardFuncUtil.checkThreeOfAKind(player1);
                if(cardsResults.isItThisType){
                    Log.i("最大 :",cardsResults.getHighCard()+"");
                    Log.i("是否",cardsResults.isItThisType+"");
//                    for(int i=0 ; i<cardsResults.getResultLeftCards().size();i++){
//                        Log.i("同花東西" , cardsResults.getResultLeftCards().get(i).getNumber()+"");
//                    }

                }
            }
        });
        btnRaise = (Button) this.findViewById(R.id.btnRaise);
        player1_1 = (ImageView) this.findViewById(R.id.player1_1);
        baserlay = (RelativeLayout) this.findViewById(R.id.base_rlay);
        player2_1 = (ImageView) this.findViewById(R.id.player2_1);
        player1_2 = (ImageView) this.findViewById(R.id.player1_2);
        player2_2 = (ImageView) this.findViewById(R.id.player2_2);
        deck1 = (ImageView) this.findViewById(R.id.deck1);
        deck2 = (ImageView) this.findViewById(R.id.deck2);
        deck3 = (ImageView) this.findViewById(R.id.deck3);
        deck4 = (ImageView) this.findViewById(R.id.deck4);
        deck5 = (ImageView) this.findViewById(R.id.deck5);

        startAnim();

    }

    private void startAnim() {

        // 600 and 250 and 900
        player1_1.animate().translationX(-800).translationY(800).setDuration(1000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                player2_1.animate().translationX(-800).translationY(-800).setDuration(1000).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        player1_2.animate().translationX(-325).translationY(800).setDuration(1000).setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                player2_2.animate().translationX(-325).translationY(-800).setDuration(1000).setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animator) {
                                        goDeckGo();
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animator) {

                                    }
                                });
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private void goDeckGo() {
        deck1.animate().translationX(-1100).setDuration(1000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        deck2.animate().translationX(-850).setDuration(1000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        deck3.animate().translationX(-600).setDuration(1000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        deck4.animate().translationX(-350).setDuration(1000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        deck5.animate().translationX(-100).setDuration(1000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
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
    private void closeCard(ImageView view ){
        view.setImageResource(R.drawable.back);
    }
    private void logicCalculation(){

    }
    private void showCard(ImageView view,Cards card){
       int color = card.getColor();
        int number = card.getNumber();
        if(color==0){
            switch (number){
                case 1:
                    view.setImageResource(R.drawable.ace_of_clubs);
                    break;
                case 2:
                    view.setImageResource(R.drawable.club_two);
                    break;
                case 3:
                    view.setImageResource(R.drawable.clubs_three);
                    break;
                case 4:
                    view.setImageResource(R.drawable.clubs_four);
                    break;
                case 5:
                    view.setImageResource(R.drawable.clubs_five);
                    break;
                case 6:
                    view.setImageResource(R.drawable.clubs_six);
                    break;
                case 7:
                    view.setImageResource(R.drawable.clubs_seven);
                    break;
                case 8:
                    view.setImageResource(R.drawable.clubs_eight);
                    break;
                case 9:
                    view.setImageResource(R.drawable.clubs_ten);
                    break;
                case 10:
                    view.setImageResource(R.drawable.clubs_ten);
                    break;
                case 11:
                    view.setImageResource(R.drawable.jack_of_clubs);
                    break;
                case 12:
                    view.setImageResource(R.drawable.queen_of_clubs);
                    break;
                case 13:
                    view.setImageResource(R.drawable.king_of_clubs);
                    break;


            }
        } else if(color==1){
            switch (number){
                case 1:
                    view.setImageResource(R.drawable.ace_of_diamonds);
                    break;
                case 2:
                    view.setImageResource(R.drawable.diamond_two);
                    break;
                case 3:
                    view.setImageResource(R.drawable.diamonds_three);
                    break;
                case 4:
                    view.setImageResource(R.drawable.diamonds_four);
                    break;
                case 5:
                    view.setImageResource(R.drawable.diamonds_five);
                    break;
                case 6:
                    view.setImageResource(R.drawable.diamonds_six);
                    break;
                case 7:
                    view.setImageResource(R.drawable.diamonds_seven);
                    break;
                case 8:
                    view.setImageResource(R.drawable.diamonds_eight);
                    break;
                case 9:
                    view.setImageResource(R.drawable.diamonds_nine);
                    break;
                case 10:
                    view.setImageResource(R.drawable.diamonds_ten);
                    break;
                case 11:
                    view.setImageResource(R.drawable.jack_of_diamonds);
                    break;
                case 12:
                    view.setImageResource(R.drawable.queen_of_diamonds);
                    break;
                case 13:
                    view.setImageResource(R.drawable.king_of_diamonds);
                    break;


            }
        }else if(color==2) {
            switch (number) {
                case 1:
                    view.setImageResource(R.drawable.ace_of_hearts);
                    break;
                case 2:
                    view.setImageResource(R.drawable.hearts_two);
                    break;
                case 3:
                    view.setImageResource(R.drawable.hearts_three);
                    break;
                case 4:
                    view.setImageResource(R.drawable.hearts_four);
                    break;
                case 5:
                    view.setImageResource(R.drawable.hearts_five);
                    break;
                case 6:
                    view.setImageResource(R.drawable.hearts_six);
                    break;
                case 7:
                    view.setImageResource(R.drawable.hearts_seven);
                    break;
                case 8:
                    view.setImageResource(R.drawable.hearts_eight);
                    break;
                case 9:
                    view.setImageResource(R.drawable.hearts_nine);
                    break;
                case 10:
                    view.setImageResource(R.drawable.hearts_ten);
                    break;
                case 11:
                    view.setImageResource(R.drawable.jack_of_hearts);
                    break;
                case 12:
                    view.setImageResource(R.drawable.queen_of_hearts);
                    break;
                case 13:
                    view.setImageResource(R.drawable.king_of_clubs);
                    break;


            }
        }else if(color==3) {
            switch (number) {
                case 1:
                    view.setImageResource(R.drawable.ace_of_spades);
                    break;
                case 2:
                    view.setImageResource(R.drawable.spades_two);
                    break;
                case 3:
                    view.setImageResource(R.drawable.spades_three);
                    break;
                case 4:
                    view.setImageResource(R.drawable.spades_four);
                    break;
                case 5:
                    view.setImageResource(R.drawable.spades_five);
                    break;
                case 6:
                    view.setImageResource(R.drawable.spades_six);
                    break;
                case 7:
                    view.setImageResource(R.drawable.spades_seven);
                    break;
                case 8:
                    view.setImageResource(R.drawable.spades_eight);
                    break;
                case 9:
                    view.setImageResource(R.drawable.spades_nine);
                    break;
                case 10:
                    view.setImageResource(R.drawable.spades_ten);
                    break;
                case 11:
                    view.setImageResource(R.drawable.jack_of_spades);
                    break;
                case 12:
                    view.setImageResource(R.drawable.queen_of_spades);
                    break;
                case 13:
                    view.setImageResource(R.drawable.king_of_spades);
                    break;


            }
        }
    }

}

