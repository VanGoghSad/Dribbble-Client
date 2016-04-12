package com.example.lw_pc.tribblekill.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.model.Shot;
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
        Picasso.with(mContext)
                .load(image.getImages().getHidpi())
                //.placeholder() 占位符
                .into(itemViewHolder.shotImage);


    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView shotImage;
        private ItemViewHolder(View itemView) {
            super(itemView);
            shotImage = (ImageView) itemView.findViewById(R.id.shotsImage);
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
}
