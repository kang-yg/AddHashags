package com.AddHashtags;

import android.widget.Button;
import android.widget.LinearLayout;

import com.example.addhashtags.MainActivity;

public class MainSingleton {
    private static MainSingleton instance;

    public MainActivity mainActivity;
    public LinearLayout linearLayout;
    public LinearLayout bigLnearLayout;
    public Button linearLayoutButton;


    public static MainSingleton getInstance() {
        if (instance == null) {
            instance = new MainSingleton();
        }

        return instance;
    }
}
