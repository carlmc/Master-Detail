package com.example.cmunayll.masterdetail;

import com.example.cmunayll.masterdetail.models.Movie;

import java.util.ArrayList;

/**
 * Created by cmunayll on 08/01/2018.
 */

public class MovieResult {

    private int total_pages;

    public int getTotal_pages() {
        return total_pages;
    }

    private ArrayList<Movie> results;

    public ArrayList<Movie> getPeliculas() {
        return results;
    }
}
