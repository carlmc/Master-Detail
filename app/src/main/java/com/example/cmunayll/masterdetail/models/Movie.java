package com.example.cmunayll.masterdetail.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by cmunayll on 05/01/2018.
 */

public class Movie implements Parcelable {

    private String id;
    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("overview")
    private String description;
    @SerializedName("release_date")
    private String date;

    public Movie() {

    }

    protected Movie(Parcel in) {
        //this.id = generarID();
        this.title = in.readString();
        this.poster = in.readString();
        this.description = in.readString();
        this.date = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };

    public String getId() {
        return generarID();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.poster);
        parcel.writeString(this.description);
        parcel.writeString(this.date);
    }

    private static String generarID() {
        return UUID.randomUUID().toString();
    }
    /*public Movie(String url) {
        this.url_image = url;
    }*/


}
