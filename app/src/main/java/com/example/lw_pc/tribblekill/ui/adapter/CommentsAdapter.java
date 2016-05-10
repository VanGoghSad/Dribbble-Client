package com.example.lw_pc.tribblekill.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lw_pc.tribblekill.App;
import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.model.Comment;
import com.example.lw_pc.tribblekill.model.Like;
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
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by LW-PC on 2016/4/26.
 */
public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Comment> mComments;
    private Shot mShot;
    private Comment mComment;
    private String mToken;

    public CommentsAdapter(Context context, List<Comment> comments, Shot shot, String token) {
        mContext = context;
        mComments = comments;
        mShot = shot;
        mToken = token;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);
        return new ItemViewHolder(v, new MyViewHolderClick() {
            @Override
            public void onAvatarClick() {

            }

            @Override
            public void onIconClick(int shotId, int commentId, String token, final int position, final MyTextView v) {
                Api api = DribbbleApi.getDribbbleApi();
                if (v.getCurrentTextColor() == mContext.getResources().getColor(R.color.colorPrimary)) {
                    api.likeComment(shotId, commentId, token).enqueue(new Callback<Like>() {
                        @Override
                        public void onResponse(Response<Like> response, Retrofit retrofit) {
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                } else {
                    api.unlikeComment(shotId, commentId, token).enqueue(new Callback<Like>() {
                        @Override
                        public void onResponse(Response<Like> response, Retrofit retrofit) {
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        mComment = mComments.get(position);

        Api api = DribbbleApi.getDribbbleApi();
        api.isLikeComment(mShot.getId(), mComment.getId(), mToken).enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Response<Like> response, Retrofit retrofit) {
                if (response.body() != null) {
                    itemViewHolder.iconLike.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        Picasso.with(mContext)
                .load(mComment.getUser().getAvatar_url())
                .into(itemViewHolder.mAvatar);
        itemViewHolder.mId.setText(mComment.getUser().getName());

        itemViewHolder.mContent.setText(Html.fromHtml(mComment.getBody()));
        itemViewHolder.mTime.setText(timeFormat(mComment.getCreated_at()));
        itemViewHolder.iconLike.setText(R.string.icon_like_border);
        itemViewHolder.mLikeCount.setText(mContext.getString(R.string.likes, mComment.getLikes_count()));

        itemViewHolder.bind(mShot.getId(), mComment.getId(), mToken, mComment);



    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private int mShotId;
        private int mCommentId;
        private String mToken;
        private Comment mComment;

        private CircleImageView mAvatar;
        private MyTextView iconLike;
        private TextView mId;
        private TextView mContent;
        private TextView mTime;
        private TextView mLikeCount;
        private MyViewHolderClick myViewHolderClick;
        private ItemViewHolder(View itemView, MyViewHolderClick listener) {
            super(itemView);
            mAvatar = (CircleImageView) itemView.findViewById(R.id.avatar);
            iconLike  = (MyTextView) itemView.findViewById(R.id.icon_like);
            mId  = (TextView) itemView.findViewById(R.id.id);
            mContent  = (TextView) itemView.findViewById(R.id.content);
            mTime  = (TextView) itemView.findViewById(R.id.publish_time);
            mLikeCount  = (TextView) itemView.findViewById(R.id.like_count);
            myViewHolderClick = listener;
            iconLike.setOnClickListener(this);
            mAvatar.setOnClickListener(this);

        }

        public void bind(int shotId, int commentId, String token, Comment comment) {
            mShotId = shotId;
            mCommentId = commentId;
            mToken = token;
            mComment = comment;
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.icon_like:
                    if (iconLike.getCurrentTextColor() == mContext.getResources().getColor(R.color.colorPrimary)) {
                        iconLike.setTextColor(mId.getCurrentTextColor());
                        mLikeCount.setText(mContext.getString(R.string.likes, Integer.parseInt(mLikeCount.getText().toString()) - 1));
                    } else {
                        iconLike.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                        mLikeCount.setText(mContext.getString(R.string.likes, Integer.parseInt(mLikeCount.getText().toString()) + 1));
                    }
                    myViewHolderClick.onIconClick(mShotId, mCommentId, mToken, getLayoutPosition(), iconLike);
                    break;

            }
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


    private interface MyViewHolderClick {
        public void onAvatarClick();

        public void onIconClick(int shotId, int commentId, String token, int position, MyTextView v);


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
