package com.example.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

import androidx.recyclerview.widget.RecyclerView;


public class AnimUtil {

    public static void animate(RecyclerView.ViewHolder holder, boolean goesDown) {

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator translateY = ObjectAnimator.ofFloat(holder.itemView, "translationY",  (goesDown) ? 50 : -50 , 0);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -50, 50, -40, 40, -30, 30, -20, 20, -10, 10, 0);

        translateX.setDuration(1000);
        translateY.setDuration(1000);

        animatorSet.playTogether(translateX, translateY);
        animatorSet.start();

    }


}
