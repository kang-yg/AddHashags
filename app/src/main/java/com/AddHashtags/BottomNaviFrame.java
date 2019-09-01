package com.AddHashtags;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.addhashtags.R;

public class BottomNaviFrame extends Fragment {
    BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_navi, null);

        bottomNavigationView = (BottomNavigationView)view.findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            MainSingleton mainSingleton = MainSingleton.getInstance();
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.popTags:
                        mainSingleton.mainActivity.setFrag(0);
                        break;
                    case R.id.myTags:
                        mainSingleton.mainActivity.setFrag(2);
                        break;
                }
                return false;
            }
        });

        return view;
    }
}
