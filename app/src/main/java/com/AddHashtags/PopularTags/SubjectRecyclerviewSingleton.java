package com.AddHashtags.PopularTags;

import android.content.Context;

public class SubjectRecyclerviewSingleton {
    private static SubjectRecyclerviewSingleton instance;

    public Context subjectRecyclerviewSingletonContext;

    public static SubjectRecyclerviewSingleton getInstence(){
        if (instance == null){
            instance = new SubjectRecyclerviewSingleton();
        }

        return instance;
    }
}
