package com.android.chatapp.screens.Auth.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.chatapp.Utils.UserPref;
import com.android.chatapp.screens.Auth.Login.Model.LoginBasRes;
import com.android.chatapp.network.ApiInterface;
import com.android.chatapp.network.RetrofitClient;
import com.android.chatapp.R;
import com.android.chatapp.databinding.ActivityLoginBinding;
import com.android.chatapp.screens.Auth.Signup.SignUpActivity;
import com.android.chatapp.screens.Chat.MainActivity;
import com.android.chatapp.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;
    ActivityLoginBinding activityLoginBinding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (UserPref.getInstance(LoginActivity.this).getlogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        setupview();
        setupobserver();
    }


    public void setupview(){
        apiInterface = RetrofitClient.getInstance().getapi();

        loginViewModel = new LoginViewModel(apiInterface);
         activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLoginViewModel(loginViewModel);
        activityLoginBinding.executePendingBindings();
        activityLoginBinding.rlSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    private void setupobserver() {
        loginViewModel.loginBasResMutableLiveData.observe(this, new Observer<LoginBasRes>() {
            @Override
            public void onChanged(LoginBasRes loginBasRes) {
                Log.e( "onChanged: ",loginBasRes.token );
                UserPref.getInstance(LoginActivity.this).setlogin(true);
                UserPref.getInstance(LoginActivity.this).settoken(loginBasRes.token);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

            }
        });
    }


    // any change in toastMessage attribute defined on the Button with bind prefix invokes this method
    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message){
        if (message != null){
            Log.e("message",message);
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}