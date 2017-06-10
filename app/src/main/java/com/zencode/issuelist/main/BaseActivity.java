package com.zencode.issuelist.main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.zencode.issuelist.R;
import com.zencode.issuelist.utils.DialogManager;


import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;


public class  BaseActivity extends AppCompatActivity {
    public static Activity mActivity;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mActivity = this;

    }

    protected void setupUI(View view) {

        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(mActivity);
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View mInnerView = ((ViewGroup) view).getChildAt(i);
                setupUI(mInnerView);
            }
        }
    }

    protected void hideSoftKeyboard(Activity mActivity) {
        try {
            if (mActivity != null && !mActivity.isFinishing()) {
                InputMethodManager mInputMethodManager = (InputMethodManager) mActivity
                        .getSystemService(INPUT_METHOD_SERVICE);

                if (mActivity.getCurrentFocus() != null
                        && mActivity.getCurrentFocus().getWindowToken() != null) {
                    mInputMethodManager.hideSoftInputFromWindow(mActivity
                            .getCurrentFocus().getWindowToken(), 0);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void nextScreen(Class<?> clazz) {

        Intent mIntent = new Intent(getApplicationContext(), clazz);
        mActivity.startActivity(mIntent);
        mActivity.overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);
    }


    public void sysOut(String msg) {
        System.out.println(msg);
        Log.d(getString(R.string.app_name), msg);
    }
    @Override
    protected void onPause() {
        super.onPause();
        Application.activityStoped();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mActivity = this;
        Application.activityResumed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Application.activityStoped();
    }



    public Bitmap ResizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable",  mActivity.getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public void onRequestSuccess(Object responseObj) {
    }


    public void onRequestFailure(Throwable t) {

        try {
            System.out.println("errorCode.getCause() Msg--------" + t.getMessage());
            System.out.println("errorCode.getCause() --------" + t.getCause().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (t instanceof IOException ||t.getCause() instanceof ConnectException || t.getCause() instanceof java.net.UnknownHostException
                || t.getMessage() == null) {

            DialogManager.showMsgPopup(this,getString(R.string.no_internet));

        } else if (t.getCause() instanceof java.net.SocketTimeoutException) {


            DialogManager.showMsgPopup(this,getString(R.string.connect_time_out));

        } else {
            DialogManager.showMsgPopup(this,getString(R.string.connect_time_out));

        }

    }
    public  void setLanguage(String languageCode) {

        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

//        try{
//            Resources res = getBaseContext().getResources();
//            // Change locale settings in the app.
//            DisplayMetrics dm = res.getDisplayMetrics();
//            Configuration conf = res.getConfiguration();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                conf.setLocale(new Locale(languageCode.toLowerCase())); // API 17+ only.
//            }
//            // Use conf.locale = new Locale(...) if targeting lower versions
//            res.updateConfiguration(conf, dm);
//        }catch (Exception e){
//
//        }

    }


}



