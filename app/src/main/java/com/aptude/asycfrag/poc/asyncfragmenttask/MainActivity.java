package com.aptude.asycfrag.poc.asyncfragmenttask;

import android.support.v4.app.FragmentManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
                implements AsyncFragment.ParentActivity {

    private static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    private ScrollView mScroll;
    private TextView mLog;

    private AsyncFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Initialize the ScrollView and TextView for logging
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mLog.setText("");

        FragmentManager fm = getSupportFragmentManager();
        mFragment = (AsyncFragment)fm.findFragmentByTag(FRAGMENT_TAG);

        if(mFragment == null){
            mFragment = new AsyncFragment();
            fm.beginTransaction().add(mFragment,FRAGMENT_TAG).commit();

        }
    }

    public void onRunBtnClick(View v){
      mFragment.runAsyncTask("Bacon","Egg","Coffee","Muffin");
    }

    public void onClearBtnClick(View v){
        mLog.setText("");
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    private void appendMessage(String logMsg){
        mLog.append(logMsg+"\n");
        mScroll.scrollTo(0, mScroll.getBottom());
    }


    @Override
    public void handleTaskUpdate(String message) {
        appendMessage(message);
    }
}
