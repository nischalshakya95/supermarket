package com.dinube.supermarket.dinube.retrofit;

import com.dinube.supermarket.dinube.service.DinubeAPIService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DinubeRetrofit {

    private final static String BASE_URL = "https://5ed9b91b72c9.ngrok.io/";

    public static DinubeAPIService getDinubeAPIInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOKHttpClient())
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(DinubeAPIService.class);
    }

    private static OkHttpClient getOKHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }
}
