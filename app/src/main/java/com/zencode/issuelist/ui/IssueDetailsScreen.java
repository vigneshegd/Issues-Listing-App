package com.zencode.issuelist.ui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zencode.issuelist.R;
import com.zencode.issuelist.adapter.CommentAdapter;
import com.zencode.issuelist.apiinterface.APIRequestHandler;
import com.zencode.issuelist.entity.IssueCmtsResponse;
import com.zencode.issuelist.entity.IssueListResponse;
import com.zencode.issuelist.main.BaseActivity;
import com.zencode.issuelist.utils.AppConstants;
import com.zencode.issuelist.utils.GlobalMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.zencode.issuelist.utils.GlobalMethods.getCommentIssue;
import static com.zencode.issuelist.utils.GlobalMethods.getLocalTime;
import static com.zencode.issuelist.utils.GlobalMethods.setListViewHeightBasedOnChildren;

/**
 * Created by Vicky N on 10/6/2017.
 */

public class IssueDetailsScreen extends BaseActivity {

    @BindView(R.id.title)
    TextView m_txtTitle;
    @BindView(R.id.username)
    TextView m_txtuser;
    @BindView(R.id.description)
    TextView m_txtDesc;
    @BindView(R.id.updated_date)
    TextView m_txtDate;
    @BindView(R.id.add_photo)
    CircleImageView m_txtuserAva;
    @BindView(R.id.list_cmt)
    ListView m_listCmts;

    @BindView(R.id.scrollView)
    ScrollView m_scrList;
    List<IssueCmtsResponse> m_cmtList =new ArrayList<IssueCmtsResponse>();
    HashMap<String, List<IssueCmtsResponse>> m_hashListCmt=new HashMap<>();
    IssueListResponse m_SelectedIssue;
    String m_cmtUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_issue_detail);
        ButterKnife.bind(this);

        m_SelectedIssue=AppConstants.SELECTED_ISSUE;
//        m_listCmts.setNestedScrollingEnabled(false);
         m_cmtUrl=m_SelectedIssue.getComments_url();
        m_txtTitle.setText(m_SelectedIssue.getTitle());
        m_txtDesc.setText(" "+m_SelectedIssue.getBody());
        m_txtuser.setText(" "+getString(R.string.user)+":"+m_SelectedIssue.getUser().getLogin());
        String updatedTime=  getLocalTime(this,m_SelectedIssue.getUpdated_at());
        String url=m_SelectedIssue.getUser().getAvatar_url();


        Glide.with(this)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(m_txtuserAva);
        m_txtDate.setText(" "+getString(R.string.updated_at)+":"+updatedTime);

        m_hashListCmt=getCommentIssue(this);
        if(m_hashListCmt!=null&&m_hashListCmt.size()>0){
            m_cmtList=m_hashListCmt.get(m_cmtUrl);
            if(m_cmtList!=null&&m_cmtList.size()>0){
                setAdapter();
            }else {
                m_cmtList=new ArrayList<>();
            }
        }else {
            m_hashListCmt=new HashMap<>();
        }
        m_scrList.requestChildFocus(m_txtTitle,m_txtTitle);
        callAPI();

    }
    private void callAPI() {
        APIRequestHandler.getInstance().getCommentList(m_cmtUrl,IssueDetailsScreen.this);

    }
    @Override
    public void onRequestSuccess(Object responseObj) {
        super.onRequestSuccess(responseObj);

        m_cmtList = (List<IssueCmtsResponse>) responseObj;
        if(m_hashListCmt==null){
            m_hashListCmt=new HashMap<>();
        }
        m_hashListCmt.put(m_cmtUrl,m_cmtList);
        GlobalMethods.storeValuetoPreference(this,GlobalMethods.ARRAY_LIST_PREFERENCE, AppConstants.COMMENT_LIST, m_hashListCmt);
        if(m_cmtList!=null&&m_cmtList.size()>0){
            setAdapter();
        }



    }
    CommentAdapter commentAdapter;
    private void setAdapter() {

        commentAdapter =new CommentAdapter(IssueDetailsScreen.this,m_cmtList);
        m_listCmts.setAdapter(commentAdapter);
        setListViewHeightBasedOnChildren(m_listCmts);
    }
}