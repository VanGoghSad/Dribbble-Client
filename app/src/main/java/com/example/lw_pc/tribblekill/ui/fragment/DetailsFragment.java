package com.example.lw_pc.tribblekill.ui.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.model.Shot;
import com.squareup.picasso.Picasso;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.lw_pc.tribblekill.R.id.count_bucket;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    public static final String SHOT = "shot";

    private Toolbar mToolbar;
    private ImageView mHeader;
    private CircleImageView mAvatar;
    private TextView mShotTitle;
    private TextView mAuthor;
    private TextView mDescription;
    private TextView mDate;

    private TextView views;
    private TextView likes;
    private TextView comments;
    private TextView buckets;
    private TextView attachments;

    private TextView iconViews;
    private TextView iconLike;
    private TextView iconComment;
    private TextView iconBucket;
    private TextView iconAttachment;

    private Shot shot;


    public static Fragment newInstance(Shot shot) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SHOT, shot);
        Fragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
        mHeader = (ImageView) v.findViewById(R.id.header_image);
        mAvatar = (CircleImageView) v.findViewById(R.id.avatar);
        mShotTitle = (TextView) v.findViewById(R.id.shot_title);
        mAuthor = (TextView) v.findViewById(R.id.user);
        mDescription = (TextView) v.findViewById(R.id.description);
        mDate = (TextView) v.findViewById(R.id.date);

        views = (TextView) v.findViewById(R.id.views_count);
        likes = (TextView) v.findViewById(R.id.like_count);
        comments = (TextView) v.findViewById(R.id.count_comment);
        buckets = (TextView) v.findViewById(count_bucket);
        attachments = (TextView) v.findViewById(R.id.count_attachment);

        iconViews = (TextView) v.findViewById(R.id.icon_views);
        iconLike = (TextView) v.findViewById(R.id.icon_like);
        iconComment = (TextView) v.findViewById(R.id.icon_comment);
        iconBucket = (TextView) v.findViewById(R.id.icon_bucket);
        iconAttachment = (TextView) v.findViewById(R.id.icon_attachment);



        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shot = (Shot) getArguments().getSerializable(SHOT);
        init();
    }

    private void init() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            // 给左上角图标的左边加上一个返回的图标
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Picasso.with(getActivity())
                .load(shot.getImages().getHidpi())
                .into(mHeader);
        Picasso.with(getActivity())
                .load(shot.getUser().getAvatar_url())
                .into(mAvatar);
        mShotTitle.setText(shot.getTitle());
        mAuthor.setText(shot.getUser().getName());
        mDate.setText(timeFormat(shot.getCreated_at()));
        views.setText(getContext().getString(R.string.views, shot.getViews_count()));

        iconViews.setText(R.string.icon_view);
        iconLike.setText(R.string.icon_like);
        iconComment.setText(R.string.icon_comment);
        iconBucket.setText(R.string.icon_bucket);
        iconAttachment.setText(R.string.icon_attachment);

        likes.setText(getContext().getString(R.string.likes, shot.getLikes_count()));
        comments.setText(getContext().getString(R.string.comments, shot.getComments_count()));
        buckets.setText(getContext().getString(R.string.buckets, shot.getBuckets_count()));
        attachments.setText(getContext().getString(R.string.attachments, shot.getAttachments_count()));
        if (shot.getDescription() != null) {
            mDescription.setText(Html.fromHtml(shot.getDescription()));
        }
        mDescription.setMovementMethod(LinkMovementMethod.getInstance());

    }

    /**
     * format the time string
     * @param time
     * @return
     */
    private String timeFormat(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", new Locale("en_US"));
        SimpleDateFormat to = new SimpleDateFormat("MMM dd, yyyy", new Locale("en_US"));
        Date d = null;
        try {
            d = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  to.format(d);
    }



}
