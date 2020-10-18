package com.example.peretz.screens.foodmenu;

import com.example.peretz.data.Menu;

import java.util.ArrayList;


interface FoodMenuView {

    void showProgress();

    void hideProgress();

    void showError(String error);

    void showData(ArrayList<Menu> menu);
}
