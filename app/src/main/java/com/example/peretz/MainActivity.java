package com.example.peretz;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.peretz.Fragment.FoodMenuFragment;
import com.example.peretz.Fragment.HomeFragment;


public class MainActivity extends FragmentActivity {

    private HomeFragment homeFragment;
    private FoodMenuFragment foodMenuFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        foodMenuFragment = new FoodMenuFragment();
    }
}
