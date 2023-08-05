package com.app.tourist.core.service.network;

import android.os.AsyncTask;

import com.app.tourist.data.service.ApiService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkGetCall {
    private OkHttpClient httpClient;
    private ExecutorService executorService;

    public NetworkGetCall() {
        httpClient = ApiService.getHttpInstance();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void call(String url, HttpCallback callback) {
        Future<ResponseBody> future = executorService.submit(() -> {
            try {
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = httpClient.newCall(request).execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });

        executorService.execute(() -> {
            try {
                ResponseBody result = future.get();
                if (result != null) {
                    callback.onSuccess(result);
                } else {
                    callback.onError(new Exception("HTTP request failed"));
                }
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }

    public interface HttpCallback {
        void onSuccess(ResponseBody response);
        void onError(Exception e);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
