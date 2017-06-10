package com.zencode.issuelist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zencode.issuelist.R;
import com.zencode.issuelist.entity.IssueListResponse;
import com.zencode.issuelist.ui.IssueDetailsScreen;
import com.zencode.issuelist.ui.IssuesListScreen;
import com.zencode.issuelist.utils.AppConstants;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zencode.issuelist.utils.GlobalMethods.getLocalTime;

/**
 * Created by admin on 6/9/2017.
 */

/**
 * Created by user on 11/16/2016.
 */

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.Holder> {

    private List<IssueListResponse> mIssuesList = new ArrayList<>();


    Context mContext;
    public IssueAdapter(Context context, List<IssueListResponse> List) {
        mIssuesList = List;
        mContext = context;

    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_issue, null);
        return new Holder(layoutView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int pos) {
        IssueListResponse mEntity = mIssuesList.get(pos);
        holder.m_txtTitle.setText(mEntity.getTitle());
        holder.m_txtDesc.setText(" "+mEntity.getBody());
        holder.m_txtuser.setText(" "+mContext.getString(R.string.user)+":"+mEntity.getUser().getLogin());
        String updatedTime=  getLocalTime(mContext,mEntity.getUpdated_at());
        String url=mEntity.getUser().getAvatar_url();
        holder.m_txtDate.setText(" "+mContext.getString(R.string.updated_at)+":"+updatedTime);

        Glide.with(mContext)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(holder.m_txtuserAva);
        holder.parentLay.setTag(pos);
        holder.parentLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int currentPos=(int)v.getTag();
               AppConstants.SELECTED_ISSUE = mIssuesList.get(currentPos);
                ((IssuesListScreen)mContext).nextScreen(IssueDetailsScreen.class);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mIssuesList.size();
    }


    class Holder extends RecyclerView.ViewHolder {

        private TextView m_txtTitle,m_txtDesc,m_txtuser,m_txtDate;
        CircleImageView m_txtuserAva;
        LinearLayout parentLay;
        public Holder(View itemView) {
            super(itemView);
            m_txtTitle = (TextView) itemView.findViewById(R.id.title);
            m_txtDesc= (TextView) itemView.findViewById(R.id.description);
            m_txtuser= (TextView) itemView.findViewById(R.id.username);
            m_txtDate= (TextView) itemView.findViewById(R.id.updated_date);
            m_txtuserAva= (CircleImageView) itemView.findViewById(R.id.add_photo);
            parentLay= (LinearLayout) itemView.findViewById(R.id.parent_lay);
        }

    }
}
