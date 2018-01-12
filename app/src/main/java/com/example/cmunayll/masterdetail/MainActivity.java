package com.example.cmunayll.masterdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cmunayll.masterdetail.fragments.MovieDetailFragment;
import com.example.cmunayll.masterdetail.fragments.MovieFragment;
import com.example.cmunayll.masterdetail.models.Movie;

public class MainActivity extends AppCompatActivity implements MovieFragment.EscuchaFragment {

    private boolean dosPanels;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //((Toolbar) findViewById(R.id.toolbar)).setVisibility(View.GONE);

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

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
