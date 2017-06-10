package com.zencode.issuelist.utils;

import com.zencode.issuelist.entity.IssueListResponse;

import java.text.SimpleDateFormat;
import java.util.HashMap;


public class AppConstants {

    public static final String shared_pref_name = "Issue_List_Prefernce";
    public static  String BASE_URL =   "https://api.github.com/repos/crashlytics/secureudid/";
    public static final String ISSUES_LIST = "ISSUES_LIST";
    public static final String COMMENT_LIST = "COMMENT_LIST";
    public static  IssueListResponse SELECTED_ISSUE ;
}
