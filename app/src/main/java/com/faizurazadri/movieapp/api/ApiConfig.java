package com.faizurazadri.movieapp.api;

import com.faizurazadri.movieapp.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    public static ApiService getApiService(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(ApiService.class);
    }
}
