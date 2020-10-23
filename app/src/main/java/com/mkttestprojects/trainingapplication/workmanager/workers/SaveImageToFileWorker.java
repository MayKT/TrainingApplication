package com.mkttestprojects.trainingapplication.workmanager.workers;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.mkttestprojects.trainingapplication.helper.AppConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by maykyawtthu on 10/21/2020
 **/

public class SaveImageToFileWorker extends Worker {
    private static final String TAG = SaveImageToFileWorker.class.getSimpleName();

    private static final String TITLE = "Blurred Image";
    private static final SimpleDateFormat DATE_FORMATTER =
            new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z", Locale.getDefault());

    public SaveImageToFileWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();

        // Makes a notification when the work starts and slows down the work so that it's easier to
        // see each WorkRequest start, even on emulated devices
        WorkerUtils.makeStatusNotification("Saving image", applicationContext);
        WorkerUtils.sleep();

        ContentResolver resolver = applicationContext.getContentResolver();
        try {
            String resourceUri = getInputData()
                    .getString(AppConstants.KEY_IMAGE_URI);
            Bitmap bitmap = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri)));
            String outputUri = MediaStore.Images.Media.insertImage(
                    resolver, bitmap, TITLE, DATE_FORMATTER.format(new Date()));
            if (TextUtils.isEmpty(outputUri)) {
                Log.e(TAG, "Writing to MediaStore failed");
                return Result.failure();
            }
            Data outputData = new Data.Builder()
                    .putString(AppConstants.KEY_IMAGE_URI, outputUri)
                    .build();
            return Result.success(outputData);
        } catch (Exception exception) {
            Log.e(TAG, "Unable to save image to Gallery", exception);
            return Worker.Result.failure();
        }    }
}
