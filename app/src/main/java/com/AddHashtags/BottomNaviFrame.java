package com.AddHashtags;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.common.R;

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
                        mainSingleton.mainActivity.setFrag(1);
                        break;
                }
                return false;
            }
        });

        return view;
    }
}
