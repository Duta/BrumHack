package com.brumhack.guessthestats;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;

public class Mode1Screen extends Activity {
    private static final int NUM_ROUNDS = 2;
    private int roundsCompleted;
    private int currentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode1_screen);
        getActionBar().hide();
        Bundle b = getIntent().getExtras();
        if(b != null) {
            roundsCompleted = b.getInt("rounds");
            currentScore = b.getInt("results");
        }
        TextView scoreText = (TextView)findViewById(R.id.score);
        scoreText.setText(getString(R.string.current_score_prefix) + " " + currentScore);

        ImageView countryImage = (ImageView)findViewById(R.id.countryImage);
        countryImage.setImageURI(Uri.fromFile(new File()));
//        int width = 300;
//        int height = 200;
//        new DownloadImageTask((ImageView)findViewById(R.id.countryImage))
//            .doInBackground("http://placekitty.artisan.io/" + width + "/" + height);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mode1_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    public int getScore() {
        return (int)(100*Math.random());
    }

    public void guess(View view) {
        int score = getScore();

        currentScore += score;

        TextView scoreText = (TextView)findViewById(R.id.score);
        scoreText.setText(getString(R.string.current_score_prefix) + " " + currentScore);

        Button button = (Button)findViewById(R.id.guessButton);
        button.setText("Next");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(roundsCompleted + 1 < NUM_ROUNDS) {
                    intent = new Intent(Mode1Screen.this, Mode1Screen.class);
                    Bundle b = new Bundle();
                    b.putInt("rounds", roundsCompleted + 1);
                    b.putInt("results", currentScore);
                    intent.putExtras(b);
                } else {
                    intent = new Intent(Mode1Screen.this, ResultsScreen.class);
                    Bundle b = new Bundle();
                    b.putInt("finalScore", currentScore);
                    intent.putExtras(b);
                }
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        TextView correctValueView = (TextView)findViewById(R.id.correctValue);
        correctValueView.setVisibility(View.VISIBLE);

        TextView addedPointsView = (TextView)findViewById(R.id.addedPoints);
        addedPointsView.setText(score +" "+ getString(R.string.points_suffix));
        addedPointsView.setVisibility(View.VISIBLE);

        final ScrollView scrollView = (ScrollView)findViewById(R.id.scrollView);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
