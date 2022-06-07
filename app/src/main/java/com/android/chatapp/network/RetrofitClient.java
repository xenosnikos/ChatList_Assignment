package com.android.chatapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static final String base_url = "https://api.quwi.com/";//base url
    private static RetrofitClient instance;
    private Retrofit retrofit; //retrofit object

    private RetrofitClient() { //constructor
        retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;

    }

    public ApiInterface getapi() { //defining api function
        return retrofit.create(ApiInterface.class);
    }
}
