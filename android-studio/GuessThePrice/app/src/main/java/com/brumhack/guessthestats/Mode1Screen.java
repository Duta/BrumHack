package com.brumhack.guessthestats;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Mode1Screen extends Activity {
    private static final int NUM_ROUNDS = 10;
    private static final int MAX_SCORE = 100;
    private int roundsCompleted;
    private int currentScore;
    private Question question;

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

        question = QuestionsGenerator.getInstance().getRandomQuestion(this);

        ImageView countryImage = (ImageView)findViewById(R.id.countryImage);
        try {
            InputStream is = getAssets().open(question.getCountry() + ".png");
            countryImage.setImageDrawable(Drawable.createFromStream(is, null));
        } catch(IOException ex) {
            countryImage.setImageResource(R.drawable.thatguy);
        }

        TextView countryName = (TextView)findViewById(R.id.countryName);
        countryName.setText(question.getCountryPretty());

        TextView descriptionView = (TextView)findViewById(R.id.description);
        descriptionView.setText(question.getDescription());
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

    public void guess(View view) {
        TextView invalidInput = (TextView)findViewById(R.id.invalidInput);
        invalidInput.setVisibility(View.GONE);

        EditText userInput = (EditText)findViewById(R.id.userInput);
        int given;
        try {
            given = Integer.parseInt(userInput.getText().toString());
        } catch(NumberFormatException e) {
            invalidInput.setVisibility(View.VISIBLE);
            return;
        }
        int score;
        int correct = question.getValue();
        if((given >= 0) && (given <= (2*correct))) {
            double cosValue = Math.cos(given / (correct / Math.PI));
            score = (int) Math.round((MAX_SCORE / 2) - (MAX_SCORE / 2) * cosValue);
        }else{
            score = 0;
        }

        /*
        int delta = Math.abs(given - correct);
        double difficultyModifier = 75;
        double reduction = (difficultyModifier*delta)/correct;
        int score = Math.max(0, MAX_SCORE - (int)Math.round(reduction));*/

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
                    b.putInt("mode", 1);
                    intent.putExtras(b);
                }
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        TextView correctValueView = (TextView)findViewById(R.id.correctValue);
        correctValueView.setText(getString(R.string.correct_value_prefix) + " " + question.getValue());
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
