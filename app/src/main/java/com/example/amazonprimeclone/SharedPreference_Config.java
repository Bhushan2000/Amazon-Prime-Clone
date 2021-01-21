package com.example.amazonprimeclone;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference_Config {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreference_Config(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.loginPreference), Context.MODE_PRIVATE);


    }

    public void writeLoginStatus(Boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.loginStatusPreference),status);
        editor.commit();
    }


    public Boolean readLoginStatus() {
        Boolean status = false;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.loginStatusPreference), false);
        return status;


    }


}

