package edu.utexas.ece.pugs.grocerylist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Brandon on 4/29/2018.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bitMapImage;

    public DownloadImageTask(ImageView bitMapImage) {
        this.bitMapImage = bitMapImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap Icon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            Icon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Icon;
    }

    protected void onPostExecute(Bitmap result) {
        bitMapImage.setImageBitmap(result);
    }
}