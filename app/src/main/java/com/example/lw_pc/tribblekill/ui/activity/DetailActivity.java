package com.example.lw_pc.tribblekill.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.ui.fragment.DetailsFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Shot shot = (Shot) getIntent().getSerializableExtra(DetailsFragment.SHOT);
        getSupportFragmentManager().beginTransaction().add(R.id.container, DetailsFragment.newInstance(shot)).commit();

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
}
