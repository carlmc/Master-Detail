package com.example.cmunayll.masterdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cmunayll.masterdetail.models.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by cmunayll on 09/01/2018.
 */

public class MovieDetailFragment extends Fragment {

    private Movie movie;
    public static final String ID_MOVIE = "idMovie";
    public static final String MOVIE_URL = "http://image.tmdb.org/t/p/w185";

    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ID_MOVIE)) {
            movie=getArguments().getParcelable(ID_MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_movie, container, false);

        if (movie != null) {
            ((TextView) v.findViewById(R.id.titulo)).setText(movie.getTitle());
            ((TextView) v.findViewById(R.id.date)).setText(movie.getDate());
            ((TextView) v.findViewById(R.id.description)).setText(movie.getDescription());
            /*Picasso.with(getActivity())
                    .load(MOVIE_URL+movie.getPoster())
                    .error(R.mipmap.ic_launcher)
                    .into((ImageView) v.findViewById(R.id.movie_image));*/

            Glide.with(getActivity())
                    .load(MOVIE_URL+movie.getPoster())
                    .thumbnail(0.1f)
                    .error(R.mipmap.ic_launcher)
                    .into((ImageView) v.findViewById(R.id.movie_image));
        }

        return v;
    }

}
