package com.app.tourist.data.sources.local;

import android.content.SharedPreferences;

public class TokenDataSource {
    private final SharedPreferences sharedPreferences;
    private final String TOKEN_KEY = "token";
    private final String ISLOGGED_KEY ="is_logged_in";

    public TokenDataSource(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
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


    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
    }
}
