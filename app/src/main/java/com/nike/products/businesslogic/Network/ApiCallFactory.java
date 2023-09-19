package com.nike.products.businesslogic.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiCallFactory {
    private static final int NETWORK_CALL_TIMEOUT = 60; //600, 300

    public static ApiHelper create(String url) {
        return getRetrofit(url).create(ApiHelper.class);
    }

    private static Retrofit getRetrofit(String url) {
        OkHttpClient.Builder builder;
        builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        builder.readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);


        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
    }
}
