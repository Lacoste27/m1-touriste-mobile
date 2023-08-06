package com.app.tourist.data.sources.local;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class TokenDataSource {
    private final SharedPreferences sharedPreferences;
    private final String TOKEN_KEY = "token";
    private final String ISLOGGED_KEY ="is_logged_in";

    public TokenDataSource(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public TokenDataSource() {
        this.sharedPreferences=null;
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public void setIsLogged(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(ISLOGGED_KEY, true);
        editor.apply();
    }

    public void setIsLoggout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(ISLOGGED_KEY, false);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, "");
    }

    public boolean getIsLogged() {
        return sharedPreferences.getBoolean(ISLOGGED_KEY, false);
    }


    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.remove(ISLOGGED_KEY);
        editor.apply();
    }
}
