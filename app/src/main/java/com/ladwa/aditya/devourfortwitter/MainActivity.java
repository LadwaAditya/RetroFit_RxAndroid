package com.ladwa.aditya.devourfortwitter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ladwa.aditya.devourfortwitter.api.ServiceGenerator;
import com.ladwa.aditya.devourfortwitter.api.StackOverFlowAPI;
import com.ladwa.aditya.devourfortwitter.api.StackOverFlowQuestions;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mrecyclerView;
    private RecyclerView.LayoutManager mlayoutManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mrecyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);
        mlayoutManager = new LinearLayoutManager(this);

        mrecyclerView.setLayoutManager(mlayoutManager);
        mrecyclerView.setAdapter();

        StackOverFlowAPI stackOverFlowAPI = ServiceGenerator.createService(StackOverFlowAPI.class);


//        Call<StackOverFlowQuestions> call = stackOverFlowAPI.loadQuestions("android");
//
//        call.enqueue(new Callback<StackOverFlowQuestions>() {
//            @Override
//            public void onResponse(Response<StackOverFlowQuestions> response, Retrofit retrofit) {
//                setProgressBarIndeterminateVisibility(false);
//                ArrayAdapter<Question> adapter = (ArrayAdapter<Question>) getListAdapter();
//                adapter.clear();
//
//                adapter.addAll(response.body().items);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });


        Observable<StackOverFlowQuestions> observable = stackOverFlowAPI.loadQuestionsRx("android");

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<StackOverFlowQuestions>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, e.toString());
                    }

                    @Override
                    public void onNext(StackOverFlowQuestions stackOverFlowQuestions) {

                    }
                });


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
