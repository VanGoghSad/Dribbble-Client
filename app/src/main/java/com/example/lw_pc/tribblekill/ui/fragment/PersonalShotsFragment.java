package com.example.lw_pc.tribblekill.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.ui.activity.DetailActivity;
import com.example.lw_pc.tribblekill.ui.adapter.ShotsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalShotsFragment extends Fragment implements View.OnClickListener{
    private static final int SPAN_COUNT = 2;
    private int page = 1;
    private int[] lastVisibleItem;

    private RecyclerView mRecyclerView;
    private ShotsAdapter mShotsAdapter;
    private Shot shot;




    public static Fragment newInstance(Shot shot) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailsFragment.SHOT, shot);
        Fragment fragment = new PersonalShotsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal_shots, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.ShotsRecyclerView);
        shot = (Shot) getArguments().getSerializable(DetailsFragment.SHOT);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        loadData();
    }

    private void init() {
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
    }

    private void loadData() {
        Api api = DribbbleApi.getDribbbleApi();
        if (shot.getUser().getType().equals("Team")) {
            api.getTeamShots(shot.getUser().getId(), page).enqueue(new Callback<List<Shot>>() {
                @Override
                public void onResponse(Response<List<Shot>> response, Retrofit retrofit) {
                    mShotsAdapter.setShots(response.body());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        } else if (shot.getUser().getType().equals("Player")) {
            api.getPersonalShots(shot.getUser().getId(), page).enqueue(new Callback<List<Shot>>() {
                @Override
                public void onResponse(Response<List<Shot>> response, Retrofit retrofit) {
                    mShotsAdapter.setShots(response.body());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }


    private void loadMore() {
        Api api = DribbbleApi.getDribbbleApi();
        if (shot.getUser().getType().equals("Team")) {
            api.getTeamShots(shot.getUser().getId(), ++page).enqueue(new Callback<List<Shot>>() {
                @Override
                public void onResponse(Response<List<Shot>> response, Retrofit retrofit) {
                    mShotsAdapter.addShots(response.body());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        } else if (shot.getUser().getType().equals("Player")) {
            api.getPersonalShots(shot.getUser().getId(), ++page).enqueue(new Callback<List<Shot>>() {
                @Override
                public void onResponse(Response<List<Shot>> response, Retrofit retrofit) {
                    mShotsAdapter.addShots(response.body());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }

    }


    @Override
    public void onClick(View v) {
        final int position = mRecyclerView.getChildAdapterPosition(v);
        if (RecyclerView.NO_POSITION != position) {
            Shot shot_1 = mShotsAdapter.getItemData(position);
            DetailActivity.start(getActivity(), shot_1);
        }
    }
}
