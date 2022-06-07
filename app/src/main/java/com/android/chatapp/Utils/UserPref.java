package com.android.chatapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPref {

   SharedPreferences userPreferences;
   SharedPreferences.Editor edit;

   public static UserPref preferences= null;



    public static UserPref getInstance(Context context){
        if (preferences == null) {
            preferences =new UserPref(context.getApplicationContext());
        }
        return preferences ;
   }

   public UserPref(Context context){
        userPreferences =
                context.getSharedPreferences("UserSharedPreferences", Context.MODE_PRIVATE);
        edit = userPreferences.edit();
    }

    public void Clear() {
        edit.clear();
        edit.commit();
    }


    public void  setlogin(Boolean login) {
        edit.putBoolean("login", login);
        edit.commit();
    }

    public Boolean getlogin(){
        return userPreferences.getBoolean("login", false);
    }


    public String gettoken() {
        return userPreferences.getString("token", "");
    }


    public void settoken(String token) {
        edit.putString("token", token);
        edit.commit();
    }
}
