package com.app.tourist.data.sources.api.users;

import android.util.Log;

import com.app.tourist.core.constant.NetworkPath;
import com.app.tourist.core.constant.UserPath;
import com.app.tourist.core.error.BadRequestException;
import com.app.tourist.core.service.network.NetworkGetCall;
import com.app.tourist.core.service.network.NetworkPostCall;
import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.core.utils.Result;
import com.app.tourist.data.models.UserModel;
import com.app.tourist.data.request.UserLoginRequest;
import com.app.tourist.data.response.UserResponse;
import com.app.tourist.data.service.ApiService;
import com.app.tourist.data.service.UserService;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiUserSourceImpl implements ApiUserSource{

    private NetworkGetCall apiGet;
    private NetworkPostCall apiPost;
    private Gson gson ;
    public ApiUserSourceImpl(){
        apiGet  =new NetworkGetCall();
        apiPost = new NetworkPostCall();
        gson = new Gson();
    }

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
    public  Result<ApiResponse> login(String username, String password) throws Exception   {
        try {
            OkHttpClient client = ApiService.getHttpInstance();
            String url = NetworkPath.host + UserPath.login;

            JSONObject postAPI = new JSONObject();
            postAPI.put("email", username);
            postAPI.put("password", password);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(mediaType, postAPI.toString());


            try {
                Future<String> response = apiPost.call(url,requestBody);

                String body = response.get(); // This call blocks until the result is available
                ApiResponse<UserResponse> result = gson.fromJson(body, ApiResponse.class);

                return new Result.Success<>(result);

            } catch (ExecutionException e) {
                Throwable throwable = e.getCause();
                return new Result.Error(new Exception(e.getCause()));
            } catch (InterruptedException e) {
                return new Result.Error(e);
            }catch (BadRequestException e){
                return new Result.Error(e);
            }
        }catch (Exception ioexception){
            return new Result.Error(ioexception);
        }
    }

    public  Result<ApiResponse> signup(String nom, String prenom, String username, String password)   {
        try {
            OkHttpClient client = ApiService.getHttpInstance();
            String url = NetworkPath.host + UserPath.signup;

            JSONObject postAPI = new JSONObject();
            postAPI.put("nom", nom);
            postAPI.put("prenom", prenom);
            postAPI.put("email", username);
            postAPI.put("password", password);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(mediaType, postAPI.toString());

            try {
                Future<String> response = apiPost.call(url,requestBody);

                String body = response.get(); // This call blocks until the result is available
                ApiResponse<UserResponse> result = gson.fromJson(body, ApiResponse.class);

                return new Result.Success<>(result);

            }catch (ExecutionException e) {
                Throwable throwable = e.getCause();
                return new Result.Error(new Exception(e.getCause()));
            } catch (InterruptedException e) {
                return new Result.Error(e);
            } catch (BadRequestException e){
                return new Result.Error(e);
            }
        }catch (Exception ioexception){
            return new Result.Error(ioexception);
        }
    }


}
