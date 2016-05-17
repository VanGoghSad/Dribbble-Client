package com.example.lw_pc.tribblekill.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lw_pc.tribblekill.App;
import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.model.Like;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.ui.fragment.DetailsFragment;
import com.example.lw_pc.tribblekill.util.MyTextView;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class DetailActivity extends AppCompatActivity {
    private App app;
    private Shot shot;
    MenuItem filterMenuItem;
    MyTextView icon_like;

    private boolean isLike;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
        shot = (Shot) getIntent().getSerializableExtra(DetailsFragment.SHOT);
        getSupportFragmentManager().beginTransaction().add(R.id.container, DetailsFragment.newInstance(shot)).commit();

        app = (App) getApplication();
        token = app.sharedPreferences.getString("access_token", "");


    }

    public static void start(Context context, Shot shot) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailsFragment.SHOT, shot);
        context.startActivity(intent);
    }


    /** 返回图标点击事件 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;




        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final Api api = DribbbleApi.getDribbbleApi();
        api.isLikeShot(shot.getId(), token).enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Response<Like> response, Retrofit retrofit) {
                isLike = response.body() != null;
                if (isLike) {
                    icon_like.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        getMenuInflater().inflate(R.menu.shot_toolbar, menu);

        filterMenuItem = menu.findItem(R.id.like);
        icon_like = (MyTextView) filterMenuItem.getActionView().findViewById(R.id.icon_like_actionbar);



        filterMenuItem.getActionView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLike) {
                    api.unlikeShot(shot.getId(), token).enqueue(new Callback<Like>() {
                        @Override
                        public void onResponse(Response<Like> response, Retrofit retrofit) {
                            isLike = false;
                            icon_like.setTextColor(getResources().getColor(R.color.white));
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                } else {
                    api.likeShot(shot.getId(), token).enqueue(new Callback<Like>() {
                        @Override
                        public void onResponse(Response<Like> response, Retrofit retrofit) {
                            if (response.body() != null) {
                                isLike = true;
                                icon_like.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                }



            }
        });
        return true;
    }


}
