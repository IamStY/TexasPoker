package com.example.stevenyang.texaspokerproject;

import android.animation.Animator;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<Cards> player1 = new LinkedList();
    List<Cards> player2 = new LinkedList();
    List<Cards> deck = new LinkedList();
    List<Cards> disposedCards = new ArrayList();
    RelativeLayout baserlay, rlay_player_2_win, rlay_player_1_win;
    ImageView player1_1, player2_1, player1_2, player2_2, deck1, deck2, deck3, deck4, deck5;
    Random ran = new Random();
    Button btnCall, btnRaise, btnLook, refresh, draw;
    TextView txt_player_1_win, txt_player_2_win, remainingCash1, remainingCash2;
    SeekBar moneyBar;
    int player1Money = 3000;
    int player2Money = 3000;
    int unit = 100;
    int player1Bet = 0;
    int player1TotalInPool;
    int player2Bet = 0;
    int player2TotalInPool;
    int tableBet = 0;
    boolean reraiseDirectlyNextStep = false;

    //    int heightPixel;
//    int widthPixel;
//    float dpHeight;
    int step = 0;

    //    float dpWidth;
    int width;
    int height;

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
        remainingCash1 = (TextView) this.findViewById(R.id.txt_remaining_cash1);
        remainingCash2 = (TextView) this.findViewById(R.id.txt_remaining_cash2);
        remainingCash1.setText(player1Money + "");
        remainingCash2.setText(player2Money + "");
        btnCall = (Button) this.findViewById(R.id.btnCall);

        btnLook = (Button) this.findViewById(R.id.btnLook);
        refresh = (Button) this.findViewById(R.id.refresh);
        moneyBar = (SeekBar) this.findViewById(R.id.moneyBar);
        moneyBar.setProgress(0);
        moneyBar.setMax(player1Money / unit);
        moneyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                btnCall.setText("下注 : " + progress * unit);
//set minimum
//                moneyBar.setProgress(15);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnRaise = (Button) this.findViewById(R.id.btnRaise);
        btnRaise.setOnClickListener(this);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        draw = (Button) this.findViewById(R.id.btn_draw);
        rlay_player_1_win = (RelativeLayout) this.findViewById(R.id.rlay_player_1_win);
        rlay_player_2_win = (RelativeLayout) this.findViewById(R.id.rlay_player_2_win);
        txt_player_1_win = (TextView) this.findViewById(R.id.txt_player_1_win);
        txt_player_2_win = (TextView) this.findViewById(R.id.txt_player_2_win);
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw.setVisibility(View.INVISIBLE);
                drawCards();
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
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;


    }

    private void drawCards() {
        startAnim();
    }

    private void startAnim() {
        player1.addAll(deck);
        player2.addAll(deck);

        Log.i("screenHeight", height + "");
        Log.i("screenWidth", width + "");
        Log.i("view bottom top diff", (deck1.getBottom() - deck1.getTop() + ""));
//        Log.i("he",(deck1.getBottom()-deck1.getTop()+""));
        final int firstLeft = -width * 2 / 3 + (deck1.getRight() - deck1.getLeft());
        final int heightPosition = height / 2 - (deck1.getBottom() - deck1.getTop()) - 120;
        // 600 and 250 and 900
        player1_1.animate().translationX(-width * 2 / 3).translationY(heightPosition).setDuration(1000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                player2_1.animate().translationX(-width * 2 / 3).translationY(-heightPosition).setDuration(1000).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        player1_2.animate().translationX(-width / 3 + (deck1.getRight() - deck1.getLeft())).translationY(heightPosition).setDuration(1000).setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                player2_2.animate().translationX(-width / 3 + (deck1.getRight() - deck1.getLeft())).translationY(-heightPosition).setDuration(1000).setListener(new Animator.AnimatorListener() {
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
        Log.i("firs deck pos", (-width * 5 / 6 + (deck1.getRight() - deck1.getLeft())) + "");
        deck1.animate().translationX(-width * 9 / 10 + (deck1.getRight() - deck1.getLeft()) / 2).setDuration(1000).setListener(new Animator.AnimatorListener() {
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
        deck2.animate().translationX(-width * 7 / 10 + (deck1.getRight() - deck1.getLeft()) / 2).setDuration(1000).setListener(new Animator.AnimatorListener() {
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
        deck3.animate().translationX(-width * 5 / 10 + (deck1.getRight() - deck1.getLeft()) / 2).setDuration(1000).setListener(new Animator.AnimatorListener() {
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
        deck4.animate().translationX(-width * 3 / 10 + (deck1.getRight() - deck1.getLeft()) / 2).setDuration(1000).setListener(new Animator.AnimatorListener() {
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
        deck5.animate().translationX(-width * 1 / 10 + (deck1.getRight() - deck1.getLeft()) / 2).setDuration(1000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                btnCall.setOnClickListener(MainActivity.this);
                btnLook.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            showCard(player1_1, player1.get(0));
                            showCard(player1_2, player1.get(1));
                        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            closeCard(player1_1);
                            closeCard(player1_2);
                        }
                        return true;
                    }
                });

                btnLook.setOnClickListener(MainActivity.this);
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

        while (tempCards.size() < amount) {
            Cards card = createCards();
            boolean giveCard = true;
            for (int i = 0; i < disposedCards.size(); i++) {
                if (disposedCards.get(i).getColor() == card.getColor() && disposedCards.get(i).getNumber() == card.getNumber()) {
                    giveCard = false;
                    break;
                }

            }
            if (giveCard) {
                tempCards.add(card);
                disposedCards.add(card);
            }

        }
        return tempCards;

    }

    private Cards createCards() {
        int randNum = ran.nextInt(13) + 1;
        int randColor = ran.nextInt(3);
        Cards cards = new Cards();
        cards.setColor(randColor);
        cards.setNumber(randNum);
        return cards;
    }

    private void closeCard(ImageView view) {
        view.setImageResource(R.drawable.back);
    }

    private void logicCalculation() {

    }

    private void showCard(ImageView view, Cards card) {
        int color = card.getColor();
        int number = card.getNumber();
        if (color == 0) {
            switch (number) {
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
                    view.setImageResource(R.drawable.clubs_nine);
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
        } else if (color == 1) {
            switch (number) {
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
        } else if (color == 2) {
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
                    view.setImageResource(R.drawable.king_of_hearts);
                    break;


            }
        } else if (color == 3) {
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

    @Override
    public void onClick(View v) {
        if (v == btnRaise) {
            if (moneyBar.getVisibility() == View.VISIBLE) {
                moneyBar.setVisibility(View.INVISIBLE);
            } else {
                moneyBar.setVisibility(View.VISIBLE);
            }
        } else if (v == btnCall) {

            switch (step) {
                case 0:
                    if (betMovementEnhancement() == true) {

                        initBet();
                        step++;
                        showCardTotalFunction(step);

                    }
                    setRemaingCash();

                    break;
                case 1:
//                    showCard(deck4, deck.get(3));
                    if (betMovementEnhancement() == true) {
                        initBet();
                        step++;
                        showCardTotalFunction(step);
                    }
                    setRemaingCash();

                    break;
                case 2:
//                    showCard(deck5, deck.get(4));
                    if (betMovementEnhancement() == true) {
                        initBet();
                        step++;
                        showCardTotalFunction(step);
                    }
                    setRemaingCash();

                    break;
                case 3:

                    if (betMovementEnhancement() == true) {
                        initBet();
                        step++;
                        showCardTotalFunction(step);
                        FinalResults finalResults = CheckCardFuncUtil.compair(player1, player2);
                        Log.e("finalResults", finalResults.getResultString());
//                            Toast.makeText(getApplicationContext(), finalResults.getResultString(), Toast.LENGTH_LONG).show();
                        StringBuilder strb = new StringBuilder();
                        strb.append(txt_player_1_win.getText() + "\n");

                        strb.append(finalResults.getResultString());

                        if (finalResults.getFlag() == 1) {
                            rlay_player_1_win.setVisibility(View.VISIBLE);
                            txt_player_1_win.setText(strb.toString());
                        } else {
                            rlay_player_2_win.setVisibility(View.VISIBLE);
                            txt_player_2_win.setText(strb.toString());
                        }
                    }
                    setRemaingCash();

                    break;
            }

            //debug field
//                Cards cards1 = new Cards();
//                cards1.setColor(0);
//                cards1.setNumber(6);
//                Cards cards2 = new Cards();
//                cards2.setColor(2);
//                cards2.setNumber(4);
//               Cards cards3 = new Cards();
//                cards3.setColor(0);
//
//                cards3.setNumber(9);
//                Cards cards4 = new Cards();
//                cards4.setColor(1);
//
//                cards4.setNumber(8);
//                Cards cards5= new Cards();
//                cards5.setColor(0);
//                cards5.setNumber(7);
//                deck.set(0,cards1);
//                deck.set(1,cards2);
//                deck.set(2,cards3);
//                deck.set(3,cards4);
//                 deck.set(4,cards5);
//                    Cards cards6= new Cards();
//                    cards6.setColor(1);
//                    cards6.setNumber(7);
//
//                    Cards cards7= new Cards();
//                    cards7.setColor(1);
//                    cards7.setNumber(10);
//                    player1.set(0,cards6);
//                            player1.set(1,cards7);
            //debug field


//
//                showCard(player1_1, player1.get(0));
//                showCard(player1_2, player1.get(1));
//                showCard(player2_1, player2.get(0));
//                showCard(player2_2, player2.get(1));


        }
//        else if(v==btnLook){
//
//
//
//        }
    }

    private void setRemaingCash() {
        remainingCash1.setText(player1Money + "");
        remainingCash2.setText(player2Money + "");
    }

    private void initBet() {
        reraiseDirectlyNextStep = false;
        tableBet = 0;
        player1Bet = 0;
        player1TotalInPool = 0;
        player2Bet = 0;
        player2TotalInPool = 0;
        moneyBar.setProgress(0);
        moneyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                btnCall.setText("下注 : " + progress * unit);
//set minimum
//                moneyBar.setProgress(15);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void showCardTotalFunction(int step) {
        switch (step) {
            case 1:

                showCard(deck1, deck.get(0));
                showCard(deck2, deck.get(1));
                showCard(deck3, deck.get(2));
                break;
            case 2:
                showCard(deck4, deck.get(3));
                break;
            case 3:
                showCard(deck5, deck.get(4));
                break;
            case 4:
                btnLook.setOnTouchListener(null);
                showCard(player2_1, player2.get(0));
                showCard(player2_2, player2.get(1));
                showCard(player1_1, player1.get(0));
                showCard(player1_2, player1.get(1));
                break;
        }
    }


    private boolean betMovementEnhancement() {
        player1Bet = moneyBar.getProgress() * unit;

        Log.e("player1Bet", player1Bet + "");
        Log.e("player1TotalInPool", player1TotalInPool + "");
        Log.e("tableBet", tableBet + "");

        if (player1Bet + player1TotalInPool >= tableBet) {
            //取得我的下注


            //扣掉玩家的現金
            player1Money -= player1Bet;
            //設置目前桌上Bet
            tableBet = player1TotalInPool + player1Bet;

            player1TotalInPool += player1Bet;


            Log.i("currentTableBet", tableBet + "");
            refreshMoneyBar();
            //電腦判斷跟還是reraise
            Random random = new Random();
            if (player1Bet == 0 || player2TotalInPool != player1TotalInPool) {
                if (player2Money != 0 || player1Money != 0) {

                    int computerRandomBet = (random.nextInt((player2Money + unit) / unit)) * unit;
                    Log.i("電腦開始隨機", "電腦開始隨機:" + computerRandomBet + " player2Money " + player2Money);
                    if (computerRandomBet <= tableBet) {
                        //跟

                        player2Bet = tableBet;
                        player2Money = player2Money - (player2Bet - player2TotalInPool);

                        Log.e("跟上後電腦池中", player2Bet + "");
                        Log.e("跟上後電腦池中", player2TotalInPool + "");
                        Log.e("跟上後電腦池中", player2TotalInPool + "");
                        return true;
                    } else {
                        //電腦re-raise

                        tableBet = computerRandomBet;
                        player2TotalInPool += tableBet;
                        player2Money -= tableBet;

                        //玩家必須跟上
                        moneyBar.setProgress((tableBet - player1TotalInPool) / unit);
                        moneyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                if (progress == 0) {
                                    btnCall.setText("Check");
                                } else if (((tableBet - player1TotalInPool) / unit) < progress) {
                                    btnCall.setText("re-raise : " + progress * unit);

                                } else {
                                    btnCall.setText("跟注 : " + progress * unit);
                                }
                                //set minimum
                                if (((tableBet - player1TotalInPool) / unit) >= progress) {
                                    moneyBar.setProgress((tableBet - player1TotalInPool) / unit);
                                } else {

                                }

                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });
                        Log.e("隨機後電腦池中", player2TotalInPool + "");
                        return false;
                    }
                } else {

                    //沒錢啦!  別那邊反raise了
                    return true;
                }
            } else {
                //玩家跟上
                return true;
            }
        } else if (player1Bet + player1TotalInPool == tableBet) {
            //取得我的下注

            //設置目前桌上Bet
            tableBet = player1Bet;
            //扣掉玩家的現金
            player1Money -= player1Bet;

            player1TotalInPool += player1Bet;

            refreshMoneyBar();
            return true;

        } else {
            Toast.makeText(getApplicationContext(), "數目異常", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void refreshMoneyBar() {
        moneyBar.setMax((player1Money) / unit);
    }
}

