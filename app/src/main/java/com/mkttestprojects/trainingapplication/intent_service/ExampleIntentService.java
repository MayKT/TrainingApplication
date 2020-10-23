package com.mkttestprojects.trainingapplication.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by maykyawtthu on 10/22/2020
 **/

public class ExampleIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ExampleIntentService(String name) {
        super(name);
    }

    public ExampleIntentService() {
        super("ExampleIntentService");
    }

    private static final String TAG = "ExampleIntentService";
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        for(int iterator = 0 ; iterator < 10 ; iterator++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "onHandleIntent() : " + iterator);
        }
    }

}
