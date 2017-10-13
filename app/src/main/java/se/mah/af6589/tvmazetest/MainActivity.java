package se.mah.af6589.tvmazetest;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private HashMap<String, Bitmap> retrievedBitmaps = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(new ArrayList<Show>());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new SpacingItemDecoration(10));
        rv.setAdapter(adapter);
        TVMazeApi api = new TVMazeApi(this);
        api.execute();
    }

    private void jsonHandler(String s) {
        ArrayList<Show> shows = new ArrayList<>();
        try {
            JSONArray a = new JSONArray(s);
            for (int i = 0; i < a.length(); i++) {
                JSONObject showObject = a.getJSONObject(i);
                shows.add(new Show(showObject));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(shows);
        rv.setAdapter(adapter);
    }

    public void method(String s) {
        jsonHandler(s);
    }

    public Bitmap getBitmap(String key) {
        return retrievedBitmaps.get(key);
    }

    public void setNewBitmap(String key, Bitmap bitmap) {
        retrievedBitmaps.put(key, bitmap);
    }
}
