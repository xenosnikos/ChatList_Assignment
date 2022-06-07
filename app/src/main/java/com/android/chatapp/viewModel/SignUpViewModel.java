package com.android.chatapp.viewModel;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.android.chatapp.BR;
import com.android.chatapp.screens.Auth.Login.Model.LoginBasRes;
import com.android.chatapp.screens.Auth.Signup.SignupBody.SignUpBody;
import com.android.chatapp.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends BaseObservable {

    SignUpBody signUpModel;

    String successMessage = "Successfully registered. Please login";
    String errorMessage = "Please enter all details";
    LoginBasRes loginBasRes;
    public MutableLiveData<LoginBasRes> loginBasResMutableLiveData;
    ApiInterface apiInterface_;


    @Bindable
    String toastMessage = null;

    public String getToastMessage(){
        return toastMessage;
    }
    public void  setToastMessage(String toastMessage){
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getUserName() {
        return signUpModel.getName();
    }

    public void setUserName(String name) {
        signUpModel.setName(name);
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserEmail() {
        return signUpModel.getEmail();
    }

    public void setUserEmail(String email) {
        signUpModel.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public String getUserPassword() {
        return signUpModel.getPassword();
    }

    public void setUserPassword(String password) {
        signUpModel.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    public SignUpViewModel(ApiInterface apiInterface){
        signUpModel = new SignUpBody("","","");
        this.apiInterface_ = apiInterface;
        loginBasResMutableLiveData=new MutableLiveData<LoginBasRes>();
    }

    boolean isValid(){
        return !TextUtils.isEmpty(getUserEmail()) && Patterns.EMAIL_ADDRESS.matcher(getUserEmail()).matches() && !getUserPassword().isEmpty()
                && getUserPassword().length()>5 && !getUserName().isEmpty();
    }

    public void onClickButton(){
        if (isValid()){
            call_signup(apiInterface_,signUpModel);
        }
        else {
            setToastMessage(errorMessage);
        }
    }

    public void call_signup(ApiInterface apiInterface, SignUpBody loginModel){

        Call<LoginBasRes> baseres=apiInterface.SignupAPI(loginModel);

        baseres.enqueue(new Callback<LoginBasRes>() {
            @Override
            public void onResponse(Call<LoginBasRes> call, Response<LoginBasRes> response) {
                Log.e( "onResponse: ",response.code()+"" );
                Log.e( "onResponse: ",response+" " );
                if (response.code()==200){
                    loginBasRes =response.body();
                    loginBasResMutableLiveData.postValue(loginBasRes);
                    setToastMessage(successMessage);
                }else {
                  setToastMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginBasRes> call, Throwable t) {
                Log.e("errorTAG", t.toString());

            }
        });

    }


}
