package com.example.lw_pc.tribblekill.core;

import android.webkit.WebView;

import com.example.lw_pc.tribblekill.model.Comment;
import com.example.lw_pc.tribblekill.model.Like;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.model.Token;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
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
    Call<List<Shot>> getShots(@Query("page") int num);

    @GET(DribbbleApi.COMMENTS_URL)
    Call<List<Comment>> getComments(@Path("id") int Id);

    @FormUrlEncoded
    @POST(DribbbleApi.OAuth_ACCESS_TOKEN)
    Call<Token> getToken(@Field("client_id") String id, @Field("client_secret")  String secret, @Field("code")  String code);


    @POST(DribbbleApi.LIKE_COMMENT_URL)
    Call<Like> likeComment(@Path("shot") int shotId, @Path("id") int Id, @Query("access_token") String token);

    @GET(DribbbleApi.LIKE_COMMENT_URL)
    Call<Like> isLikeComment(@Path("shot") int shotId, @Path("id") int Id, @Query("access_token") String token);

    @DELETE(DribbbleApi.LIKE_COMMENT_URL)
    Call<Like> unlikeComment(@Path("shot") int shotId, @Path("id") int Id, @Query("access_token") String token);

    @POST(DribbbleApi.LIKE_SHOT_URL)
    Call<Like> likeShot(@Path("id") int shotId, @Query("access_token") String token);
}
