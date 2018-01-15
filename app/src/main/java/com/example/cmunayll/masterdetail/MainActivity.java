package com.example.cmunayll.masterdetail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.cmunayll.masterdetail.adapters.MovieAdapter;
import com.example.cmunayll.masterdetail.fragments.FavoriteFragment;
import com.example.cmunayll.masterdetail.fragments.MovieDetailFragment;
import com.example.cmunayll.masterdetail.fragments.MovieFragment;
import com.example.cmunayll.masterdetail.models.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieFragment.EscuchaFragment, SharedPreferences.OnSharedPreferenceChangeListener {

    private boolean dosPanels;
    private Movie movie;
    private static final String LOG_TAG = MovieAdapter.class.getName();

    @BindView(R.id.fab) FloatingActionButton floa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        floa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verFavoritos();
            }
        });

        if (findViewById(R.id.contenedor_movie_detalle) != null) {
            dosPanels = true;

            cargarFragmentDetail(movie);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_movie_lista, new MovieFragment())
                .commit();
    }

    private void cargarFragmentDetail(Movie id) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(MovieDetailFragment.ID_MOVIE,  id);
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_movie_detalle, fragment)
                .commit();
    }

    @Override
    public void selectMovie(Movie movie) {
        if (dosPanels) {
            cargarFragmentDetail(movie);
        }
        else {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.ID_MOVIE, movie);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.d(LOG_TAG, "Preferences updated");
        checkSortOrder();
    }

    private void checkSortOrder() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(
                this.getString(R.string.pref_sort_order_key),
                this.getString(R.string.pref_most_popular)
        );
        if (sortOrder.equals(this.getString(R.string.pref_most_popular))) {
            Log.d(LOG_TAG, "Sorting by most popular");
        }
        else if (sortOrder.equals(this.getString(R.string.favorite))) {
            Log.d(LOG_TAG, "Sorting by favorite");
            verFavoritos();
        }
        else {
            Log.d(LOG_TAG, "Sorting by vote average");
        }
    }

    public void verFavoritos() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_movie_lista, new FavoriteFragment())
                .commit();

    }
}
