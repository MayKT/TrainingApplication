package com.mkttestprojects.trainingapplication.job_intent_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mkttestprojects.trainingapplication.R;
import butterknife.BindView;
/**
 * Created by maykyawtthu on 10/22/2020
 **/

public class JobIntentServiceTestActivity extends AppCompatActivity{

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent mIntent = new Intent(this, ExampleJobIntentService.class);
        mIntent.putExtra("maxCountValue", 10);
        ExampleJobIntentService.enqueueWork(this, mIntent);

    }

}
