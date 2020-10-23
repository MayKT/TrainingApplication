package com.mkttestprojects.trainingapplication.job_intent_service;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by maykyawtthu on 10/22/2020
 **/

public class ServiceResultReceiver extends ResultReceiver {
    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public ServiceResultReceiver(Handler handler) {
        super(handler);
    }
    private Receiver mReceiver;

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }


    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }
}
