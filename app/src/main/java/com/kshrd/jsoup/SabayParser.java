package com.kshrd.jsoup;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by pirang on 7/10/17.
 */

public class SabayParser extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        String url = "http://news.sabay.com.kh/article/950208";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            Log.e("ooooo", document.getElementsByTag("title").first().text());

            Elements images = document.select("div.content-grp-img img");
            Log.e("FIRST", images.get(1).attr("src"));
            for (Element element : images) {
                Log.e("IMAGE_PATH", element.attr("src"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
