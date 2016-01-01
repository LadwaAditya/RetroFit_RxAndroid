package com.ladwa.aditya.devourfortwitter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ladwa.aditya.devourfortwitter.api.StackOverFlowQuestions;

/**
 * Created by Aditya on 01-Jan-16.
 */
public class StackOverFlowQuestionAdapter extends RecyclerView.Adapter<StackOverFlowQuestionAdapter.ViewHolder> {

    protected StackOverFlowQuestions mQuestions;

    public StackOverFlowQuestionAdapter(StackOverFlowQuestions questions) {
        this.mQuestions = questions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mLink;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
