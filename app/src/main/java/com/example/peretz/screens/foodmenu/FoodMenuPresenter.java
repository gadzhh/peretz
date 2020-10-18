package com.example.peretz.screens.foodmenu;

import com.example.peretz.data.DataManager;
import com.example.peretz.data.Menu;
import com.example.peretz.data.RequestListener;

import java.util.ArrayList;

public class FoodMenuPresenter {

    private DataManager dataManager;
    private FoodMenuView view;

    public FoodMenuPresenter(FoodMenuView view) {
        this.view = view;
        initRequest();
    }

    private void initRequest() {

        dataManager = new DataManager();

        dataManager.getMenuItems(new RequestListener() {
            @Override
            public void onSuccess(ArrayList<Menu> menu) {
                view.hideProgress();
                view.showData(menu);
            }

            @Override
            public void onError(String error) {
                view.hideProgress();
                view.showError(error);
            }
        });
    }
}
