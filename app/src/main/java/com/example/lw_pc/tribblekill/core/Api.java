package com.example.lw_pc.tribblekill.core;

import android.support.annotation.Nullable;

import com.example.lw_pc.tribblekill.model.Attachment;
import com.example.lw_pc.tribblekill.model.Comment;
import com.example.lw_pc.tribblekill.model.Follow;
import com.example.lw_pc.tribblekill.model.Like;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.model.Token;

import java.util.List;

import retrofit.Call;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
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

    @DELETE(DribbbleApi.LIKE_SHOT_URL)
    Call<Like> unlikeShot(@Path("id") int shotId, @Query("access_token") String token);

    @GET(DribbbleApi.LIKE_SHOT_URL)
    Call<Like> isLikeShot(@Path("id") int shotId, @Query("access_token") String token);

    @GET(DribbbleApi.Personal_Shots)
    Call<List<Shot>> getPersonalShots(@Path("id") int userId, @Query("page") int num);

    @GET(DribbbleApi.Team_Shots)
    Call<List<Shot>> getTeamShots(@Path("id") int teamId, @Query("page") int num);

    @GET(DribbbleApi.Followers)
    Call<List<Follow>> getFollowers(@Path("id") int userId);

    @GET(DribbbleApi.Check_Follow_User)
    Call<Nullable> isFollowUser(@Path("id") int userId, @Query("access_token") String Token);

    @PUT(DribbbleApi.Follow_User)
    Call<Nullable> followUser(@Path("id") int userId, @Query("access_token") String Token);

    @DELETE(DribbbleApi.Follow_User)
    Call<Nullable> unfollowUser(@Path("id") int userId, @Query("access_token") String Token);

    @GET(DribbbleApi.Following_Shot)
    Call<List<Shot>> getFollowingShots(@Query("page") int num, @Query("access_token") String Token);

    @GET(DribbbleApi.Attachments)
    Call<List<Attachment>> getAttachments(@Path("id") int shotId);
}
