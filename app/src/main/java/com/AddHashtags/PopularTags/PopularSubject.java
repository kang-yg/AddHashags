package com.AddHashtags.PopularTags;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.AddHashtags.MainSingleton;
import com.example.addhashtags.R;

public class PopularSubject extends Fragment {
    Button button01, button02, button03, button04, button05, button06;

    public PopularSubject() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.popular_subject, null);

        button01 = view.findViewById(R.id.popSubject_line01_item01);
        button02 = view.findViewById(R.id.popSubject_line01_item02);
        button03 = view.findViewById(R.id.popSubject_line02_item01);
        button04 = view.findViewById(R.id.popSubject_line02_item02);
        button05 = view.findViewById(R.id.popSubject_line03_item01);
        button06 = view.findViewById(R.id.popSubject_line03_item02);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PopularSubject", button01.getText().toString());

                MainSingleton mainSingleton = MainSingleton.getInstance();
                mainSingleton.mainActivity.setFrag(1);
            }
        });

        return view;
    }
}
