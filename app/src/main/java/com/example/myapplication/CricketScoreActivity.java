package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class CricketScoreActivity extends AppCompatActivity {
    String unique_key;
    RecyclerView rv;
    public static final String TAG = "CricketScoreActivity";
    Boolean matchStarted;
    CricketScoreAdapter adapter;
    ImageView ivAnim;
    ProgressBar pbScore;
    SwipeRefreshLayout str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket_score);

        Log.d(TAG, "onCreate: unique_key -> " + unique_key);
        Intent i = getIntent();

        if (i != null) {

            unique_key = i.getStringExtra("unique_id");
            matchStarted = i.getBooleanExtra("matchStarted", false);
            Log.d(TAG, "onCreate: inside IF");

        }

        MySensorManager.accelSensorActivate(this, new MySensorManager.OnEventListener() {
            @Override
            public void setOnEventListener() {

                Log.d(TAG, "FROM CRICKET SCORE");
                finish();

            }
        });

        str = (SwipeRefreshLayout) findViewById(R.id.str);
        str.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                str.setRefreshing(false);
                str.destroyDrawingCache();
                str.clearAnimation();

            }
        });

        ivAnim = (ImageView) findViewById(R.id.ivAnim);
        ivAnim.setBackgroundResource(R.drawable.anim_items);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000);
        animator.setRemoveDuration(1000);

        ivAnim.setVisibility(View.GONE);
        pbScore = (ProgressBar) findViewById(R.id.pbScore);
        rv = (RecyclerView) findViewById(R.id.rvCricketScore);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(animator);

        StringBuilder URL = new StringBuilder();
        URL.append("http://cricapi.com/api/cricketScore?apikey=JUQuaCHs9yfYxcsT1InOJ1wOC9I3&unique_id=");
        URL.append(unique_key);

        CricketScoreTask cricketScoreTask = new CricketScoreTask(new CricketScoreTask.OnItemView() {
            @Override
            public void setOnItemView(CricketScorePOJO cricketScorePOJO) {

                ivAnim.setVisibility(View.VISIBLE);
                CricketScoreAdapter cricketScoreAdapter = new CricketScoreAdapter(cricketScorePOJO, CricketScoreActivity.this, matchStarted);
                rv.setAdapter(cricketScoreAdapter);

            }
        }, matchStarted, pbScore);

        cricketScoreTask.execute(URL.toString());

        AnimationDrawable ar = (AnimationDrawable) ivAnim.getBackground();
        ar.start();

    }
}