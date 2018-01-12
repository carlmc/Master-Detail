package com.example.cmunayll.masterdetail.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;


/**
 * Created by cmunayll on 05/01/2018.
 */

public class Movie implements Parcelable {

    @SerializedName("id")
    @DatabaseField(generatedId = true)
    private int idMovie;
    @DatabaseField
    private String title;
    @SerializedName("poster_path")
    @DatabaseField
    private String poster;
    @SerializedName("overview")
    @DatabaseField
    private String description;
    @SerializedName("release_date")
    @DatabaseField
    private String date;

    public Movie() {

    }

    protected Movie(Parcel in) {
        this.idMovie = in.readInt();
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

    public int getIdMovie() {
        return idMovie;
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

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.idMovie);
        parcel.writeString(this.title);
        parcel.writeString(this.poster);
        parcel.writeString(this.description);
        parcel.writeString(this.date);
    }

}
