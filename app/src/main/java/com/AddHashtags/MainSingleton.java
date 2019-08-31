package com.AddHashtags;

import com.example.addhashtags.MainActivity;

public class MainSingleton {
    private static MainSingleton instance;

    public MainActivity mainActivity;


    public static MainSingleton getInstance() {
        if (instance == null) {
            instance = new MainSingleton();
        }

        return instance;
    }
}
