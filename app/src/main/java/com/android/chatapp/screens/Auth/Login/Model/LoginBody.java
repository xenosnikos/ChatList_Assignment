package com.android.chatapp.screens.Auth.Login.Model;

import androidx.annotation.Nullable;

public class LoginBody {

    @Nullable
    String email,password;

    public LoginBody(@Nullable String email, @Nullable String password) {
        this.email = email;
        this.password = password;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }
}
