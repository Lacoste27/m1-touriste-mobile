package com.app.tourist.data.sources.api.users;

import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.core.utils.Result;
import com.app.tourist.data.models.UserModel;
import com.app.tourist.data.request.UserLoginRequest;
import com.app.tourist.data.service.ApiService;
import com.app.tourist.data.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUserSourceImpl implements ApiUserSource{

    @Override
    public Result<ApiResponse> getAllUser(){
        try{
            UserService userService = ApiService.getRetrofitInstance().create(UserService.class);
            Call<ApiResponse> call = userService.getAllUser();
            Response<ApiResponse> result = call.execute();
            Result<ApiResponse> response;

            if(result.isSuccessful()){
                response = null;
            } else {
                response = null;
            }
            return response;
        }catch (Exception exception){
            return new Result.Error(exception);
        }
    }

    @Override
    public Result<ApiResponse> login(String username, String password) {
        try {
            UserService userService = ApiService.getRetrofitInstance().create(UserService.class);
            UserLoginRequest request = new UserLoginRequest(username, password);
            Call<ApiResponse> call = userService.login(request);
            Response<ApiResponse> result = call.execute();
            Result<ApiResponse> response;

            if(result.isSuccessful()){
                response = null;
            } else {
                response = null;
            }
            return response;
        } catch (Exception exception) {
           return new Result.Error(exception);
        }
    }
}
