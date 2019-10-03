package com.AddHashtags.MyHashtags;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;

public class MineSingleton {
    private static MineSingleton instance;

    public Context mineContext;
    public FragmentTransaction adpterFragmentTransaction;

    public static MineSingleton getInstance() {
        if (instance == null) {
            instance = new MineSingleton();
        }
        return instance;
    }
}
