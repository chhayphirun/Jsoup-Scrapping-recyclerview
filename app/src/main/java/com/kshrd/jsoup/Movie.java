package com.kshrd.jsoup;

/**
 * Created by pirang on 7/10/17.
 */

public class Movie {

    private String title;
    private String posterUrl;
    private String link;
    private String linktrailer;
    private String date;

    public Movie(String title, String posterUrl, String link, String linktrailer, String date) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.link = link;
        this.linktrailer = linktrailer;
        this.date = date;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Movie(String title, String posterUrl, String link) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.link = link;
    }

    public String getLinktrailer() {
        return linktrailer;
    }

    public void setLinktrailer(String linktrailer) {
        this.linktrailer = linktrailer;
    }

    public Movie(String title, String posterUrl, String link, String linktrailer) {

        this.title = title;
        this.posterUrl = posterUrl;
        this.link = link;
        this.linktrailer = linktrailer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return this.title+","+linktrailer;
    }
}
