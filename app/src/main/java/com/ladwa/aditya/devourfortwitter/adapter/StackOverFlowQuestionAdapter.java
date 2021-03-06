package com.ladwa.aditya.devourfortwitter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ladwa.aditya.devourfortwitter.R;
import com.ladwa.aditya.devourfortwitter.model.StackOverFlowQuestions;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mQuestions.getItems().get(position).getTitle());
        holder.mLink.setText(mQuestions.getItems().get(position).getLink());
    }

    @Override
    public int getItemCount() {
        return mQuestions.getItems().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mLink;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTitle = (TextView) itemView.findViewById(R.id.textView_title);
            this.mLink = (TextView) itemView.findViewById(R.id.textView_link);
        }
    }


}
