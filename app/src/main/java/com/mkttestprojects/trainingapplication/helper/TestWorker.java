package com.mkttestprojects.trainingapplication.helper;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Created by maykyawtthu on 10/20/2020
 **/

public class TestWorker extends Worker {

    public TestWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Do the work here--in this case, upload the images.
//        uploadImages();

        // Indicate whether the work finished successfully with the Result
        return Result.success();

        /*
        Result.success(): The work finished successfully.
        Result.failure(): The work failed.
        Result.retry(): The work failed and should be tried at another time according to its retry policy.
         */
    }
}
