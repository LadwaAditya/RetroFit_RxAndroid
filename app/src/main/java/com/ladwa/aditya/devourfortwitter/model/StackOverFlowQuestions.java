package com.ladwa.aditya.devourfortwitter.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Aditya on 01-Jan-16.
 */
public class StackOverFlowQuestions extends RealmObject {

    private RealmList<QuestionModel> items;

    public RealmList<QuestionModel> getItems() {
        return items;
    }

    public void setItems(RealmList<QuestionModel> items) {
        this.items = items;
    }
}
