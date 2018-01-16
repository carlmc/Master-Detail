package com.example.cmunayll.masterdetail.fragments;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmunayll.masterdetail.R;
import com.example.cmunayll.masterdetail.adapters.MovieAdapter;
import com.example.cmunayll.masterdetail.models.Movie;
import com.example.cmunayll.masterdetail.orm.MovieDataHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmunayll on 15/01/2018.
 */

public class FavoriteFragment extends Fragment implements MovieAdapter.OnItemClickListener {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MovieAdapter adapter;

    private MovieDataHelper movieDataHelper = null;
    List<Movie> peliculas;

    public FavoriteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_movie, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        switch (getActivity().getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                layoutManager = new GridLayoutManager(getActivity(), 3);
                break;
            default:
                layoutManager = new GridLayoutManager(getActivity(), 2);
                break;
        }

        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieAdapter(getActivity(),this);

        recyclerView.setAdapter(adapter);

        movieDataHelper = new MovieDataHelper(getActivity());

        getFavorites();

        return view;
    }

    @Override
    public void onClick(MovieAdapter.ViewHolder viewHolder, Movie movie) {

    }

    private MovieDataHelper getHelper() {
        if (movieDataHelper == null) {
            movieDataHelper = OpenHelperManager.getHelper(getActivity(), MovieDataHelper.class);
        }
        return movieDataHelper;
    }

    private void getFavorites() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                peliculas=new ArrayList<>();
                peliculas.clear();
                try {
                    peliculas.addAll(getHelper().getMovies());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                adapter.setMovieList(peliculas);
            }
        }.execute();
    }
}
