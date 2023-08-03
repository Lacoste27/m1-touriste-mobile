package com.app.tourist.data.request;

import com.app.tourist.domain.entities.User;

public class UserLoginRequest {
    private String username;
    private String password;

    public UserLoginRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
