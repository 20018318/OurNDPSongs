package sg.edu.rp.c346.id20018318.ourndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter( Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvYr = rowView.findViewById(R.id.tvYr);
//        TextView tvRating = rowView.findViewById(R.id.tvRating);
        TextView tvSinger = rowView.findViewById(R.id.tvSinger);
        ImageView ivNew = rowView.findViewById(R.id.imageViewNew);
        RatingBar rbStars = rowView.findViewById(R.id.ratingBar2);

        Song currentSong = songList.get(position);

        tvName.setText(currentSong.getTitle());
        tvYr.setText(String.valueOf(currentSong.getYear()));
        tvSinger.setText(currentSong.getSingers());
//        tvRating.setText(currentSong.toString());
        rbStars.setRating(currentSong.getStars());

        if (currentSong.getYear() < 2019) {
            ivNew.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }
}

