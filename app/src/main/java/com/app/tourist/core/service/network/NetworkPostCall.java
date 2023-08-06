package com.app.tourist.core.service.network;

import com.app.tourist.core.error.BadRequestException;
import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.core.utils.Result;
import com.app.tourist.data.service.ApiService;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkPostCall {
    private OkHttpClient httpClient;
    private ExecutorService executorService;

    public NetworkPostCall() {
        httpClient = ApiService.getHttpInstance();
        executorService = Executors.newSingleThreadExecutor();
    }

    public Future<String> call(String url, RequestBody body) throws BadRequestException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> apiCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                OkHttpClient client = ApiService.getHttpInstance();
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if(response.isSuccessful()){
                        return response.body().string();
                    } else {
                        if(response.networkResponse().code() == 400){
                            throw new BadRequestException("Vérifier votre données");
                        } else {
                            throw  new IOException("Api called failed"+String.valueOf(response.networkResponse().code()));
                        }
                    }
                } catch (IOException e) {
                    throw e;
                }
            }
        };

        return executor.submit(apiCallable);
    }

    public interface HttpCallback {
        Result<ApiResponse> onSuccess(ResponseBody response);
        Result<ApiResponse> onError(Exception e);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
