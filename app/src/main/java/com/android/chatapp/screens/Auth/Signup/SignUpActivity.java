package com.android.chatapp.screens.Auth.Signup;

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
import com.android.chatapp.network.ApiInterface;
import com.android.chatapp.network.RetrofitClient;
import com.android.chatapp.R;
import com.android.chatapp.databinding.ActivitySignUpBinding;
import com.android.chatapp.screens.Auth.Login.Model.LoginBasRes;
import com.android.chatapp.screens.Chat.MainActivity;
import com.android.chatapp.viewModel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {

    SignUpViewModel signUpViewModel;
    ApiInterface apiInterface;
    ActivitySignUpBinding activitySignUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupview();
        setupobserver();



    }

    private void setupobserver() {
        signUpViewModel.loginBasResMutableLiveData.observe(this, new Observer<LoginBasRes>() {
            @Override
            public void onChanged(LoginBasRes loginBasRes) {
                Log.e( "onChanged: ",loginBasRes.token );
                UserPref.getInstance(SignUpActivity.this).setlogin(true);
                UserPref.getInstance(SignUpActivity.this).settoken(loginBasRes.token);
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void setupview() {
        apiInterface = RetrofitClient.getInstance().getapi();

        // ViewModel updates the Model after observing changes in the View
        // model will also update the view via the ViewModel
        signUpViewModel= new SignUpViewModel(apiInterface);
        activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        activitySignUpBinding.setSignUpViewModel(signUpViewModel);
        activitySignUpBinding.executePendingBindings();

        activitySignUpBinding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // any change in toastMessage attribute defined on the Button with bind prefix invokes this method
    @BindingAdapter({"toastMessagee"})
    public static void runMe(View view, String message){
        if (message != null){
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}