package com.mkttestprojects.trainingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mkttestprojects.trainingapplication.common.BaseActivity;
import com.mkttestprojects.trainingapplication.intent_service.IntentServiceTestActivity;
import com.mkttestprojects.trainingapplication.job_intent_service.JobIntentServiceTestActivity;
import com.mkttestprojects.trainingapplication.local_service.LocalServiceTestActivity;
import com.mkttestprojects.trainingapplication.messenger_service.MessengerServiceTestActivity;
import com.mkttestprojects.trainingapplication.workmanager.WorkManagerTestActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_test_service)
    Button btnTestService;

    @BindView(R.id.btn_test_messenger_service)
    Button btnTestMessengerService;

    @BindView(R.id.btn_test_workmanager)
    Button btnWorkManager;

    @BindView(R.id.btn_test_job_intent_service)
    Button btnTestJobIntentService;

    @BindView(R.id.btn_intent_service)
    Button btnTestIntentService;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {

        setupToolbar(false);
        init();
    }

    private void init() {
        btnTestService.setOnClickListener(this);
        btnTestMessengerService.setOnClickListener(this);
        btnWorkManager.setOnClickListener(this);
        btnTestIntentService.setOnClickListener(this);
        btnTestJobIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_test_service:
                startActivity(new Intent(this, LocalServiceTestActivity.class));
                break;
            case R.id.btn_test_messenger_service:
                startActivity(new Intent(this, MessengerServiceTestActivity.class));
                break;
            case R.id.btn_test_workmanager:
                startActivity(new Intent(this, WorkManagerTestActivity.class));
                break;
            case R.id.btn_test_job_intent_service:
                startActivity(new Intent(this, JobIntentServiceTestActivity.class));
                break;
            case R.id.btn_intent_service:
                startActivity(new Intent(this, IntentServiceTestActivity.class));
        }
    }
}