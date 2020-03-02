package com.example.newsviewsv2.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("user_pref",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setLoginStatus(boolean status){
        editor.putBoolean("status",status);
        editor.commit();
    }

    public boolean getLoginStatus(){
        return sharedPreferences.getBoolean("status",false);
    }
}
