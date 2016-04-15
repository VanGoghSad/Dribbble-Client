package com.example.lw_pc.tribblekill.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.ui.activity.DetailActivity;
import com.example.lw_pc.tribblekill.ui.adapter.ShotsAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{
    private static final int SPAN_COUNT = 2;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ShotsAdapter mShotsAdapter;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    public static Fragment newInstance() {
        return new ListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.ShotsRecyclerView);
        avLoadingIndicatorView = (AVLoadingIndicatorView) v.findViewById(R.id.loading);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.srl_shots_list);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        loadData();
    }

    private void init() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL));
        mShotsAdapter = new ShotsAdapter(getActivity(), new ArrayList<Shot>(), this);
        mRecyclerView.setAdapter(mShotsAdapter);
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        Api api = DribbbleApi.getDribbbleApi();
        api.getShots().enqueue(new Callback<List<Shot>>() {
            @Override
            public void onResponse(Response<List<Shot>> response, Retrofit retrofit) {
                mShotsAdapter.setShots(response.body());
                avLoadingIndicatorView.setVisibility(View.GONE);
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
            Shot shot = mShotsAdapter.getItemData(position);
            DetailActivity.start(getActivity(), shot);
        }
    }

    @Override
    public void onRefresh() {
        loadData();
        hideProgress();
    }

    public void hideProgress() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
