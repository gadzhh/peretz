package com.example.peretz.screens.foodmenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peretz.R;
import com.example.peretz.data.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.MenuItemsHolder> {

    private OnButtonListener onButtonListener;
    private ArrayList<Menu> items = new ArrayList<>();

    public MenuItemsAdapter(OnButtonListener Listener) {
        onButtonListener = Listener;
    }

    public void setData(ArrayList<Menu> data) {

        items.clear();
        items.addAll(data);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_item, parent, false);
        return new MenuItemsHolder(view, onButtonListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemsHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class MenuItemsHolder extends RecyclerView.ViewHolder {

        private OnButtonListener onButtonListener;
        private int quantity = 0;
        private ImageView imageViewDish;
        private TextView dishName, dishDescription, dishPrice, dishQuantity;
        private Button btnMinus, btnPlus;


        public MenuItemsHolder(@NonNull View itemView, OnButtonListener listener) {
            super(itemView);

            onButtonListener = listener;

            imageViewDish = itemView.findViewById(R.id.iv_dish);
            dishName = itemView.findViewById(R.id.tv_dish_name);
            dishDescription = itemView.findViewById(R.id.tv_dish_description);
            dishPrice = itemView.findViewById(R.id.tv_dish_price);
            dishQuantity = itemView.findViewById(R.id.tv_dish_quantity);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnPlus = itemView.findViewById(R.id.btn_plus);
        }

        private void changePrice(int number) {
            dishQuantity.setText(String.valueOf(number));
        }

        void bind(Menu item) {

            Picasso
                    .get()
                    .load(item.getImage())
                    .into(imageViewDish);

            dishName.setText(item.getName());
            dishDescription.setText(item.getDescription());
            dishPrice.setText(itemView.getContext().getString(R.string.price_text, item.getPrice()));

            quantity = item.getQuantity();

            btnMinus.setVisibility(View.INVISIBLE);
            btnMinus.setOnClickListener(view -> {
                quantity = quantity - 1;
                changePrice(quantity);

                if (quantity == 0) {
                    btnMinus.setVisibility(View.INVISIBLE);
                    dishQuantity.setVisibility(View.INVISIBLE);
                }

                onButtonListener.onButtonClick(item.getId(), true);
            });

            btnPlus.setOnClickListener(view -> {
                btnMinus.setVisibility(View.VISIBLE);
                dishQuantity.setVisibility(View.VISIBLE);
                quantity = quantity + 1;
                changePrice(quantity);

                onButtonListener.onButtonClick(item.getId(), false);

            });

            if (item.getQuantity() > 0) {
                btnMinus.setVisibility(View.VISIBLE);
                dishQuantity.setText(String.valueOf(item.getQuantity()));
            } else if (item.getQuantity() == 0) {
                btnMinus.setVisibility(View.INVISIBLE);
                dishQuantity.setVisibility(View.INVISIBLE);
            }
        }
    }
}


