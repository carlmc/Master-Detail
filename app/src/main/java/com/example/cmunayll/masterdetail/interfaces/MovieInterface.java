package com.example.cmunayll.masterdetail.interfaces;

import com.example.cmunayll.masterdetail.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cmunayll on 08/01/2018.
 */

public interface MovieInterface {
    @GET("movie/popular")
    Call<MovieResult> getMovies();
}
