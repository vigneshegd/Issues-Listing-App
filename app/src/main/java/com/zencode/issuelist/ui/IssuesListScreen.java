package com.zencode.issuelist.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.zencode.issuelist.R;
import com.zencode.issuelist.adapter.IssueAdapter;
import com.zencode.issuelist.apiinterface.APIRequestHandler;
import com.zencode.issuelist.entity.IssueListResponse;
import com.zencode.issuelist.main.BaseActivity;
import com.zencode.issuelist.utils.AppConstants;
import com.zencode.issuelist.utils.CustomSwipeToRefresh;
import com.zencode.issuelist.utils.GlobalMethods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zencode.issuelist.utils.GlobalMethods.getOfflineIssuesList;

/**
 * Created by admin on 6/10/2017.
 */

public class IssuesListScreen extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler_issue)
    RecyclerView m_listIssuer;
    @BindView(R.id.swipe_refresh_layout)
    CustomSwipeToRefresh swipeRefreshLayout;
    List<IssueListResponse> m_ListIssue =new ArrayList<IssueListResponse> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_issue_list);

        ButterKnife.bind(this);
        initializePullToRfresh();
        m_ListIssue=getOfflineIssuesList(this);
        if(m_ListIssue!=null&&m_ListIssue.size()>0){
            setAdapter();
        }else {
            m_ListIssue=new ArrayList<>();
        }
        callAPI();
    }
    private void initializePullToRfresh() {
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRequestSuccess(Object responseObj) {
        super.onRequestSuccess(responseObj);

        m_ListIssue = (List<IssueListResponse>) responseObj;

        GlobalMethods.storeValuetoPreference(this,GlobalMethods.ARRAY_LIST_PREFERENCE, AppConstants.ISSUES_LIST,m_ListIssue);
        if(m_ListIssue!=null&&m_ListIssue.size()>0){
            setAdapter();
        }


        }
    IssueAdapter issueAdapter;
    private void setAdapter() {

        issueAdapter=new IssueAdapter(IssuesListScreen.this,m_ListIssue);
        GridLayoutManager m_gridLayBarber = new GridLayoutManager(this, 1);
        m_listIssuer.setHasFixedSize(true);
        m_listIssuer.setLayoutManager(m_gridLayBarber);
        m_listIssuer.setAdapter(issueAdapter);
    }

    @Override
    public void onRefresh() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);

            callAPI();
        }
    }

    private void callAPI() {
        APIRequestHandler.getInstance().getIssueList(IssuesListScreen.this);

    }
}


