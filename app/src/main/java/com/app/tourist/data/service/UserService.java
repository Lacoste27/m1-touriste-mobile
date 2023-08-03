package com.app.tourist.data.service;

import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.data.request.UserLoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("users/")
    Call<ApiResponse> getAllUser();

    @POST("users/login")
    Call<ApiResponse> login(@Body UserLoginRequest login);
}
