package com.android.chatapp.viewModel;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.android.chatapp.network.ApiInterface;
import com.android.chatapp.screens.Chat.Model.ChannelsList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatViewModel extends BaseObservable {

    public MutableLiveData<ChannelsList> channelsListMutableLiveData;
    ChannelsList channelsList;

    public ChatViewModel() {
        channelsListMutableLiveData = new MutableLiveData<>();

    }

    public void GetChatList(ApiInterface apiInterface, String token){

        Call<ChannelsList> baseres=apiInterface.GetChatAPI(token);

        baseres.enqueue(new Callback<ChannelsList>() {
            @Override
            public void onResponse(Call<ChannelsList> call, Response<ChannelsList> response) {
                Log.e( "onResponse:",response.code()+"" );
                Log.e( "onResponse: ",response+" " );
                if (response.code()==200){
                    channelsList =response.body();
                    channelsListMutableLiveData.postValue(channelsList);
                }
            }

            @Override
            public void onFailure(Call<ChannelsList> call, Throwable t) {
                Log.e("errorTAG", t.toString());

            }
        });

    }



}
