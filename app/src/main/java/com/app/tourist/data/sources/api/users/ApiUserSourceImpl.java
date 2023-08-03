package com.app.tourist.data.sources.api.users;

import android.util.Log;

import com.app.tourist.core.constant.NetworkPath;
import com.app.tourist.core.constant.UserPath;
import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.core.utils.Result;
import com.app.tourist.data.models.UserModel;
import com.app.tourist.data.request.UserLoginRequest;
import com.app.tourist.data.service.ApiService;
import com.app.tourist.data.service.UserService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiUserSourceImpl implements ApiUserSource{

    @Override
    public Result<ApiResponse> getAllUser(){
        try{

            String url = NetworkPath.host+ UserPath.getAllUser;
            OkHttpClient client = ApiService.getHttpInstance();

            Request request = new Request.Builder().url(url).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    // Handle failure here
                    Log.e("OkHttp Error", e.toString());
                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    // Handle the response here
                    String responseBody = response.body().toString();
                    Log.d("c Response", responseBody);
                }
            });

            return null;
        }catch (Exception exception){
            return new Result.Error(exception);
        }
    }

    @Override
    public Result<ApiResponse> login(String username, String password) {
        try {
            OkHttpClient client = ApiService.getHttpInstance();

            Request request = new Request.Builder().url(NetworkPath.host+ UserPath.login).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    // Handle failure here
                    Log.e("OkHttp Error", e.toString());
                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    // Handle the response here
                    String responseBody = response.body().toString();
                    Log.d("c Response", responseBody);
                }
            });

            return null;
        } catch (Exception exception) {
           return new Result.Error(exception);
        }
    }
}
