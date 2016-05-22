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

        /*if (image.isAnimated()) {
            Picasso.with(mContext)
                    .load(R.drawable.animated)
                    .into(itemViewHolder.animated);

        } else {
            itemViewHolder.animated.setVisibility(View.INVISIBLE);
        }*/
        Picasso.with(mContext)
                .load(image.getImages().getNormal())
                .into(itemViewHolder.shotImage);



        itemViewHolder.itemView.setOnClickListener(mListener);
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView shotImage;
        private ImageView animated;

        private ItemViewHolder(View itemView) {
            super(itemView);
            shotImage = (ImageView) itemView.findViewById(R.id.shotsImage);
            animated = (ImageView) itemView.findViewById(R.id.isGif);
        }
    }

    @Override
    public int getItemCount() {
        return mShots.size();
    }

    public void addShots(List<Shot> shots) {
        for (Shot shot:shots) {
            mShots.add(shot);
        }
        notifyDataSetChanged();
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
