package com.example.cmunayll.masterdetail.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cmunayll.masterdetail.R;
import com.example.cmunayll.masterdetail.models.Movie;
import com.example.cmunayll.masterdetail.orm.MovieDataHelper;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cmunayll on 09/01/2018.
 */

public class MovieDetailFragment extends Fragment {

    private Movie movie;
    public static final String ID_MOVIE = "idMovie";
    public static final String MOVIE_URL = "http://image.tmdb.org/t/p/w185";
    private MovieDataHelper movieDataHelper = null;

    @BindView(R.id.titulo) TextView titulo;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.movie_image) ImageView imagen;
    @BindView(R.id.boton_favorite) MaterialFavoriteButton favoriteButton;

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

            Glide.with(getActivity())
                    .load(MOVIE_URL+movie.getPoster())
                    .thumbnail(0.1f)
                    .error(R.mipmap.ic_launcher)
                    .into(imagen);
        }

        favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                if (favorite) {

                    try {
                        Dao<Movie, Integer> movieDao = getHelper().getMovieDao();
                        movieDao.create(movie);
                        Log.i(MovieDataHelper.class.getName(), "Se agregó a favoritos!");
                    } catch (SQLException e) {
                        Log.e(MovieDataHelper.class.getName(), "No se agregó a favoritos!", e);
                        e.printStackTrace();
                    }
                    Snackbar.make(buttonView, "Added to Favorite",
                            Snackbar.LENGTH_SHORT).show();
                }
                else {
                    try {
                        Dao<Movie, Integer> movieDao2 = getHelper().getMovieDao();
                        movieDao2.delete(movie);
                        Log.i(MovieDataHelper.class.getName(), "Se eliminó de favoritos!");
                    } catch (SQLException e) {
                        Log.e(MovieDataHelper.class.getName(), "No se pudo eliminar!");
                        e.printStackTrace();
                    }
                    Snackbar.make(buttonView, "Removed from Favorite", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private MovieDataHelper getHelper() {
        if (movieDataHelper == null) {
            movieDataHelper = OpenHelperManager.getHelper(getActivity(), MovieDataHelper.class);
        }
        return movieDataHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (movieDataHelper != null) {
            OpenHelperManager.releaseHelper();
            movieDataHelper = null;
        }
    }

}
