package com.example.lw_pc.tribblekill.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.ui.adapter.ViewPagerAdapter;
import com.example.lw_pc.tribblekill.ui.fragment.DetailsFragment;
import com.example.lw_pc.tribblekill.ui.fragment.ListFragment;
import com.example.lw_pc.tribblekill.util.FastBlur;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.blurry.Blurry;

public class PersonalPageActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private CollapsingToolbarLayout mCllapsingToolbarLayout;
    private CircleImageView mAvatar;
    private TextView mUsername;
    private TextView mBio;
    private ImageView mBackgorund;
    private ImageView mBackgroundSource;
    private AppBarLayout mAppBar;


    private Shot shot;
    private List<Fragment> list_fragment;
    private List<String> list_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);

        shot = (Shot) getIntent().getSerializableExtra(DetailsFragment.SHOT);

        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mCllapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mAvatar = (CircleImageView) findViewById(R.id.avatar);
        mUsername = (TextView) findViewById(R.id.username);
        mBio = (TextView) findViewById(R.id.bio);
        mBackgorund = (ImageView) findViewById(R.id.background);

        /*Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.white));*/
        init();
    }

    private void init() {
        setSupportActionBar(mToolBar);
        final ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            // 给左上角图标的左边加上一个返回的图标
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Picasso.with(this)
                .load(shot.getUser().getAvatar_url())
                .into(mAvatar);

        mUsername.setText(shot.getUser().getName());
        mBio.setText(Html.fromHtml(shot.getUser().getBio()));
        mCllapsingToolbarLayout.setTitleEnabled(false);
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (mCllapsingToolbarLayout.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(mCllapsingToolbarLayout)) {
                    if(actionBar != null && actionBar.getTitle() != shot.getUser().getName()) {
                        actionBar.setTitle(shot.getUser().getName());
                    }
                }else{
                    if(actionBar != null) {
                        actionBar.setTitle("");
                    }
                }
            }
        });

        list_fragment = new ArrayList<>();
        list_fragment.add(new ListFragment());
        list_fragment.add(new ListFragment());
        list_fragment.add(new ListFragment());

        list_title = new ArrayList<>();
        String[] tabTitle = getResources().getStringArray(R.array.tabLayout_item);
        for (String s : tabTitle) {
            list_title.add(s);
        }

        adapter = new ViewPagerAdapter(this.getSupportFragmentManager(), list_fragment, list_title);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    public static void start(Context context, Shot shot) {
        Intent intent = new Intent(context, PersonalPageActivity.class);
        intent.putExtra(DetailsFragment.SHOT, shot);
        context.startActivity(intent);
    }

    private void applyBlur() {
        mBackgroundSource.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mBackgroundSource.getViewTreeObserver().removeOnPreDrawListener(this);
                mBackgroundSource.buildDrawingCache();

                Bitmap bmp = mBackgroundSource.getDrawingCache();
                blur(bmp, mBackgorund);
                mBackgorund.setVisibility(View.INVISIBLE);
                return true;
            }
        });
    }

    private void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();
        float scaleFactor = 8;
        float radius = 10;

        Bitmap overlay = Bitmap.createBitmap(
                (int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()
                / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);

        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        view.setBackground(new BitmapDrawable(getResources(), overlay));
        view.setVisibility(View.VISIBLE);
        System.out.println(System.currentTimeMillis() - startMs + "ms");
    }

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
