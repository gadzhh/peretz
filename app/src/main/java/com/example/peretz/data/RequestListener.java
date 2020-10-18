package com.example.peretz.data;

import java.util.ArrayList;

public interface RequestListener {

    void onSuccess(ArrayList<Menu> menu);

    void onError(String error);
}