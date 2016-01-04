package com.ladwa.aditya.devourfortwitter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ladwa.aditya.devourfortwitter.R;
import com.ladwa.aditya.devourfortwitter.model.QuestionModel;

import io.realm.RealmResults;

/**
 * Created by Aditya on 04-Jan-16.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {


    RealmResults<QuestionModel> mquestionModel;

    public QuestionAdapter(RealmResults<QuestionModel> mquestionModel) {
        this.mquestionModel = mquestionModel;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mquestionModel.get(position).getTitle());
        holder.mLink.setText(mquestionModel.get(position).getLink());
    }

    @Override
    public int getItemCount() {
//        if (mquestionModel == null)
//            return 0;
//        else
            return mquestionModel.size();
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
