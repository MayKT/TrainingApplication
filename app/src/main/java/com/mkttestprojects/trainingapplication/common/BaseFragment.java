package com.mkttestprojects.trainingapplication.common;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mkttestprojects.trainingapplication.custom_control.MyanProgressDialog;
import com.mkttestprojects.trainingapplication.custom_control.MyanTextProcessor;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected Unbinder unbinder;
    public Context mContext;

    private MyanProgressDialog myCustomProgressDialog;

    private int someStateValue;
    private final String SOME_VALUE_KEY = "someValueToSave";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            someStateValue = savedInstanceState.getInt(SOME_VALUE_KEY);
            // Do something with value if needed
        }

        return  inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, getView());

        mContext=getView().getContext();

        myCustomProgressDialog = new MyanProgressDialog(mContext);

        setUpContents(savedInstanceState);
    }

    public void showToastMsg(String msg) {
        Toast.makeText(mContext, MyanTextProcessor.processText(mContext,msg), Toast.LENGTH_LONG).show();
    }

    public void showShortToastMsg(String msg) {
        Toast.makeText(mContext, MyanTextProcessor.processText(mContext,msg), Toast.LENGTH_SHORT).show();
    }

    public void showMyanProgressDialog() {
            myCustomProgressDialog.showDialog();
    }


    public void hideMyanProgressDialog() {
            myCustomProgressDialog.hideDialog();
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract void setUpContents(Bundle savedInstanceState);

}
