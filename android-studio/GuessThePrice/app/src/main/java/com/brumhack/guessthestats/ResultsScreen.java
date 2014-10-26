package com.brumhack.guessthestats;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class ResultsScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);
        getActionBar().hide();

        Bundle b = getIntent().getExtras();
        int mode = b.getInt("mode");
        int score = b.getInt("finalScore");

//        String hsFilePath = "mode-" + mode + "-hiscores";
//
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(hsFilePath)));
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//            String[] scores = sb.toString().replaceAll("\\s+", "").split(",");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        TextView scoreText = (TextView)findViewById(R.id.result);
        scoreText.setText("" + score);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.results_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
