package com.android.chatapp.screens.Auth.Signup.SignupBody;

import androidx.annotation.Nullable;

public class SignUpBody {

    @Nullable
    String name,email,password;

    public SignUpBody(@Nullable String name, @Nullable String email, @Nullable String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
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
