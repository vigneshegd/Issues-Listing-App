package com.zencode.issuelist.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zencode.issuelist.entity.IssueCmtsResponse;
import com.zencode.issuelist.entity.IssueListResponse;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class GlobalMethods {
    public static int STRING_PREFERENCE = 1;
    public static int INT_PREFERENCE = 2;
    public static int BOOLEAN_PREFERENCE = 3;
    public static int ARRAY_LIST_PREFERENCE = 4;
    public static int LONG_PREFERENCE = 5;


    public static void storeValuetoPreference(Context context, int preference, String key, Object value) {
        SharedPreferences sharedPreference = context.getSharedPreferences(AppConstants.shared_pref_name,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreference.edit();
        if (preference == STRING_PREFERENCE) {
            edit.putString(key, (String) value);
        }
        if (preference == INT_PREFERENCE) {
            edit.putInt(key, (Integer) value);
        }
        if (preference == BOOLEAN_PREFERENCE) {
            edit.putBoolean(key, (Boolean) value);
        }
        if (preference == ARRAY_LIST_PREFERENCE) {
            Gson gson = new Gson();
            String arrayList1 = gson.toJson(value);
            edit.putString(key, arrayList1);
        }
        edit.commit();

    }



    public static Object getValueFromPreference(Context context, int preference, String key) {
        SharedPreferences sharedPreference = context.getSharedPreferences(AppConstants.shared_pref_name,
                Context.MODE_PRIVATE);

        if (preference == STRING_PREFERENCE) {
            return (Object) sharedPreference.getString(key, "");
        }
        if (preference == INT_PREFERENCE) {
            return (Object) sharedPreference.getInt(key, 0);
        }
        if (preference == BOOLEAN_PREFERENCE) {
            return (Object) sharedPreference.getBoolean(key, false);
        }
        if (preference == ARRAY_LIST_PREFERENCE) {


            String arrayList = sharedPreference.getString(key, null);

            return (Object) arrayList;
        }

        return null;

    }


    public static ArrayList<IssueListResponse> getOfflineIssuesList(Context mcontext) {
        String arrayList = (String) GlobalMethods.getValueFromPreference(mcontext, GlobalMethods.ARRAY_LIST_PREFERENCE,
                AppConstants.ISSUES_LIST);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<IssueListResponse>>() {
        }.getType();
        ArrayList<IssueListResponse> arrayList1 = gson.fromJson(arrayList, type);

        return arrayList1;
    }
    public static HashMap<String, List<IssueCmtsResponse>> getCommentIssue(Context mcontext) {
        String arrayList = (String) GlobalMethods.getValueFromPreference(mcontext, GlobalMethods.ARRAY_LIST_PREFERENCE,
                AppConstants.COMMENT_LIST);
        Gson gson = new Gson();
        Type type = new TypeToken<  HashMap<String, ArrayList<IssueCmtsResponse>>>() {
        }.getType();
        HashMap<String, List<IssueCmtsResponse>> arrayList1 = gson.fromJson(arrayList, type);

        return arrayList1;
    }

    public static String getLocalTime(Context ctx,String inputDate) {
        Date dateobj;
        dateobj = null;
        String lv_dateFormateInLocalTimeZone = "";

        try {
            SimpleDateFormat Inputformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.US);;

            dateobj = Inputformat.parse(inputDate);
            SimpleDateFormat displayFormat = new SimpleDateFormat("MM-dd-yyyy",Locale.US);


            displayFormat.setTimeZone(TimeZone.getDefault());
            lv_dateFormateInLocalTimeZone = displayFormat.format(dateobj);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lv_dateFormateInLocalTimeZone;
    }

    public static String getLocalTime(String inputDate, String outputFormat) {
        Date dateobj;
        String lv_dateFormateInLocalTimeZone = "";
        try {

            SimpleDateFormat Inputformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dateobj = Inputformat.parse(inputDate);
            SimpleDateFormat displayFormat = new SimpleDateFormat(outputFormat);


            displayFormat.setTimeZone(TimeZone.getDefault());
            lv_dateFormateInLocalTimeZone = displayFormat.format(dateobj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lv_dateFormateInLocalTimeZone;
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }






}