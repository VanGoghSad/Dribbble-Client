package com.example.lw_pc.tribblekill.core;

import android.util.Pair;
import java.util.List;
import java.util.StringTokenizer;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by LW-PC on 2016/4/11.
 */
public class DribbbleApi {
    public static final String ACCESS_TOKEN = "5e94ee35dba3d726950d8141c73d526f961abf835b1296d3818cfc847f57a2f1";
    public static final String _ACCESS_TOKEN = "?access_token=5e94ee35dba3d726950d8141c73d526f961abf835b1296d3818cfc847f57a2f1";
    public static final String CLIENT_ID = "5988fce26ff72caeff19fc4a3a4cfd2dcb9194b2650b5d240997c16a3534bd4b";
    public static final String CLIENT_SECRET = "ed3da9a11a357ec53d068bb320d577fdb8a36966371375a1106a9454c3ac098b";
    private static final String BASE_URL = "https://api.dribbble.com/v1/";
    public static final String Shots = "shots?access_token=" + ACCESS_TOKEN;
    public static final String OAuth_CODE ="https://dribbble.com/oauth/authorize?client_id=" + CLIENT_ID + "&scope=public+write+comment+upload";
    public static final String OAuth_ACCESS_TOKEN = "https://dribbble.com/oauth/token";
    public static final String COMMENTS_URL = BASE_URL + "shots/{id}/comments" + _ACCESS_TOKEN;
    public static final String LIKE_COMMENT_URL = BASE_URL + "shots/{shot}/comments/{id}/like";
    public static final String LIKE_SHOT_URL = BASE_URL + "shots/{id}/like";
    public static final String Personal_Shots = BASE_URL + "users/{id}/shots" + _ACCESS_TOKEN;
    public static final String Team_Shots = BASE_URL + "teams/{id}/shots" + _ACCESS_TOKEN;
    public static final String Followers = BASE_URL + "users/{id}/followers" + _ACCESS_TOKEN;
    public static final String Check_Follow_User = BASE_URL + "user/following/{id}";
    public static final String Follow_User = BASE_URL + "users/{id}/follow";
    public static final String Following_Shot = BASE_URL + "user/following/shots";
    public static final String Attachments = BASE_URL + "shots/{id}/attachments" + _ACCESS_TOKEN;
    public static final String User = BASE_URL + "user";

    private Retrofit retrofit;
    private static DribbbleApi instance = new DribbbleApi();



    private DribbbleApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Api getDribbbleApi() {
        return instance.retrofit.create(Api.class);
    }

    /*public String getUrl(String suffix, List<Pair<String, String>> queryString){
        String url = BASE_URL + suffix;

        for (Pair<String, String> item :
                queryString) {
            url += ("&" + item.first + "=" + item.second);
        }
        return url;
    }*/


}
