package com.example.lw_pc.tribblekill.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.core.Api;
import com.example.lw_pc.tribblekill.core.DribbbleApi;
import com.example.lw_pc.tribblekill.model.Token;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    public String code;

    private WebView mWebView;
    private Toolbar mToolBar;

    public static Fragment newInstance() {
        Fragment fragment = new LoginFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        mWebView = (WebView) v.findViewById(R.id.loginView);
        mToolBar = (Toolbar) v.findViewById(R.id.toolbar);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolBar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            // 给左上角图标的左边加上一个返回的图标
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mWebView.loadUrl(DribbbleApi.OAuth_CODE);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                System.out.println(url);
                if(url.contains("code")) {
                    code = url.substring(url.indexOf("=")+1, url.length());
                    Api api = DribbbleApi.getDribbbleApi();
                    api.getToken(DribbbleApi.CLIENT_ID, DribbbleApi.CLIENT_SECRET, code).enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Response<Token> response, Retrofit retrofit) {
                            //access_token
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
}
