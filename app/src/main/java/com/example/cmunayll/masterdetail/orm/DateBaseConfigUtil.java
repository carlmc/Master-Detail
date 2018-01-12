package com.example.cmunayll.masterdetail.orm;

import com.example.cmunayll.masterdetail.models.Movie;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by cmunayll on 12/01/2018.
 */

public class DateBaseConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] movies = new Class[]{Movie.class};

    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt", movies);
    }

}
