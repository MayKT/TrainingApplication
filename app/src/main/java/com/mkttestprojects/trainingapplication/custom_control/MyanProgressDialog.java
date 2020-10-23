package com.mkttestprojects.trainingapplication.custom_control;

import android.app.Dialog;
import android.content.Context;

import com.mkttestprojects.trainingapplication.R;

public class MyanProgressDialog {

    private Dialog dialog;

    public MyanProgressDialog(Context context)
    {
        dialog = new Dialog(context, R.style.DialogTransparent);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(false);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
    }

    public void showDialog()
    {
        dialog.show();
    }

    public void hideDialog()
    {
        dialog.dismiss();
    }

}
