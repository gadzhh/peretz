package com.example.peretz.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceHelper {

    private static final String PREFS_APP = "APP_PREF";
    private static final String PREFS_CART = "PREFS_CART";

    private static SharedPreferences prefs;
    private Context context;


    public SharedPreferenceHelper(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREFS_APP, MODE_PRIVATE);
    }

    public int quantityInBasket(String productId) {

        Gson gson = new Gson();
        HashMap<String, Integer> cart;

        String json = prefs.getString(PREFS_CART, "");
        Type type = new TypeToken<HashMap<String, Integer>>() {
        }.getType();
        cart = gson.fromJson(json, type);

        if (cart != null && !cart.isEmpty()) {
            for (HashMap.Entry<String, Integer> entry : cart.entrySet()) {
                String key = entry.getKey();
                if (key.equals(productId)) {
                    return entry.getValue();
                }
            }
        }
        return 0;
    }

    public void addToCart(String productId) {

        Gson gson = new Gson();
        HashMap<String, Integer> cart;

        String json = prefs.getString(PREFS_CART, "");
        Type type = new TypeToken<HashMap<String, Integer>>() {
        }.getType();
        cart = gson.fromJson(json, type);
        if (cart != null && !cart.isEmpty()) {

            boolean newFood = true;

            for (HashMap.Entry<String, Integer> entry : cart.entrySet()) {
                if (entry.getKey().equals(productId)) {
                    newFood = false;
                    entry.setValue(entry.getValue() + 1);
                }
            }

            if (newFood) {
                cart.put(productId, 1);
            }
        } else {
            cart = new HashMap<>();
            cart.put(productId, 1);
        }

        prefs.edit().putString(PREFS_CART, gson.toJson(cart)).apply();
    }

    public void removeFromCart(String productId) {

        Gson gson = new Gson();
        ConcurrentHashMap<String, Integer> cart;

        String json = prefs.getString(PREFS_CART, "");
        Type type = new TypeToken<ConcurrentHashMap<String, Integer>>() {
        }.getType();
        cart = gson.fromJson(json, type);

        for (ConcurrentHashMap.Entry<String, Integer> entry : cart.entrySet()) {
            if (entry.getKey().equals(productId)) {
                if (entry.getValue() > 1) {
                    entry.setValue(entry.getValue() - 1);
                } else {
                    cart.remove(entry.getKey());
                }
            }
        }

        prefs.edit().putString(PREFS_CART, gson.toJson(cart)).apply();
    }

    public HashMap<String, Integer> getCart() {

        Gson gson = new Gson();
        String json = prefs.getString(PREFS_CART, "");
        Type type = new TypeToken<HashMap<String, Integer>>() {
        }.getType();

        return gson.fromJson(json, type);
    }
}
