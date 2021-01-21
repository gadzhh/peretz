package com.example.peretz;

import com.example.peretz.data.FoodApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App {
    private static final String BASE_URL = "https://peretz-group.ru/api/v2/";
    private static App networkFoodApi;
    private Retrofit retrofit;

    private App() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static App getNetworkFoodApi() {
        if (networkFoodApi == null) {
            networkFoodApi = new App();
        }
        return networkFoodApi;
    }

    public FoodApi getJSONApi() {
        return retrofit.create(FoodApi.class);
    }
}

