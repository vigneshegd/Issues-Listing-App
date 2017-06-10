package com.zencode.issuelist.utils;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zencode.issuelist.R;
import com.zencode.issuelist.main.BaseActivity;


public class DialogManager extends BaseActivity {

    static Dialog mDialog;
    static Dialog progress;

    static DialogFragment mDialogFrag;;

    public static Toast toastMake=Toast.makeText(mActivity, "", Toast.LENGTH_SHORT);;
    public static void showToast1(Context mContext, String mString) {
        mString=mString.toUpperCase();
        toastMake.cancel();
        toastMake=Toast.makeText(mContext, mString, Toast.LENGTH_SHORT);
        toastMake.show();
    }


    public static Dialog getDialog(Context mContext, int mLayout) {


        Dialog mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mDialog.setContentView(mLayout);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        return mDialog;
    }



    private static Dialog getLoadingDialog(Context mContext) {

        mDialog = getDialog(mContext, R.layout.progress);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        // mDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        // WindowManager.LayoutParams.MATCH_PARENT);
        // mDialog.getWindow().setGravity(Gravity.CENTER);
        // ImageView _imagView = (ImageView)
        // mDialog.findViewById(R.id.imageView1);
        // handler = new MyHandler(context, _imagView);
        // _index = 1;
        // _timer = new Timer();
        // TickClass tick = new TickClass(context);
        // _timer.schedule(tick, 100, 100);

        return mDialog;
    }

    public static void showProgress(Context context) {
        if (progress != null){
            progress.dismiss();
        }
        progress = getLoadingDialog(context);

        progress.show();

    }


    public static void hideProgress() {

        // if (_timer != null) {
        // _timer.cancel();
        // }
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }

    }


    public static void showMsgPopup(final Context mContext,final String msg) {
        if (mDialog != null )
        {
            mDialog.dismiss();
        }
        mDialog = getDialog(mContext, R.layout.popup_msg_layout);

        Button m_btnOk = (Button) mDialog.findViewById(R.id.submitbutton);
        TextView mTitte=(TextView)mDialog.findViewById(R.id.msg_txt);
        mTitte.setText(msg.toUpperCase());


            mDialog.setCanceledOnTouchOutside(true);
            mDialog.setCancelable(true);

        m_btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialog.dismiss();


            }
        });

        mDialog.show();
    }


}
