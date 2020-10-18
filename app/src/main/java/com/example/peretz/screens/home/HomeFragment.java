package com.example.peretz.screens.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.peretz.screens.foodmenu.FoodMenuFragment;
import com.example.peretz.R;


public class HomeFragment extends Fragment {

    private Button goToShoppingButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
    }

    private void initViews(View view) {

        goToShoppingButton = view.findViewById(R.id.btn_go_to_shopping);
        goToShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FoodMenuFragment foodMenuFragment = new FoodMenuFragment();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_for_fragments, foodMenuFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}
