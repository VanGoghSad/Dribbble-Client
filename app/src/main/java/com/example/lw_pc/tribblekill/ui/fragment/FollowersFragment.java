package com.example.lw_pc.tribblekill.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.model.Follow;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.ui.activity.DetailActivity;
import com.example.lw_pc.tribblekill.ui.activity.PersonalPageActivity;
import com.example.lw_pc.tribblekill.ui.adapter.FollowersAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowersFragment extends Fragment implements View.OnClickListener{
    private int page = 1;

    private RecyclerView mRecyclerView;
    private FollowersAdapter mFollowersAdapter;

    private Shot shot;



    public static Fragment newInstance(Shot shot) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailsFragment.SHOT, shot);
        Fragment fragment = new FollowersFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_followers, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.followersRecyclerView);
        shot = (Shot) getArguments().getSerializable(DetailsFragment.SHOT);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
        mFollowersAdapter = new FollowersAdapter(getActivity(), new ArrayList<Follow>(), shot, this);
        mRecyclerView.setAdapter(mFollowersAdapter);

        Api api = DribbbleApi.getDribbbleApi();
        api.getFollowers(shot.getUser().getId()).enqueue(new Callback<List<Follow>>() {
            @Override
            public void onResponse(Response<List<Follow>> response, Retrofit retrofit) {
                mFollowersAdapter.setFollowers(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        final int position = mRecyclerView.getChildAdapterPosition(v);
        if (RecyclerView.NO_POSITION != position) {
            Follow follow = mFollowersAdapter.getItemData(position);
            Shot shot = follow.getFollower().toShot();
            PersonalPageActivity.start(getActivity(), shot);
        }
    }
}
