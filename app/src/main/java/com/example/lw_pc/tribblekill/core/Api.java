package com.example.lw_pc.tribblekill.core;

import android.webkit.WebView;

import com.example.lw_pc.tribblekill.model.Comment;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.model.Token;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by LW-PC on 2016/4/11.
 */
public interface Api {
    @GET(DribbbleApi.Shots)
    Call<List<Shot>> getShots();

    @GET(DribbbleApi.COMMENTS_URL)
    Call<List<Comment>> getComments(@Path("id") int Id);

    @FormUrlEncoded
    @POST(DribbbleApi.OAuth_ACCESS_TOKEN)
    Call<Token> getToken(@Field("client_id") String id, @Field("client_secret")  String secret, @Field("code")  String code);
    
}
