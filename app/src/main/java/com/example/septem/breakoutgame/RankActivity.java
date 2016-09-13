package com.example.septem.breakoutgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

/**
 * Rank Activity, written by Haowei Wang on 5/1/16.
 */
public class RankActivity extends Activity{

    // Min Heap to store every player and score.
    PriorityQueue<PlayerAndScore> minHeap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        Intent intent = getIntent();

        // Set the heap's initial size to 10 at the beginning.
        minHeap = new PriorityQueue<>(10, new ScoreComparator());
        // Read the file
        read();
        // Determine if user entered his name before jump into this activity.
        if (intent.hasExtra("totalscore") && intent.hasExtra("name")) {
            int score = intent.getIntExtra("totalscore", -1);
            String name = intent.getStringExtra("name");
            // If the size of heap if not enough to 10, or the top element is less than the score.
            if (minHeap.size() == 10 && minHeap.peek().getScore() < score) {
                minHeap.poll();
            }
            // Put the new player's name and score to heap.
            minHeap.offer(new PlayerAndScore(name, score));
        }
        // Write to file
        write(minHeap);
        int size = minHeap.size();
        // Show every player's name and score based on the size of heap.
        while(size > 0) {
            switch (size) {
                case 1: {
                    TextView nameText = (TextView) findViewById(R.id.name_1);
                    TextView scoreText = (TextView) findViewById(R.id.score_1);
                    nameText.append(minHeap.peek().getPlayer());
                    scoreText.append(minHeap.poll().getScore() + "");
                    break;
                }
                case 2: {
                    TextView nameText = (TextView) findViewById(R.id.name_2);
                    TextView scoreText = (TextView) findViewById(R.id.score_2);
                    nameText.append(minHeap.peek().getPlayer());
                    scoreText.append(minHeap.poll().getScore() + "");
                    break;
                }
                case 3: {
                    TextView nameText = (TextView) findViewById(R.id.name_3);
                    TextView scoreText = (TextView) findViewById(R.id.score_3);
                    nameText.append(minHeap.peek().getPlayer());
                    scoreText.append(minHeap.poll().getScore() + "");
                    break;
                }
                case 4: {
                    TextView nameText = (TextView) findViewById(R.id.name_4);
                    TextView scoreText = (TextView) findViewById(R.id.score_4);
                    nameText.append(minHeap.peek().getPlayer());
                    scoreText.append(minHeap.poll().getScore() + "");
                    break;
                }
                case 5: {
                    TextView nameText = (TextView) findViewById(R.id.name_5);
                    TextView scoreText = (TextView) findViewById(R.id.score_5);
                    nameText.append(minHeap.peek().getPlayer());
                    scoreText.append(minHeap.poll().getScore() + "");
                    break;
                }
                case 6: {
                    TextView nameText = (TextView) findViewById(R.id.name_6);
                    TextView scoreText = (TextView) findViewById(R.id.score_6);
                    nameText.append(minHeap.peek().getPlayer());
                    scoreText.append(minHeap.poll().getScore() + "");
                    break;
                }
                case 7: {
                    TextView nameText = (TextView) findViewById(R.id.name_7);
                    TextView scoreText = (TextView) findViewById(R.id.score_7);
                    nameText.append(minHeap.peek().getPlayer());
                    scoreText.append(minHeap.poll().getScore() + "");
                    break;
                }
                case 8: {
                    TextView nameText = (TextView) findViewById(R.id.name_8);
                    TextView scoreText = (TextView) findViewById(R.id.score_8);
                    nameText.append(minHeap.peek().getPlayer());
                    scoreText.append(minHeap.poll().getScore() + "");
                    break;
                }
                case 9: {
                    TextView nameText = (TextView) findViewById(R.id.name_9);
                    TextView scoreText = (TextView) findViewById(R.id.score_9);
                    nameText.append(minHeap.peek().getPlayer());
                    scoreText.append(minHeap.poll().getScore() + "");
                    break;
                }
                case 10: {
                    TextView nameText = (TextView) findViewById(R.id.name_10);
                    TextView scoreText = (TextView) findViewById(R.id.score_10);
                    nameText.append(minHeap.peek().getPlayer());
                    scoreText.append(minHeap.poll().getScore() + "");
                    break;
                }
                default: {
                    break;
                }
            }
            size--;
        }
        //write(minHeap);
        Button restartButton = (Button) findViewById(R.id.restart_button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(RankActivity.this, MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        });
    }

    /* Read function to read player name and score in file */
    public void read() {
        FileInputStream in;
        BufferedReader reader = null;
        try {
            in = openFileInput("rank"); // The file name is "rank"
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                minHeap.offer(new PlayerAndScore(data[0], Integer.parseInt(data[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*Write function to store every player's name and score into the file*/
    public void write(PriorityQueue<PlayerAndScore> list) {
        FileOutputStream out;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("rank", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            for(PlayerAndScore ps : list) {
                writer.write(ps.getPlayer() + "\t" + ps.getScore() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
