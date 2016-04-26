package com.example.lw_pc.tribblekill.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.model.Comment;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.util.MyTextView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LW-PC on 2016/4/26.
 */
public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private View.OnClickListener mListener;
    private List<Comment> mComments;

    public CommentsAdapter(Context context, List<Comment> comments, View.OnClickListener listener) {
        mContext = context;
        mComments = comments;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        final Comment comment = mComments.get(position);

        Picasso.with(mContext)
                .load(comment.getUser().getAvatar_url())
                .into(itemViewHolder.mAvatar);
        itemViewHolder.mId.setText(comment.getUser().getName());

        itemViewHolder.mContent.setText(Html.fromHtml(comment.getBody()));
        itemViewHolder.mTime.setText(timeFormat(comment.getCreated_at()));
        itemViewHolder.iconLike.setText(R.string.icon_like_border);
        itemViewHolder.mLikeCount.setText(mContext.getString(R.string.likes, comment.getLikes_count()));

        itemViewHolder.itemView.setOnClickListener(mListener);
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView mAvatar;
        private MyTextView iconLike;
        private TextView mId;
        private TextView mContent;
        private TextView mTime;
        private TextView mLikeCount;
        private ItemViewHolder(View itemView) {
            super(itemView);
            mAvatar = (CircleImageView) itemView.findViewById(R.id.avatar);
            iconLike  = (MyTextView) itemView.findViewById(R.id.icon_like);
            mId  = (TextView) itemView.findViewById(R.id.id);
            mContent  = (TextView) itemView.findViewById(R.id.content);
            mTime  = (TextView) itemView.findViewById(R.id.publish_time);
            mLikeCount  = (TextView) itemView.findViewById(R.id.like_count);

        }

    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
        notifyDataSetChanged();
    }

    public List<Comment> getComments() {
        return mComments;
    }

    public Comment getItemData(int position) {
        return getComments().get(position);
    }

    /**
     * format comments publish_time
     * @param time
     * @return
     */
    private String timeFormat(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", new Locale("en_US"));
        Date create = null;
        try {
            create = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date now = new Date();
        long nowTime = now.getTime();
        long createTime = create.getTime();
        int result;
        if((nowTime - createTime) / (1000 * 60) < 60) {
            result = (int) ((nowTime - createTime) / (1000 * 60));
            return "about "+ result + " minutes ago";
        } else {
            result = (int) ((nowTime - createTime) / (1000 * 60 * 60));
            return "about "+ result + " hours ago";
        }


    }
}
