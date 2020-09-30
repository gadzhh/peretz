package com.example.peretz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {
    @GET("products")
    Call<List<Menu>> getData(
            @Query("category") String resourceCategory,
            @Query("key") String key
    );
}
