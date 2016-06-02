package com.example.lw_pc.tribblekill.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lw_pc.tribblekill.App;
import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.core.UserInfo;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.model.Token;
import com.example.lw_pc.tribblekill.model.User;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private App app;

    public String code;

    private WebView mWebView;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mWebView = (WebView) findViewById(R.id.loginView);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mWebView.loadUrl(DribbbleApi.OAuth_CODE);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                if(url.contains("code")) {
                    code = url.substring(url.indexOf("=")+1, url.length());
                    final Api api = DribbbleApi.getDribbbleApi();
                    api.getToken(DribbbleApi.CLIENT_ID, DribbbleApi.CLIENT_SECRET, code).enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Response<Token> response, Retrofit retrofit) {
                            app = (App) getApplication();
                            SharedPreferences.Editor editor = app.sharedPreferences.edit();
                            editor.putString("access_token", response.body().getAccess_token());
                            editor.putString("isLogin", "true");
                            editor.apply();

                            api.getUser(response.body().getAccess_token()).enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Response<User> response, Retrofit retrofit) {
                                    SharedPreferences.Editor editor = app.sharedPreferences.edit();
                                    editor.putString("avatar_url", response.body().getAvatar_url());
                                    editor.putString("name", response.body().getName());
                                    editor.apply();
                                    Shot info = UserInfo.getInstance().getInfo();
                                    info.setUser(response.body());
                                    info.setValue(response.body().getAvatar_url(), response.body().getName());
                                    info.notifyObservers();
                                }

                                @Override
                                public void onFailure(Throwable t) {

                                }
                            });

                            LoginActivity.this.finish();


                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });

                }
                return true;
            }

        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
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
