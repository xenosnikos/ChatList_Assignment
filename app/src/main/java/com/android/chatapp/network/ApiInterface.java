package com.android.chatapp.network;

import com.android.chatapp.screens.Auth.Login.Model.LoginBasRes;
import com.android.chatapp.screens.Auth.Login.Model.LoginBody;
import com.android.chatapp.screens.Auth.Signup.SignupBody.SignUpBody;
import com.android.chatapp.screens.Chat.Model.ChannelsList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("v2/auth/login")//endpoint
    Call<LoginBasRes> LoginAPI(@Body LoginBody loginModel);

  @POST("v2/auth/signup")//endpoint
    Call<LoginBasRes> SignupAPI(@Body SignUpBody loginModel);

 @GET("v2/chat-channels")//endpoint
    Call<ChannelsList> GetChatAPI(@Header("Authorization") String header);

}
