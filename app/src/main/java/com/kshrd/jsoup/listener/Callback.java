package com.kshrd.jsoup.listener;

import com.kshrd.jsoup.Movie;

import java.util.List;

/**
 * Created by pirang on 7/10/17.
 */

public interface Callback {

    void onPreExecute();
    void onPostExecute(List<Movie> movieList);
    void onError();

}
