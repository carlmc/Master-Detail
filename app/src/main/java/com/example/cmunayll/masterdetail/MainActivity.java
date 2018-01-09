package com.example.cmunayll.masterdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cmunayll.masterdetail.fragments.MovieFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //((Toolbar) findViewById(R.id.toolbar)).setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_movie_lista, new MovieFragment())
                .commit();
    }
}
