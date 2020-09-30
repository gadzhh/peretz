package com.example.peretz.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peretz.Menu;
import com.example.peretz.MenuItemsAdapter;
import com.example.peretz.NetworkFoodApi;
import com.example.peretz.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodMenuFragment extends Fragment {

    private Button backButton;
    private RecyclerView foodList;
    private MenuItemsAdapter menuItemsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initRequest();
    }

    private void initViews(View view) {
        backButton = view.findViewById(R.id.btn_go_back);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_for_fragments, homeFragment).addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        foodList = view.findViewById(R.id.rv_menu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        foodList.setLayoutManager(layoutManager);
        foodList.setHasFixedSize(true);

        menuItemsAdapter = new MenuItemsAdapter();
        foodList.setAdapter(menuItemsAdapter);
    }

    private void initRequest() {

        NetworkFoodApi.getNetworkFoodApi().getJSONApi().getData(
                "93",
                "47be9031474183ea92958d5e255d888e47bdad44afd5d7b7201d0eb572be5278"
        ).
                enqueue(new Callback<List<Menu>>() {
                    @Override
                    public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                        menuItemsAdapter.setData((ArrayList<Menu>) response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Menu>> call, Throwable t) {

                    }
                });
    }
}
