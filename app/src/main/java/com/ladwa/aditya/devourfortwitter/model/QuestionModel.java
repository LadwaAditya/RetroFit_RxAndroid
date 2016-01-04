package com.ladwa.aditya.devourfortwitter.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Aditya on 01-Jan-16.
 */
public class QuestionModel extends RealmObject {

    private String title;
    @PrimaryKey
    private String link;

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
}
