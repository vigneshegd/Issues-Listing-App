package com.zencode.issuelist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zencode.issuelist.R;
import com.zencode.issuelist.entity.IssueCmtsResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.zencode.issuelist.utils.GlobalMethods.getLocalTime;

/**
 * Created by Vicky N on 6/6/2017.
 */

public class CommentAdapter extends BaseAdapter {
    LayoutInflater m_layoutInfalter = null;
    private Holder mHolder;

    private List<IssueCmtsResponse> mCmtList = new ArrayList<>();


    Context mContext;
    public CommentAdapter(Context context, List<IssueCmtsResponse> List) {
        mCmtList = List;
        mContext = context;
        m_layoutInfalter = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    class Holder {

        @BindView(R.id.title)
        TextView m_txtTitle;

        @BindView(R.id.description)
        TextView m_txtDesc;

        @BindView(R.id.add_photo)
        CircleImageView m_txtuserAva;


        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getCount() {
        return mCmtList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        view = m_layoutInfalter.inflate(R.layout.comment_items, parent, false);
        mHolder = new Holder(view);
        IssueCmtsResponse mEntity = mCmtList.get(position);
        String updatedTime=  getLocalTime(mContext,mEntity.getUpdated_at());
        mHolder.m_txtTitle.setText(mEntity.getUser().getLogin()+" "+mContext.getString(R.string.commended_on)+" "+updatedTime);
        mHolder.m_txtDesc.setText(" "+mEntity.getBody());
        String url=mEntity.getUser().getAvatar_url();
        Glide.with(mContext)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(mHolder.m_txtuserAva);

        return  view;
    }

}
