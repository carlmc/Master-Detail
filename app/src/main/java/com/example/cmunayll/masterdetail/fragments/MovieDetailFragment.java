package com.example.cmunayll.masterdetail.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cmunayll.masterdetail.R;
import com.example.cmunayll.masterdetail.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cmunayll on 09/01/2018.
 */

public class MovieDetailFragment extends Fragment {

    private Movie movie;
    public static final String ID_MOVIE = "idMovie";
    public static final String MOVIE_URL = "http://image.tmdb.org/t/p/w185";

    @BindView(R.id.titulo) TextView titulo;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.movie_image) ImageView imagen;

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
            ButterKnife.bind(this, v);

            titulo.setText(movie.getTitle());
            date.setText(movie.getDate());
            description.setText(movie.getDescription());
            /*Picasso.with(getActivity())
                    .load(MOVIE_URL+movie.getPoster())
                    .error(R.mipmap.ic_launcher)
                    .into(imagen);*/

            Glide.with(getActivity())
                    .load(MOVIE_URL+movie.getPoster())
                    .thumbnail(0.1f)
                    .error(R.mipmap.ic_launcher)
                    .into(imagen);
        }

        return v;
    }

}
