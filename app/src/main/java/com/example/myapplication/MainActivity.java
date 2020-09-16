package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "MainActivity";
    TextView tvOngoingMatches, tvUpcomingMatches;
    Boolean matchStarted;
    TextToSpeech tts;
    SwipeRefreshLayout str;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOngoingMatches = (TextView) findViewById(R.id.tvOngoingMatches);
        tvUpcomingMatches = (TextView) findViewById(R.id.tvUpcomingMatches);
        //tvCalender = (TextView) findViewById(R.id.tvCalender);
        str = (SwipeRefreshLayout) findViewById(R.id.str);
        tvUpcomingMatches.setOnClickListener(this);
        tvOngoingMatches.setOnClickListener(this);
        //tvCalender.setOnClickListener(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,255,255)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(MainActivity.this, "Used to give Feedback", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, FeedbackActivity.class));

            }
        });

        str.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //Toast.makeText(MainActivity.this, "Refreshing", Toast.LENGTH_SHORT).show();
                str.setRefreshing(false);
                // both the below methods are used to stop the Spinner from the activity
                str.destroyDrawingCache();
                str.clearAnimation();
                //startActivity(new Intent(MainActivity.this, MainActivity.class));
                //onCreate(null);
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tvOngoingMatches:

                matchStarted = true;

                break;
            case R.id.tvUpcomingMatches:

                matchStarted = false;

                break;

        /*    case R.id.tvCalender:

                tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {


                        int result = tts.setLanguage(Locale.FRENCH);

                        if (result == TextToSpeech.LANG_MISSING_DATA
                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e("TTS", "This Language is not supported");
                        } else {
                            //btnSpeak.setEnabled(true);
                            tts.speak("elle est petite.", TextToSpeech.QUEUE_FLUSH, null);
                            Log.d(TAG, "onInit: inside ELSE");
                        }
                    }
                });

                *//*tts.speak("saksham", TextToSpeech.QUEUE_ADD, null);*//*
                break;*/
        }

        Intent i = new Intent(this, LiveMatchActivity.class);
        i.putExtra("matchStarted", matchStarted);
        startActivity(i);


    }
}