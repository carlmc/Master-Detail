package com.example.cmunayll.masterdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cmunayll.masterdetail.models.Movie;

/**
 * Created by cmunayll on 09/01/2018.
 */

public class MovieDetailFragment extends Fragment {

    private Movie movie;

    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_movie, container, false);

        if (movie != null) {
            ((TextView) v.findViewById(R.id.titulo)).setText(movie.getTitle());
            ((TextView) v.findViewById(R.id.date)).setText(movie.getDate());
            ((TextView) v.findViewById(R.id.description)).setText(movie.getDescription());
        }



        return v;
    }

}
