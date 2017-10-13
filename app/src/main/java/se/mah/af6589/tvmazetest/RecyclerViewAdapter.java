package se.mah.af6589.tvmazetest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gustaf Bohlin on 12/10/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Show> shows;

    public RecyclerViewAdapter(ArrayList<Show> shows) {
        this.shows = shows;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Show show = shows.get(position);
        holder.tvShowName.setText(show.getName());
        holder.ivPoster.setImageFromURL(show.getImageURL());
        holder.tvSeasonEpisode.setText("S" + show.getSeasonNumber() + "E" + show.getEpisodeNumber());
        holder.tvEpisodeName.setText(show.getEpisodeName());
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvShowName, tvSeasonEpisode, tvEpisodeName;
        private InternetImageView ivPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            tvShowName = itemView.findViewById(R.id.tv_show_name);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvSeasonEpisode = itemView.findViewById(R.id.tv_season_episode);
            tvEpisodeName = itemView.findViewById(R.id.tv_episode_name);
        }
    }
}
