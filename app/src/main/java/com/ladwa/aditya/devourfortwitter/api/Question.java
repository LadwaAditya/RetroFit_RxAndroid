package com.ladwa.aditya.devourfortwitter.api;

/**
 * Created by Aditya on 01-Jan-16.
 */
public class Question {
    String title;
    String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return title;
    }
}
