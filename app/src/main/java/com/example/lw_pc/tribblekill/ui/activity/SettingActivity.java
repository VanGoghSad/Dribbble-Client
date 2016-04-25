package com.example.lw_pc.tribblekill.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.ui.fragment.DetailsFragment;
import com.example.lw_pc.tribblekill.ui.fragment.LoginFragment;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportFragmentManager().beginTransaction().add(R.id.container, LoginFragment.newInstance()).commit();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
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
