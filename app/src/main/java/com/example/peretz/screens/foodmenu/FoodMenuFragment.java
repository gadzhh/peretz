package com.example.peretz.screens.foodmenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peretz.R;
import com.example.peretz.data.Menu;
import com.example.peretz.data.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.Objects;


public class FoodMenuFragment extends Fragment implements FoodMenuView, OnButtonListener {

    private ProgressBar progressBar;
    private MenuItemsAdapter menuItemsAdapter;
    private FoodMenuPresenter foodMenuPresenter;
    private SharedPreferenceHelper prefsHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food_menu, container, false);
    }


    @SuppressLint("CommitPrefEdits")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initPrefs();
        initPresenter();
    }

    private void initPresenter() {
        foodMenuPresenter = new FoodMenuPresenter(this);
    }

    private void initPrefs() {

        if (getActivity().getBaseContext() != null) {
            prefsHelper = new SharedPreferenceHelper(getActivity().getBaseContext());
        }
    }

    private void initViews(View view) {

        Toolbar toolbar;
        RecyclerView foodList;

        toolbar = view.findViewById(R.id.menu_toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                fragmentManager.popBackStackImmediate();
            }
        });

        foodList = view.findViewById(R.id.rv_menu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        foodList.setLayoutManager(layoutManager);
        foodList.setHasFixedSize(true);

        menuItemsAdapter = new MenuItemsAdapter(this);
        foodList.setAdapter(menuItemsAdapter);


        progressBar = view.findViewById(R.id.progress);
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(ArrayList<Menu> menu) {

        for (int i = 0; i < menu.size(); i++) {
            int index = prefsHelper.quantityInBasket(String.valueOf(menu.get(i).getId()));
            menu.get(i).setQuantity(index);
        }
        menuItemsAdapter.setData(menu);
    }


    @Override
    public void onButtonClick(int productId, boolean isDelete) {
        if (isDelete) {
            prefsHelper.removeFromCart(String.valueOf(productId));
        } else {
            prefsHelper.addToCart(String.valueOf(productId));
        }
    }
}