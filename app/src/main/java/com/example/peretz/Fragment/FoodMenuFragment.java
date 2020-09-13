package com.example.peretz.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peretz.MenuItemsAdapter;
import com.example.peretz.R;


public class FoodMenuFragment extends Fragment {

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
    }

    private void initViews(View view) {

        foodList = view.findViewById(R.id.rv_menu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        foodList.setLayoutManager(layoutManager);
        foodList.setHasFixedSize(true);

        menuItemsAdapter = new MenuItemsAdapter(4);
        foodList.setAdapter(menuItemsAdapter);
    }
}
