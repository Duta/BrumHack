package com.brumhack.guessthestats;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;


public class Mode2Screen extends Activity {
    private static final int NUM_ROUNDS = 10;
    private static final int NUM_QUESTIONS = 4;
    private static final int MAX_SCORE = 100;
    private static Random rgen = new Random();
    private int roundsCompleted;
    private int currentScore;
    private List<Question> questions;
    private TableRow selectedRow;
    private Question selectedQuestion;
    private int correctIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode2_screen);
        getActionBar().hide();

        Bundle b = getIntent().getExtras();
        if(b != null) {
            roundsCompleted = b.getInt("rounds");
            currentScore = b.getInt("results");
        }

        TextView scoreText = (TextView) findViewById(R.id.score2);
        scoreText.setText(getString(R.string.current_score_prefix) + " " + currentScore);

        questions = QuestionsGenerator.getInstance().getRandomQuestions(this, NUM_QUESTIONS);
        correctIndex = rgen.nextInt(questions.size());

        TextView valueText = (TextView)findViewById(R.id.value);
        valueText.setText("" + questions.get(correctIndex).getValue());

        TableLayout tableLayout = (TableLayout)findViewById(R.id.statsTableLayout);

        for(final Question question : questions) {
            System.out.println("Adding: " + question);
            float imageWidth = getResources().getDimension(R.dimen.mode2OptionImageWidth);
            float imageHeight = getResources().getDimension(R.dimen.mode2OptionImageHeight);
            ImageView countryImage = new ImageView(this);
            countryImage.setMaxWidth((int) imageWidth);
            countryImage.setMinimumWidth((int) imageWidth);
            countryImage.setMaxHeight((int) imageHeight);
            countryImage.setMinimumHeight((int)imageHeight);
            try {
                InputStream is = getAssets().open(question.getCountry() + ".png");
                countryImage.setImageDrawable(Drawable.createFromStream(is, null));
            } catch (IOException ex) {
                countryImage.setImageResource(R.drawable.thatguy);
            }

            TextView countryName = new TextView(this);
            countryName.setText(question.getCountryPretty());
            countryName.setTextColor(getResources().getColor(R.color.foreground_color));

            TextView descriptionView = new TextView(this);
            descriptionView.setText(question.getDescription());
            countryName.setTextColor(getResources().getColor(R.color.foreground_color));

            final TableRow questionRow = new TableRow(this);
            questionRow.addView(countryImage);
            questionRow.addView(countryName);
            questionRow.addView(descriptionView);
            questionRow.setGravity(Gravity.CENTER);
            questionRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(selectedRow != null) {
                        selectedRow.setBackgroundResource(R.color.background_color);
                    }
                    selectedQuestion = question;
                    selectedRow = questionRow;
                    selectedRow.setBackgroundResource(R.color.white_overlay);
                }
            });

            tableLayout.addView(questionRow);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    public void guess2(View view) {
        if(selectedQuestion == null) return;
        double given = selectedQuestion.getValue();
        int score;
        double correct = questions.get(correctIndex).getValue();
        if ((given >= 0) && (given <= (2 * correct))) {
            double cosValue = Math.cos(given / (correct / Math.PI));
            score = (int) Math.round((MAX_SCORE / 2) - (MAX_SCORE / 2) * cosValue);
        } else {
            score = 0;
        }

        currentScore += score;

        TextView scoreText = (TextView) findViewById(R.id.score2);
        scoreText.setText(getString(R.string.current_score_prefix) + " " + currentScore);

        Button button = (Button) findViewById(R.id.guessButton2);
        button.setText("Next");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (roundsCompleted + 1 < NUM_ROUNDS) {
                    intent = new Intent(Mode2Screen.this, Mode2Screen.class);
                    Bundle b = new Bundle();
                    b.putInt("rounds", roundsCompleted + 1);
                    b.putInt("results", currentScore);
                    intent.putExtras(b);
                } else {
                    intent = new Intent(Mode2Screen.this, ResultsScreen.class);
                    Bundle b = new Bundle();
                    b.putInt("finalScore", currentScore);
                    b.putInt("mode", 2);
                    intent.putExtras(b);
                }
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        TableLayout tableLayout = (TableLayout)findViewById(R.id.statsTableLayout);
        tableLayout.getChildAt(correctIndex).setBackgroundResource(R.color.green_color);

        TextView addedPointsView = (TextView) findViewById(R.id.addedPoints2);
        addedPointsView.setText(score + " " + getString(R.string.points_suffix));
        addedPointsView.setVisibility(View.VISIBLE);

        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView2);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
