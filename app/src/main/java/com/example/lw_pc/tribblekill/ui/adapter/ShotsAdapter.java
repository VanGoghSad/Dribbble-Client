package com.example.lw_pc.tribblekill.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.util.MyTextView;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by LW-PC on 2016/4/11.
 * shots RecyclerView 适配器
 */
public class ShotsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Shot> mShots;
    private Context mContext;
    private View.OnClickListener mListener;


    public ShotsAdapter(Context context, List<Shot> shots, View.OnClickListener listener) {
        mContext = context;
        mShots = shots;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.items_shots, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        final Shot image = mShots.get(position);

        if (image.isAnimated()) {
            Picasso.with(mContext)
                    .load(R.drawable.animated)
                    .into(itemViewHolder.animated);

        } else {
            itemViewHolder.animated.setVisibility(View.INVISIBLE);
        }
        Picasso.with(mContext)
                .load(image.getImages().getHidpi())
                .into(itemViewHolder.shotImage);

        itemViewHolder.iconLike.setText(R.string.icon_like);
        itemViewHolder.iconComment.setText(R.string.icon_comment);
        itemViewHolder.iconView.setText(R.string.icon_view);

        itemViewHolder.likes.setText(mContext.getString(R.string.likes, image.getLikes_count()));
        itemViewHolder.comments.setText(mContext.getString(R.string.comments, image.getComments_count()));
        itemViewHolder.views.setText(mContext.getString(R.string.views, image.getViews_count()));

        itemViewHolder.itemView.setOnClickListener(mListener);
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView shotImage;
        private ImageView animated;
        private MyTextView iconLike;
        private MyTextView iconComment;
        private MyTextView iconView;
        private TextView likes;
        private TextView comments;
        private TextView views;

        private ItemViewHolder(View itemView) {
            super(itemView);
            shotImage = (ImageView) itemView.findViewById(R.id.shotsImage);
            animated = (ImageView) itemView.findViewById(R.id.isGif);
            iconLike = (MyTextView) itemView.findViewById(R.id.icon_likes);
            iconComment = (MyTextView) itemView.findViewById(R.id.icon_comments);
            iconView = (MyTextView) itemView.findViewById(R.id.icon_views);
            likes = (TextView) itemView.findViewById(R.id.likes_count);
            comments = (TextView) itemView.findViewById(R.id.comment_count);
            views = (TextView) itemView.findViewById(R.id.views_count);

        }
    }

    @Override
    public int getItemCount() {
        return mShots.size();
    }

    public void setShots(List<Shot> shots) {
        mShots = shots;
        notifyDataSetChanged();
    }

    public List<Shot> getShots() {
        return mShots;
    }

    public Shot getItemData(int position) {
        return getShots().get(position);
    }
}
