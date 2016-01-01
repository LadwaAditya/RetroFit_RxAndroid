package com.ladwa.aditya.devourfortwitter.api;


import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Aditya on 01-Jan-16.
 */
public interface StackOverFlowAPI {

    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverFlowQuestions> loadQuestions(@Query("tagged") String tags);

    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Observable<StackOverFlowQuestions> loadQuestionsRx(@Query("tagged") String tags);

    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Observable<List<Question>> loadQuestionRx(@Query("tagged") String tags);

}
