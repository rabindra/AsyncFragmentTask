package com.aptude.asycfrag.poc.asyncfragmenttask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Rabindra on 11/29/17.
 */

public class AsyncFragment extends Fragment {

    private ParentActivity mParent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mParent = (ParentActivity) context;

        Log.i("AsyncFragment", "attached");
    }

    public void runAsyncTask(String... params){
        MyTask myTask = new MyTask();
        myTask.execute(params);
    }

    public interface ParentActivity{
        void handleTaskUpdate(String message);
    }

    class MyTask extends AsyncTask<String, String, Void> {


        @Override
        protected Void doInBackground(String... params) {

            for (String s: params){
                publishProgress("I had "+s);

                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mParent.handleTaskUpdate(values[0]);
        }
    }
}
