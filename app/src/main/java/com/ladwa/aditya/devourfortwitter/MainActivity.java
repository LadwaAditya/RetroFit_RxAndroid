package com.ladwa.aditya.devourfortwitter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ladwa.aditya.devourfortwitter.adapter.QuestionAdapter;
import com.ladwa.aditya.devourfortwitter.adapter.StackOverFlowQuestionAdapter;
import com.ladwa.aditya.devourfortwitter.api.ServiceGenerator;
import com.ladwa.aditya.devourfortwitter.api.StackOverFlowAPI;
import com.ladwa.aditya.devourfortwitter.model.QuestionModel;
import com.ladwa.aditya.devourfortwitter.model.StackOverFlowQuestions;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mrecyclerView;
    private RecyclerView.LayoutManager mlayoutManager;
    private StackOverFlowQuestionAdapter mAdapter;
    private Subscription getQuestionSubscription;
    private Realm realm;
    private StackOverFlowAPI stackOverFlowAPI;
    private RealmChangeListener mrealmChangeListener;
    private RealmResults<QuestionModel> mQuestion;
    private QuestionAdapter mquestionAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mrecyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);
        mlayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlayoutManager);

        realm = Realm.getDefaultInstance();

        mrealmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange() {
                mrecyclerView.setAdapter(mquestionAdapter);
                Log.d(TAG, "onChange" + mQuestion.size());
            }
        };

        realm.addChangeListener(mrealmChangeListener);
        mQuestion = realm.where(QuestionModel.class).findAll();
        mquestionAdapter = new QuestionAdapter(mQuestion);
        mquestionAdapter.notifyDataSetChanged();
        Log.d(TAG, "onCreate " + mQuestion.size());
        mrecyclerView.setAdapter(mquestionAdapter);


        stackOverFlowAPI = ServiceGenerator.createService(StackOverFlowAPI.class);
        getQuestionSubscription = stackOverFlowAPI.loadQuestionsRx("android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<StackOverFlowQuestions>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Completed");

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StackOverFlowQuestions stackOverFlowQuestions) {

                        realm = Realm.getDefaultInstance();

                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(stackOverFlowQuestions.getItems());
                        realm.commitTransaction();

                    }
                });


    }

    @Override
    protected void onPause() {
        super.onPause();
        getQuestionSubscription.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
        Log.d(TAG, "OnDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
