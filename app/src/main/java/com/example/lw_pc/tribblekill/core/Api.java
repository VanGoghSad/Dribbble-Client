package com.example.lw_pc.tribblekill.core;

import com.example.lw_pc.tribblekill.model.Shot;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Created by LW-PC on 2016/4/11.
 */
public interface Api {
    @GET(DribbbleApi.Shots)
    Call<List<Shot>> getShots();
}
