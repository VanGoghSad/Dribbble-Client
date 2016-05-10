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
import android.widget.Toast;

import com.example.lw_pc.tribblekill.App;
import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.model.Like;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.ui.fragment.DetailsFragment;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class DetailActivity extends AppCompatActivity {
    private App app;
    private Shot shot;

    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

            case R.id.like:
                Api api = DribbbleApi.getDribbbleApi();
                api.likeShot(shot.getId(), token).enqueue(new Callback<Like>() {
                    @Override
                    public void onResponse(Response<Like> response, Retrofit retrofit) {
                        //点赞shot
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shot_toolbar, menu);
        return true;
    }


}
