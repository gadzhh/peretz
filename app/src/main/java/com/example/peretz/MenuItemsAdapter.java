package com.example.peretz;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.MenuItemsHolder> {

    private ArrayList<Menu> items = new ArrayList<>();

    public void setData(ArrayList<Menu> data) {

        items.clear();
        items.addAll(data);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_item, parent, false);
        return new MenuItemsHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemsHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class MenuItemsHolder extends RecyclerView.ViewHolder {

        private int quantity = 0;

        private ImageView imageViewDish;
        private TextView dishName;
        private TextView dishDescription;
        private TextView dishPrice;
        private TextView dishQuantity;
        private Button btnMinus;
        private Button btnPlus;


        public MenuItemsHolder(@NonNull View itemView) {
            super(itemView);

            imageViewDish = itemView.findViewById(R.id.iv_dish);
            dishName = itemView.findViewById(R.id.tv_dish_name);
            dishDescription = itemView.findViewById(R.id.tv_dish_description);
            dishPrice = itemView.findViewById(R.id.tv_dish_price);
            dishQuantity = itemView.findViewById(R.id.tv_dish_quantity);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnPlus = itemView.findViewById(R.id.btn_plus);


            btnMinus.setVisibility(View.INVISIBLE);
            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quantity = quantity - 1;
                    changePrice(quantity);

                    if (quantity == 0) {
                        btnMinus.setVisibility(View.INVISIBLE);
                        dishQuantity.setVisibility(View.INVISIBLE);
                    }

                }
            });


            btnPlus.setVisibility(View.VISIBLE);
            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnMinus.setVisibility(View.VISIBLE);
                    dishQuantity.setVisibility(View.VISIBLE);
                    quantity = quantity + 1;
                    changePrice(quantity);
                }
            });


        }

        private void changePrice(int number) {
            dishQuantity.setText("" + number);
        }

        void bind(Menu item) {

            Picasso
                    .get()
                    .load(item.getImage())
                    .into(imageViewDish);

            dishName.setText(item.getName());
            dishDescription.setText(item.getDescription());
            dishPrice.setText((item.getPrice()) + " ₽");
        }
    }

}
