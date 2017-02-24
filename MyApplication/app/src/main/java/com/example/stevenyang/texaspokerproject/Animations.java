package com.example.stevenyang.texaspokerproject;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by User on 2017/2/21.
 */

public class Animations {
    public Animation fromAtoB(float fromX, float fromY, float toX, float toY, Animation.AnimationListener l, int speed){


        Animation fromAtoB = new TranslateAnimation(
                Animation.ABSOLUTE, //from xType
                fromX,
                Animation.ABSOLUTE, //to xType
                toX,
                Animation.ABSOLUTE, //from yType
                fromY,
                Animation.ABSOLUTE, //to yType
                toY
        );

        fromAtoB.setDuration(speed);
        fromAtoB.setInterpolator(new AccelerateDecelerateInterpolator());


        if(l != null)
            fromAtoB.setAnimationListener(l);
        return fromAtoB;
    }
}