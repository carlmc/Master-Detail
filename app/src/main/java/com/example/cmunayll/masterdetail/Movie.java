package com.example.cmunayll.masterdetail;

/**
 * Created by cmunayll on 05/01/2018.
 */

public class Movie {

    private String idMovie;
    private String titleMovie;
    private String subtitle;
    private String description;
    private String url_image;

    public Movie(String id, String title, String subtitle, String description, String url_image) {
        this.idMovie = id;
        this.titleMovie = title;
        this.subtitle = subtitle;
        this.description = description;
        this.url_image = url_image;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String id) {
        this.idMovie = id;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String title) {
        this.titleMovie = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
