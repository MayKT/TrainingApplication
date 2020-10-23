package com.mkttestprojects.trainingapplication.intent_service;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mkttestprojects.trainingapplication.R;

import butterknife.BindView;

/**
 * Created by maykyawtthu on 10/22/2020
 **/

public class IntentServiceTestActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, ExampleIntentService.class);
        startService(intent);
    }

}