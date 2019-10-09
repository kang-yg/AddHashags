package com.AddHashtags.PopularTags;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.AddHashtags.MainSingleton;
import com.example.addhashtags.R;

public class PopularSubject extends Fragment  {
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

        View.OnClickListener myClick = new View.OnClickListener() {
            MainSingleton mainSingleton = MainSingleton.getInstance();
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.popSubject_line01_item01:
                        mainSingleton.mainActivity.indexPopularSubject(0);
                        break;
                    case R.id.popSubject_line01_item02:
                        mainSingleton.mainActivity.indexPopularSubject(1);
                        break;
                    case R.id.popSubject_line02_item01:
                        mainSingleton.mainActivity.indexPopularSubject(2);
                        break;
                    case R.id.popSubject_line02_item02:
                        mainSingleton.mainActivity.indexPopularSubject(3);
                        break;
                    case R.id.popSubject_line03_item01:
                        mainSingleton.mainActivity.indexPopularSubject(4);
                        break;
                    case R.id.popSubject_line03_item02:
                        mainSingleton.mainActivity.indexPopularSubject(5);
                        break;
                }

            }
        };

        button01.setOnClickListener(myClick);
        button02.setOnClickListener(myClick);
        button03.setOnClickListener(myClick);
        button04.setOnClickListener(myClick);
        button05.setOnClickListener(myClick);
        button06.setOnClickListener(myClick);

        return view;
    }
}
