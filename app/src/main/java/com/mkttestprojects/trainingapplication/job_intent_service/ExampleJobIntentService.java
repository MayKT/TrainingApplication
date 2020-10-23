package com.mkttestprojects.trainingapplication.job_intent_service;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

/**
 * Created by maykyawtthu on 10/22/2020
 **/

public class ExampleJobIntentService extends JobIntentService {

    private static final String TAG = "ExampleJobIntentService";
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        int count = intent.getIntExtra("maxCountValue",1);
        for(int iterator = 0 ; iterator < count ; iterator++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "onHandleIntent() : " + iterator);
        }
    }

    private static final int JOB_ID = 2;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, ExampleJobIntentService.class, JOB_ID, intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        toast("All work complete");
    }

    final Handler mHandler = new Handler();

    // Helper for showing tests
    void toast(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override public void run() {
                Toast.makeText(ExampleJobIntentService.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
