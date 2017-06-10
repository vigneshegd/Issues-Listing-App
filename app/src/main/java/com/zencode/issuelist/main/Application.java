package com.zencode.issuelist.main;

import android.content.Context;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;

public class Application extends android.app.Application {
    static Context mAppContext;
    private static boolean activityVisible;

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityStoped() {
        activityVisible = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseCrash.report(new Exception("My first Android non-fatal error"));

        Application.mAppContext = getApplicationContext();
        Thread.setDefaultUncaughtExceptionHandler(mUncaughtExceptionHandler);


    }



    public static Context getAppContext() {

        return Application.mAppContext;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
//        MultiDex.install(this);
    }

    private UncaughtExceptionHandler mUncaughtExceptionHandler = new UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread thread, Throwable exception) {
            exception.printStackTrace();
//                    FirebaseCrash.report(new Exception(exception.toString()));

//             restartApp();
//            android.os.Process.killProcess(android.os.Process.myPid());

        }
    };



    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    @SuppressWarnings("unused")
                    boolean deleteResult = deleteDir(new File(appDir, s));
                }
            }
        }

    }

    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }




}
