package se.mah.af6589.tvmazetest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Gustaf Bohlin on 12/10/2017.
 */

public class InternetImageView extends ImageView {

    public InternetImageView(Context context) {
        super(context);
    }

    public InternetImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InternetImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InternetImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setImageFromURL(String url) {
        Bitmap bitmap = ((MainActivity) getContext()).getBitmap(url);
        if (bitmap != null)
            setImageBitmap(bitmap);
        else
            new getImage().execute(url);
    }

    private class getImage extends AsyncTask<String, Integer, Bitmap> {

        private String key;

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                key = strings[0];
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(key).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            setImageBitmap(bitmap);
            ((MainActivity) getContext()).setNewBitmap(key, bitmap);
        }
    }
}
