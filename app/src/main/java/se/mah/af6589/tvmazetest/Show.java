package se.mah.af6589.tvmazetest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gustaf Bohlin on 12/10/2017.
 */

public class Show {

    private String showName;
    private String episodeName;
    private String imageURL;
    private String episodeNumber;
    private String seasonNumber;

    public Show(JSONObject object) throws JSONException {
        JSONObject show = object.getJSONObject("show");
        showName = show.getString("name");
        episodeName = object.getString("name");
        seasonNumber = object.getString("season");
        episodeNumber = object.getString("number");
        imageURL = show.getJSONObject("image").getString("original");
    }

    public String getName() {
        return showName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public String getSeasonNumber() {
        return seasonNumber;
    }
}
