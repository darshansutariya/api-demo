package com.app.famezone.apiCalling;


import com.app.famezone.Response.AppCampingList;
import com.app.famezone.Response.AppList;
import com.app.famezone.Response.NotificationList;
import com.app.famezone.Response.Profile;
import com.app.famezone.Response.YoutubeCampingList;
import com.app.famezone.Response.Category;
import com.app.famezone.Response.ContantSetting;
import com.app.famezone.Response.EarningHistory;
import com.app.famezone.Response.Invite;
import com.app.famezone.Response.ListWebSetting;
import com.app.famezone.Response.ListYoutubeSetting;
import com.app.famezone.Response.SignUpResponse;
import com.app.famezone.Response.TransactionList;
import com.app.famezone.Response.WebCampingList;
import com.app.famezone.Response.WebsiteList;
import com.app.famezone.Response.YoutubeList;
import com.google.gson.JsonElement;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("User/usersignin")
    Call<SignUpResponse> signUp(@Field("fname") String fName,
                                @Field("lname") String lName,
                                @Field("email") String email,
                                @Field("profile") String profile,
                                @Field("devicetoken") String deviceToken,
                                @Field("refferalcode") String refer,
                                @Field("fbid") String fbId,
                                @Field("gbid") String gId,
                                @Field("playerId") String playerId);

    @FormUrlEncoded
    @POST("User/logout")
    Call<Response<JsonElement>> logout(@Field("userid") String userId,
                                       @Field("authtoken") String authToken,
                                       @Field("devicetoken") String deviceToken);

   
}