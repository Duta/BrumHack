package com.brumhack.guessthestats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class GameModeScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_screen);
        getActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_mode_screen, menu);
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

    public void mode1Selected(View view) {
        Intent toMode1Screen = new Intent(this, Mode1Screen.class);
        toMode1Screen.setFlags(toMode1Screen.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(toMode1Screen);
    }

    public void mode2Selected(View view) {
        Intent toMode2Screen = new Intent(this, Mode2Screen.class);
        toMode2Screen.setFlags(toMode2Screen.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(toMode2Screen);
    }

    public void mode3Selected(View view) {
        Intent toMode3Screen = new Intent(this, Mode3Screen.class);
        toMode3Screen.setFlags(toMode3Screen.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(toMode3Screen);
    }

    public void settings(View view) {
        Intent toSettingsScreen = new Intent(this, SettingsScreen.class);
        startActivity(toSettingsScreen);
    }
}
