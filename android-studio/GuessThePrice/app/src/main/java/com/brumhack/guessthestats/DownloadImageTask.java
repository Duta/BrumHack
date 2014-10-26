package com.brumhack.guessthestats;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Bertie on 25/10/2014.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView image;

    public DownloadImageTask(ImageView image) {
        this.image = image;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new URL(urlDisplay).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch(IOException e) {
            // Stuff
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        image.setImageBitmap(bitmap);
    }
}
