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
public class ListFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    protected boolean isVisible;
    protected static final int SPAN_COUNT = 2;
    protected int page = 1;
    protected int[] lastVisibleItem;

    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected ShotsAdapter mShotsAdapter;
    protected AVLoadingIndicatorView avLoadingIndicatorView;

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
    }

    protected void init() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mShotsAdapter = new ShotsAdapter(getActivity(), new ArrayList<Shot>(), this);
        mRecyclerView.setAdapter(mShotsAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (lastVisibleItem != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem[1] + 1 == mShotsAdapter.getItemCount()) {
                        loadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
            }
        });
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
    }

    protected void loadData() {
        Api api = DribbbleApi.getDribbbleApi();
        api.getShots(1).enqueue(new Callback<List<Shot>>() {
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

    protected void loadMore() {
        Api api = DribbbleApi.getDribbbleApi();
        api.getShots(++page).enqueue(new Callback<List<Shot>>() {
            @Override
            public void onResponse(Response<List<Shot>> response, Retrofit retrofit) {
                mShotsAdapter.addShots(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }


    /**
     * 仅当Fragment可见时加载数据
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            showData();
        }
    }

    protected void showData() {
        if(isVisible) {
            isVisible = false;
            loadData();
        }
    }

    /**
     * item点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        final int position = mRecyclerView.getChildAdapterPosition(v);
        if (RecyclerView.NO_POSITION != position) {
            Shot shot = mShotsAdapter.getItemData(position);
            DetailActivity.start(getActivity(), shot);
        }
    }

   /**
     * 页面刷新事件
     */
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        loadData();
        page = 1;
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
