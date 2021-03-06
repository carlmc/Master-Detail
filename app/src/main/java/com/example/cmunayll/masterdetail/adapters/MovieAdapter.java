package com.example.cmunayll.masterdetail.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.cmunayll.masterdetail.models.Movie;
import com.example.cmunayll.masterdetail.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cmunayll on 05/01/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private Context context;
    private static final String URL = "http://image.tmdb.org/t/p/w185";
    private OnItemClickListener escuchaClicks;

    public MovieAdapter(Context context, OnItemClickListener escuchar) {
        this.context = context;
        this.movies = new ArrayList<>();
        this.escuchaClicks = escuchar;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_lista, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        String image_url = URL + movie.getPoster();
        Picasso.with(context)
                .load(image_url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.load)
                .into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return (movies != null ? movies.size() : 0);
    }

    private Movie obtenerIdMovie(int pos) {
        if (pos != RecyclerView.NO_POSITION) {
            return movies.get(pos);
        }
        else {
            return null;
        }
    }

    public void setMovieList(List<Movie> movieList) {
        this.movies=movieList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.poster) ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            escuchaClicks.onClick(this, obtenerIdMovie(getAdapterPosition()));
        }

    }

    public interface OnItemClickListener {
            void onClick(ViewHolder viewHolder, Movie movie);
    }
}
