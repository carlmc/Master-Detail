package com.example.cmunayll.masterdetail.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cmunayll.masterdetail.R;
import com.example.cmunayll.masterdetail.models.Movie;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cmunayll on 11/01/2018.
 */

public class MovieDataHelper extends OrmLiteSqliteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Movie";
    private static final int DATABASE_VERSION = 1;

    private Dao<Movie, Integer> movies = null;
    private RuntimeExceptionDao<Movie, Integer> runtimeExceptionDao = null;

    public MovieDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Movie.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Movie.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao<Movie, Integer> getMovieDao() throws SQLException {
        if (movies == null) {
            movies = getDao(Movie.class);
            Log.i(MovieDataHelper.class.getName(), "DAO!");
        }
        return movies;
    }

    public List<Movie> getMovies() throws SQLException {
        Dao<Movie, Integer> dao=getMovieDao();
        List<Movie> lista = dao.queryForAll();
        return lista;
    }

    public RuntimeExceptionDao<Movie, Integer> getMovieDaoRuntime() {
        if (runtimeExceptionDao == null) {
            runtimeExceptionDao = getRuntimeExceptionDao(Movie.class);
        }
        return runtimeExceptionDao;
    }

    @Override
    public void close() {
        super.close();
        movies = null;
        runtimeExceptionDao = null;
    }
}
