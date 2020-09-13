package com.example.peretz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.MenuItemsHolder> {

    private int numberItems; //кол-во эл-в списка.

    //передаем в конструктор, при создании адаптера.
    public MenuItemsAdapter(int numberOfItems) {   //передаем целое число
        numberItems = numberOfItems;                //назначаем это число
    }

    @NonNull
    @Override
    public MenuItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_item, parent, false);
        return new MenuItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class MenuItemsHolder extends RecyclerView.ViewHolder {


        ImageView imageViewDish;
        TextView dishName;
        TextView dishDescription;
        TextView dishPrice;
        TextView dishQuantity;
        Button btnMinus;
        Button btnPlus;


        public MenuItemsHolder(@NonNull View itemView) {
            super(itemView);

            imageViewDish = itemView.findViewById(R.id.iv_dish);
            dishName = itemView.findViewById(R.id.tv_dish_name);
            dishDescription = itemView.findViewById(R.id.tv_dish_description);
            dishPrice = itemView.findViewById(R.id.tv_dish_price);
            dishQuantity = itemView.findViewById(R.id.tv_dish_quantity);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnPlus = itemView.findViewById(R.id.btn_plus);


        }

    }

}
