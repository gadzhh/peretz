package com.example.peretz.data;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkFoodApi extends Application {
    private static final String BASE_URL = "https://peretz-group.ru/api/v2/";
    private static NetworkFoodApi networkFoodApi;
    private Retrofit retrofit;

    private NetworkFoodApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkFoodApi getNetworkFoodApi() {
        if (networkFoodApi == null) {
            networkFoodApi = new NetworkFoodApi();
        }
        return networkFoodApi;
    }

    public FoodApi getJSONApi() {
        return retrofit.create(FoodApi.class);
    }
}

