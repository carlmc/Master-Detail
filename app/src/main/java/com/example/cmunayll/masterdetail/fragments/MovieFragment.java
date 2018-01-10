package com.example.cmunayll.masterdetail.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmunayll.masterdetail.MovieResult;
import com.example.cmunayll.masterdetail.R;
import com.example.cmunayll.masterdetail.adapters.MovieAdapter;
import com.example.cmunayll.masterdetail.interfaces.MovieInterface;
import com.example.cmunayll.masterdetail.models.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by cmunayll on 05/01/2018.
 */

public class MovieFragment extends Fragment implements MovieAdapter.OnItemClickListener {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Movie> movies;
    private MovieAdapter adapter;

    public static final String MOVIE_URL = "http://api.themoviedb.org/3/";

    private EscuchaFragment escucha;

    public MovieFragment() {

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

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(MOVIE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        MovieInterface service = retrofit.create(MovieInterface.class);

        Call<MovieResult> call = service.getMovies();

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, retrofit2.Response<MovieResult> response) {
                adapter.setMovieList(response.body().getPeliculas());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EscuchaFragment) {
            escucha = (EscuchaFragment) context;
        }
        else {
            throw new RuntimeException(context.toString()+"debes implementar");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        escucha = null;
    }

    public void cargarDetalle(Movie movie) {
        if (escucha != null) {
            escucha.selectMovie(movie);
        }
    }

    @Override
    public void onClick(MovieAdapter.ViewHolder viewHolder, Movie movie) {
        cargarDetalle(movie);
    }

    public static class LoggingInterceptor implements Interceptor {
        @Override public okhttp3.Response intercept(Chain chain) throws IOException {

            HttpUrl url = chain.request().url()
                    .newBuilder()
                    .addQueryParameter("api_key", "c69a5a2c9b8903e76de9a0e6fa564fd6")
                    .build();

            Request request = chain.request().newBuilder().url(url).build();;

            long t1 = System.nanoTime();
            String requestLog = String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers());
            //YLog.d(String.format("Sending request %s on %s%n%s",
            //        request.url(), chain.connection(), request.headers()));
            if(request.method().compareToIgnoreCase("post")==0){
                requestLog ="\n"+requestLog+"\n"+"";
            }
            Log.d("TAG","request"+"\n"+requestLog);

            okhttp3.Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            String responseLog = String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers());

            String bodyString = response.body().string();

            Log.d("TAG","response"+"\n"+responseLog+"\n"+bodyString);

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                    .build();
        }
    }

    private static String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            if (copy != null && copy.body() != null) // make sure its not null to avoif NPE
                copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    public interface EscuchaFragment {
        void selectMovie(Movie movie);
    }

}
