package com.example.lw_pc.tribblekill.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.model.Follow;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.model.User;
import com.example.lw_pc.tribblekill.ui.activity.PersonalPageActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LW-PC on 2016/5/22.
 */
public class FollowersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Follow> mFollows;
    private Follow mFollow;
    private Shot mShot;
    private View.OnClickListener mListener;

    public FollowersAdapter(Context context, List<Follow> follows, Shot shot, View.OnClickListener listener) {
        mContext = context;
        mFollows = follows;
        mShot = shot;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        mFollow = mFollows.get(position);

        Picasso.with(mContext)
                .load(mFollow.getFollower().getAvatar_url())
                .into(itemViewHolder.mAvatar);
        itemViewHolder.mName.setText(mFollow.getFollower().getName());
        itemViewHolder.mBio.setText(Html.fromHtml(mFollow.getFollower().getBio()));

        itemViewHolder.itemView.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mFollows.size();
    }

    public void setFollowers(List<Follow> follows) {
        mFollows = follows;
        notifyDataSetChanged();
    }

    public List<Follow> getFollowers() {
        return mFollows;
    }

    public Follow getItemData(int position) {
        return getFollowers().get(position);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mAvatar;
        private TextView mName;
        private TextView mBio;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mAvatar = (CircleImageView) itemView.findViewById(R.id.avatar);
            mName = (TextView) itemView.findViewById(R.id.id);
            mBio = (TextView) itemView.findViewById(R.id.bio);
        }


    }


}
