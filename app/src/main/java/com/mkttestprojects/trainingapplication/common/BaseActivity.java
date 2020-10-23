package com.mkttestprojects.trainingapplication.common;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mkttestprojects.trainingapplication.R;
import com.mkttestprojects.trainingapplication.custom_control.MyanBoldTextView;
import com.mkttestprojects.trainingapplication.custom_control.MyanProgressDialog;
import com.yammobots.kconnectioncheck.KConnectionCheck;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity implements KConnectionCheck.ConnectionStatusChangeListener {

    protected Unbinder unbinder;
    private MyanProgressDialog myCustomProgressDialog;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_text)
    TextView toolbar_text;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;

    private int MY_CAMERA_REQUEST_CODE = 10;

    private boolean connectionStatus = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        unbinder = ButterKnife.bind(this);
        myCustomProgressDialog = new MyanProgressDialog(this);
        setUpContents(savedInstanceState);

//        if (!(this instanceof SplashActivity))
//            KConnectionCheck.addConnectionCheck(this, this, this);
    }

    protected void setupToolbar(boolean isChild) {

        if (toolbar != null)
            setSupportActionBar(toolbar);

        if (isChild) {
            if (getSupportActionBar() != null) {

                /*final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
                upArrow.setColorFilter(getResources().getColor(R.color.colorTextColorPrimary), PorterDuff.Mode.SRC_ATOP);
                getSupportActionBar().setHomeAsUpIndicator(upArrow);*/


                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    protected void setupToolbarText(String text) {
        //getSupportActionBar().setTitle(text);
        toolbar_text.setText(text);
    }

    protected void setupToolbarBgColor(String color) {
        toolbar.setBackgroundColor(Color.parseColor(color));
    }

    protected void setupToolbarTextColor(String color) {
        toolbar.setTitleTextColor(Color.parseColor(color));
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract void setUpContents(Bundle savedInstanceState);

    public void askPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkAndRequestPermissions(this)) {
                onAllPermissionsGranted();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_CAMERA_REQUEST_CODE);
            }
        }
    }

    public static boolean checkAndRequestPermissions(Activity context) {
        int ExternalStorePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);

        int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (ExternalStorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    showToastMsg("Requires Access to Camera.");
                    finish();
                } else if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    showToastMsg("Requires Access to Your Storage.");
                    finish();
                } else {
                    onAllPermissionsGranted();
                }
                break;
        }
    }

    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }


    public void onAllPermissionsGranted() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void showShortToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    // check version on play store and force update
//    private void forceUpdate() {
//        PackageManager packageManager = this.getPackageManager();
//        PackageInfo packageInfo = null;
//        try {
//            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
//            String currentVersion = packageInfo.versionName;
//
//            new ForceUpdateAsync(currentVersion, BaseActivity.this).execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }


//    }

    public void showLoading() {
        if (!this.isFinishing()) {
            myCustomProgressDialog.showDialog();
        }
    }

    public void hideLoading() {
        if (!this.isFinishing()) {
            myCustomProgressDialog.hideDialog();
        }
    }


    public void showAlertDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok",
                        (dialog, whichButton) -> dialog.dismiss()).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        forceUpdate();
    }

    public void onConnectionStatusChange(boolean connectionStatus) {
    }
}
