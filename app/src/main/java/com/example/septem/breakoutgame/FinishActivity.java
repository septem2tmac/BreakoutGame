package com.example.septem.breakoutgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Game over class, Written by Haowei Wang on 5/1/16.
 */
public class FinishActivity extends Activity {

    private int score;
    private float time;
    private int totalScore;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_finish);

        TextView scoreView = (TextView) findViewById(R.id.score_text);

        Intent intent = getIntent();
        // Get the score from MainActivity
        score = intent.getIntExtra("score", -1);
        // Put the score on score text view.
        scoreView.append(score + "");

        TextView timeView = (TextView) findViewById(R.id.time);
        // Get the time from MainActivity
        time = intent.getLongExtra("time", -1);
        time /= 1000; // Convert million second to second
        // Put the time on time text view.
        timeView.append(time + "s");

        TextView totalScoreView = (TextView) findViewById(R.id.totalscore_text);
        // Calculate the total score, it equals score * time
        totalScore = Math.round(score * time);
        totalScoreView.append(totalScore + "");

        // Restart Button which finishes this activity, and start Main Activity.
        Button restartButton = (Button) findViewById(R.id.restart_button);
        Button rankButoon = (Button) findViewById(R.id.rank_button);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(FinishActivity.this, MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        });

        // Rank Button which finishes this activity, and start Rank Activity.
        rankButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRankActivity = new Intent(FinishActivity.this, RankActivity.class);
                EditText name = (EditText) findViewById(R.id.name_text);
                if (name.getText() != null && name.getText().length() != 0) {
                    // Pass the total score and player name to Rank Activity.
                    intentRankActivity.putExtra("totalscore", totalScore);
                    intentRankActivity.putExtra("name", name.getText().toString());
                }
                startActivity(intentRankActivity);
                finish();
            }
        });
    }
}
