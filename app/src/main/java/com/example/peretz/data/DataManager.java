package com.example.peretz.data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {

    public void getMenuItems(RequestListener listener) {

        ArrayList<Menu> menu = new ArrayList<>();

        NetworkFoodApi.getNetworkFoodApi().getJSONApi().getData(
                "93",
                "47be9031474183ea92958d5e255d888e47bdad44afd5d7b7201d0eb572be5278"
        ).
                enqueue(new Callback<List<Menu>>() {
                    @Override
                    public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                        if (response.body() != null) {
                            listener.onSuccess((ArrayList<Menu>) response.body());
                        } else {
                            listener.onError("Data is Empty");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Menu>> call, Throwable t) {
                        listener.onError("" + t.getLocalizedMessage());
                    }
                });
    }
}
