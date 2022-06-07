package com.android.chatapp.screens.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.android.chatapp.Utils.UserPref;
import com.android.chatapp.network.ApiInterface;
import com.android.chatapp.network.RetrofitClient;
import com.android.chatapp.R;
import com.android.chatapp.databinding.ActivityMainBinding;
import com.android.chatapp.screens.Auth.Signup.SignUpActivity;
import com.android.chatapp.screens.Chat.Model.ChannelsList;
import com.android.chatapp.viewModel.ChatViewModel;

public class MainActivity extends AppCompatActivity {

    ChatViewModel chatViewModel;
    ActivityMainBinding activityMainBinding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupview();
        setupobserver();
    }

    private void setupview() {
        apiInterface = RetrofitClient.getInstance().getapi();

        chatViewModel = new ChatViewModel();
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setChatViewModel(chatViewModel);
        activityMainBinding.executePendingBindings();

        activityMainBinding.recycler.setLayoutManager(new LinearLayoutManager(this));

        activityMainBinding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

   private void setupobserver() {
       chatViewModel.GetChatList(apiInterface,"Bearer "+ UserPref.getInstance(MainActivity.this).gettoken());
       chatViewModel.channelsListMutableLiveData.observe(this, new Observer<ChannelsList>() {
           @Override
           public void onChanged(ChannelsList channelsList) {
               activityMainBinding.recycler.setAdapter(new ChatAdapter(channelsList.channels,MainActivity.this));
           }
       });

    }
}