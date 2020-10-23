package com.mkttestprojects.trainingapplication.local_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

import com.mkttestprojects.trainingapplication.R;
import com.mkttestprojects.trainingapplication.common.BaseActivity;

import butterknife.BindView;

/**
 * Created by maykyawtthu on 10/16/2020
 **/

public class LocalServiceTestActivity extends BaseActivity {
    @BindView(R.id.btn_click_me)
    Button btnClickMe;

    LocalService mService;
    boolean mBound = false;

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_service_test;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        setupToolbar(true);
        setupToolbarText("ServiceTestActivity");
        btnClickMe.setOnClickListener(v -> {
            if (mBound) {
                int num = mService.getRandomNumber();
                showToastMsg("Number is" + num);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }
}
