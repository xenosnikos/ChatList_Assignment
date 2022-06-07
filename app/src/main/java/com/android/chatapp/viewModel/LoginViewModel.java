package com.android.chatapp.viewModel;

import android.text.TextUtils;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.android.chatapp.BR;
import com.android.chatapp.screens.Auth.Login.Model.LoginBasRes;
import com.android.chatapp.screens.Auth.Login.Model.LoginBody;
import com.android.chatapp.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends BaseObservable {
    LoginBody loginModel;

    private String successMessage = "Successfully Login";
    private String errorMessage = "Please enter all details";
    ApiInterface apiInterface_;
    LoginBasRes loginBasRes;
   public MutableLiveData<LoginBasRes> loginBasResMutableLiveData;

    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getUserEmail(){
        return loginModel.getEmail();
    }

    public void setUserEmail(String email){
        loginModel.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public String getPassword(){
        return loginModel.getPassword();
    }

    public void setPassword(String password){
        loginModel.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    public LoginViewModel(ApiInterface apiInterface){
        loginModel = new LoginBody("","");
        this.apiInterface_ = apiInterface;
        loginBasResMutableLiveData=new MutableLiveData<LoginBasRes>();
    }



    public boolean isValid(){
        return !TextUtils.isEmpty(getUserEmail()) && getPassword().length()>5;
    }

    public void onButtonClicked(){
        if (isValid()){
                onCreateButtonClicked();

        }else {
            setToastMessage(errorMessage);
        }
    }

    public boolean checkUser(){
        return getUserEmail().equals("parveenkumar12984@gmail.com") && getPassword().equals("123456");
    }

    public void onCreateButtonClicked(){
        Log.e( "onResponse: ","111" );

        call_login(apiInterface_,loginModel);
    }

    public void call_login(ApiInterface apiInterface, LoginBody loginModel){

        Call<LoginBasRes> baseres=apiInterface.LoginAPI(loginModel);

        baseres.enqueue(new Callback<LoginBasRes>() {
            @Override
            public void onResponse(Call<LoginBasRes> call, Response<LoginBasRes> response) {
                if (response.code()==200){
                    loginBasRes =response.body();
                    loginBasResMutableLiveData.postValue(loginBasRes);
                }else {
                    setToastMessage("Please enter valid email & password");
                }
            }

            @Override
            public void onFailure(Call<LoginBasRes> call, Throwable t) {
                Log.e("errorTAG", t.toString());

            }
        });

    }
}
