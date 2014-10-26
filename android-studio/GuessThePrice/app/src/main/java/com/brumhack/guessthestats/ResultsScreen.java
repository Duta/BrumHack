package com.brumhack.guessthestats;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ResultsScreen extends Activity {
    private static final int MAX_SCORES = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);
        getActionBar().hide();

        Bundle b = getIntent().getExtras();
        int mode = b.getInt("mode");
        int score = b.getInt("finalScore");

        String hsFilePath = "mode-" + mode + "-hiscores";

        try {
            File hsFile = new File(getFilesDir(), hsFilePath);
            hsFile.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(hsFile));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            String rawScoresText = sb.toString().replaceAll("\\s+", "");
            List<Integer> scores = new ArrayList<Integer>();
            if(!"".equals(rawScoresText)) {
                for (String rawScore : rawScoresText.split(",")) {
                    scores.add(Integer.parseInt(rawScore));
                }
            }

            int insertionIndex = scores.size();
            for(int i = 0; i < scores.size(); i++) {
                if(scores.get(i) < score) {
                    insertionIndex = i;
                    break;
                }
            }
            scores.add(insertionIndex, score);
            while(scores.size() > MAX_SCORES) {
                scores.remove(scores.size() - 1);
            }

            LinearLayout scoresLayout = (LinearLayout)findViewById(R.id.scores);
            for(Integer hiscore : scores) {
                TextView scoreView = new TextView(this);
                scoreView.setText("" + hiscore);
                scoreView.setTextSize(getResources().getDimension(R.dimen.highscores_entry_size));
                scoreView.setTextColor(getResources().getColor(R.color.foreground_color));
                scoreView.setGravity(Gravity.CENTER);
                scoresLayout.addView(scoreView);
            }

            sb = new StringBuilder();
            boolean prependComma = false;
            for(Integer hiscore : scores) {
                if(prependComma) {
                    sb.append(",");
                }
                sb.append(hiscore);
                prependComma = true;
            }

            FileOutputStream os = openFileOutput(hsFilePath, Context.MODE_PRIVATE);
            os.write(sb.toString().getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
