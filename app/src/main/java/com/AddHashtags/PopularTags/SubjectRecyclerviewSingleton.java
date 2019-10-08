package com.AddHashtags.PopularTags;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class SubjectRecyclerviewSingleton {
    private static SubjectRecyclerviewSingleton instance;

    public Context subjectRecyclerviewSingletonContext;
    public LayoutInflater subjectRecyclerviewLayoutInflater;

    public static SubjectRecyclerviewSingleton getInstence(){
        if (instance == null){
            instance = new SubjectRecyclerviewSingleton();
        }

        return instance;
    }
}
