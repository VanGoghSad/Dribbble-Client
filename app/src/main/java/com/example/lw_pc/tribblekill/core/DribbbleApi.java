package com.example.lw_pc.tribblekill.core;

import android.util.Pair;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by LW-PC on 2016/4/11.
 */
public class DribbbleApi {
    public static final String ACCESS_TOKEN = "5e94ee35dba3d726950d8141c73d526f961abf835b1296d3818cfc847f57a2f1";
    private static final String BASE_URL = "https://api.dribbble.com/v1/";
    public static final String Shots = "shots?access_token=" + ACCESS_TOKEN;

    private Retrofit retrofit;
    private static DribbbleApi instance = new DribbbleApi();

    /*public static String getUrl(String suffix, List<Pair<String, String>> queryString){
        String url = BASE_URL + suffix;

        for (Pair<String, String> item :
                queryString) {
            url += ("&" + item.first + "=" + item.second);
        }
        return url;
    }*/

    private DribbbleApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Api getDribbbleApi() {
        return instance.retrofit.create(Api.class);
    }



}
