package com.example.lw_pc.tribblekill.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.model.Shot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    public static final String SHOT = "shot";

    private Toolbar mToolbar;
    private FloatingActionButton fab;
    private ImageView mHeader;
    private CircleImageView mAvatar;
    private TextView mShotTitle;
    private TextView mAuthor;

    private TextView likes;
    private TextView comments;
    private TextView attachments;
    private TextView views;
    private TextView buckets;


    private Shot shot;
    private List<Map<String, Object>> data_list;
    private int[] icon = {R.drawable.views, R.drawable.views, R.drawable.views, R.drawable.views, R.drawable.views, R.drawable.views};
    private String[] iconName = {"name", "name", "name", "name", "name", "name"};

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
        //fab = (FloatingActionButton) v.findViewById(R.id.fab);
        mHeader = (ImageView) v.findViewById(R.id.header_image);
        mAvatar = (CircleImageView) v.findViewById(R.id.avatar);
        mShotTitle = (TextView) v.findViewById(R.id.shot_title);
        mAuthor = (TextView) v.findViewById(R.id.UserAndDate);

        likes = (TextView) v.findViewById(R.id.likes_text);
        views = (TextView) v.findViewById(R.id.views_text);
        attachments = (TextView) v.findViewById(R.id.attach_text);
        buckets = (TextView) v.findViewById(R.id.buckets_text);
        comments = (TextView) v.findViewById(R.id.comment_text);



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
        mAuthor.setText(getContext().getString(R.string.author, shot.getUser().getName()));
        likes.setText(getContext().getString(R.string.likes, shot.getLikes_count()));
        views.setText(getContext().getString(R.string.views, shot.getViews_count()));
        buckets.setText(getContext().getString(R.string.buckets, shot.getBuckets_count()));
        comments.setText(getContext().getString(R.string.comments, shot.getComments_count()));
        attachments.setText(getContext().getString(R.string.attachments, shot.getAttachments_count()));

    }


}
