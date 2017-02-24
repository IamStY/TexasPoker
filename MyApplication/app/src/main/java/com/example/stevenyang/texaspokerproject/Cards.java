package com.example.stevenyang.texaspokerproject;

/**
 * Created by User on 2017/2/21.
 */

public class Cards implements Comparable<Cards>  {
    int color; //0 : 梅花 1:方塊 2:愛心 3:黑桃
    int number;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Cards cards) {
        if(this.number > cards.getNumber()){return 1;}
        if(this.number < cards.getNumber()){return -1;}
        else{return 0;}
    }
}
