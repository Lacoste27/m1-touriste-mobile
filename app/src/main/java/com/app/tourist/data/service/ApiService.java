package com.app.tourist.data.service;

import com.app.tourist.core.constant.NetworkPath;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static OkHttpClient client;

    public static OkHttpClient getHttpInstance() {
        if (client == null) {
            OkHttpClient _client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();
            client = _client;
        }
        return client;
    }
}
