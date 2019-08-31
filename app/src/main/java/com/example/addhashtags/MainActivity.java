package com.example.addhashtags;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.AddHashtags.BottomNaviFrame;
import com.AddHashtags.MainSingleton;
import com.AddHashtags.PopularTags.PopularSubject;
import com.AddHashtags.PopularTags.SubjectRecyclerview;

public class MainActivity extends AppCompatActivity {

    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;

    PopularSubject popularSubject;
    BottomNaviFrame bottomNaviFrame;
    SubjectRecyclerview subjectRecyclerview;

    public static MainSingleton mainSingleton = MainSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mainSingleton.mainActivity = this;
        mainSingleton.bigLnearLayout = findViewById(R.id.big_linearVisibility);
        mainSingleton.linearLayout = findViewById(R.id.linearVisibility);
        mainSingleton.linearLayoutButton = findViewById(R.id.copy_button);
        mainSingleton.linearLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.copyDone), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNaviFrame = new BottomNaviFrame();
        popularSubject = new PopularSubject();
        subjectRecyclerview = new SubjectRecyclerview();

        setBottomNavi();

        setFrag(0);
    }

    //BottomNavi 프래그먼트
    public void setBottomNavi() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.bottomNaviFrame, bottomNaviFrame);
        fragmentTransaction.commit();
    }

    //MainFrame 변경
    public void setFrag(int n) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (n) {
            case 0:
                Log.d("setFrag()", "0");
                fragmentTransaction.replace(R.id.mainFrame, popularSubject);
                fragmentTransaction.commit();
                break;
            case 1:
                Log.d("setFrag()", "1");
                fragmentTransaction.replace(R.id.mainFrame, subjectRecyclerview);
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);
                break;
        }
    }
}