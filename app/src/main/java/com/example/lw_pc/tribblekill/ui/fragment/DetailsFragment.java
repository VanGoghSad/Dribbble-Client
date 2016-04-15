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
import android.widget.ImageView;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.model.Shot;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    public static final String SHOT = "shot";

    private Toolbar mToolbar;
    private FloatingActionButton fab;
    private ImageView header;

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
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        header = (ImageView) v.findViewById(R.id.header_image);

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
                .into(header);
    }
}
