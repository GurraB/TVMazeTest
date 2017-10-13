package se.mah.af6589.tvmazetest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Gustaf Bohlin on 12/10/2017.
 */

public class TVMazeApi extends AsyncTask<String, Integer, String> {

    private HttpURLConnection connection;
    private URL url;
    private String stringURL = "http://api.tvmaze.com/schedule?country=US&date=";
    private String inputLine, result;
    private MainActivity activity;

    public TVMazeApi(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date());
            url = new URL(stringURL + currentDate);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while((inputLine = reader.readLine()) != null)
                stringBuilder.append(inputLine);
            reader.close();
            streamReader.close();
            result = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        activity.method(s);
    }
}
