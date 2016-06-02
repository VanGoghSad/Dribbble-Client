package com.example.lw_pc.tribblekill.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lw_pc.tribblekill.App;
import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.core.UserInfo;
import com.example.lw_pc.tribblekill.model.Login;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.model.User;
import com.example.lw_pc.tribblekill.ui.adapter.ViewPagerAdapter;
import com.example.lw_pc.tribblekill.ui.fragment.FollowingShotsFragment;
import com.example.lw_pc.tribblekill.ui.fragment.ListFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Observer {
    public static final String USER = "user";

    private App mApp;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private SearchView searchView;
    private NavigationView mNavigationView;

    private ImageView mAvatar;
    private TextView mName;


    private List<Fragment> list_fragment;
    private List<String> list_title;
    private ListFragment listFragment;
    private FollowingShotsFragment followingShotsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApp = (App) getApplication();
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        searchView = (SearchView) findViewById(R.id.action_search);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        //Observer 负责登录成功后更新UI
        UserInfo.getInstance().getInfo().addObserver(this);
        UserInfo.getInstance().getOut().addObserver(this);

        initNavigation();
        loadData();
        //UserInfo.getInstance().getInfo().notifyObservers();
    }

    private void initNavigation() {
        drawer = (DrawerLayout) findViewById(R.id.container);
        toggle = new ActionBarDrawerToggle(this, drawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        View headerView = mNavigationView.inflateHeaderView(R.layout.nav_header_main);
        mAvatar = (ImageView) headerView.findViewById(R.id.imageView);
        mName = (TextView) headerView.findViewById(R.id.name);

        if (mApp.sharedPreferences.getString("isLogin", "").equals("true")) {
            Picasso.with(this)
                    .load(mApp.sharedPreferences.getString("avatar_url", ""))
                    .into(mAvatar);
            mName.setText(mApp.sharedPreferences.getString("name", ""));

            Api api = DribbbleApi.getDribbbleApi();
            api.getUser(mApp.sharedPreferences.getString("access_token", "")).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Response<User> response, Retrofit retrofit) {
                    final Shot shot = new Shot();
                    shot.setUser(response.body());
                    mAvatar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PersonalPageActivity.start(MainActivity.this, shot);
                        }
                    });
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });


        } else {
            mAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginActivity.start(MainActivity.this);
                }
            });
        }
    }

    private void loadData() {
        list_fragment = new ArrayList<>();
        listFragment = new ListFragment();
        followingShotsFragment = new FollowingShotsFragment();
        list_fragment.add(listFragment);
        list_fragment.add(followingShotsFragment);

        list_title = new ArrayList<>();
        String[] tabTitle = getResources().getStringArray(R.array.tabLayout_item);
        for (String s : tabTitle) {
            list_title.add(s);
        }

        adapter = new ViewPagerAdapter(this.getSupportFragmentManager(), list_fragment, list_title);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.setting) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 登陆成功后update UI
     * @param observable
     * @param data
     */

    @Override
    public void update(final Observable observable, Object data) {
        if (observable instanceof Shot) {
            Picasso.with(this)
                    .load(((Shot) observable).getUser().getAvatar_url())
                    .into(mAvatar);
            mName.setText(((Shot) observable).getUser().getName());

            followingShotsFragment.onRefresh();
            mAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonalPageActivity.start(MainActivity.this, (Shot)observable);
                }
            });

        } else if (observable instanceof Login) {
            Picasso.with(this)
                    .load(R.drawable.avatar)
                    .into(mAvatar);
            mAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginActivity.start(MainActivity.this);
                }
            });

            mName.setText("username");
            //followingShotsFragment.onRefresh();
        }
    }
}
