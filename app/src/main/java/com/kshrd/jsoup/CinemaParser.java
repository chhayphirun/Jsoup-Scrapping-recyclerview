package com.kshrd.jsoup;

import android.os.AsyncTask;
import android.util.Log;

import com.kshrd.jsoup.listener.Callback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pirang on 7/10/17.
 */

public class CinemaParser extends AsyncTask<Void, Void, List<Movie>> {

    private List<Movie> movieList;
    private Callback callback;

    public CinemaParser(Callback callback) {
        movieList = new ArrayList<>();
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        callback.onPreExecute();
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        String baseUrl = "https://www.legend.com.kh";
        String url = baseUrl + "/Browsing/Movies/NowShowing";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            Elements movies = document.select("article#movies-list div.list-item.movie");
            for (Element movie : movies) {
                String posterUrl = movie.select("div.image-outer img").first().attr("src");
                String title = movie.select("div.item-details h3.item-title").first().text();
                String link = baseUrl + movie.select("div.image-outer a").first().attr("href");

                Movie item = new Movie(title, posterUrl, link);
                movieList.add(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
            callback.onError();
        }
        return movieList;
    }

    @Override
    protected void onPostExecute(List<Movie> movieList) {
        callback.onPostExecute(movieList);
    }
}
