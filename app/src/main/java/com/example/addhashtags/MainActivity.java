package com.example.addhashtags;

import android.content.DialogInterface;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.AddHashtags.BottomNaviFrame;
import com.AddHashtags.CopyTags;
import com.AddHashtags.GlobalVariable;
import com.AddHashtags.MainSingleton;
import com.AddHashtags.MyHashtags.Mine;
import com.AddHashtags.PopularTags.PopularSubject;
import com.AddHashtags.PopularTags.SubjectRecyclerview;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;

    //프래그먼트 뷰
    PopularSubject popularSubject;
    BottomNaviFrame bottomNaviFrame;
    SubjectRecyclerview subjectRecyclerview;
    public Mine mine;

    //adMob
    private AdView mAdView;

    public static MainSingleton mainSingleton = MainSingleton.getInstance();

    long pressBack = 0;

    @Override
    public void onBackPressed() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (System.currentTimeMillis() > pressBack + 1000) {
            pressBack = System.currentTimeMillis();

            fragmentTransaction.replace(R.id.mainFrame, popularSubject);
            fragmentManager.popBackStack();
            fragmentTransaction.commit();
        } else if (System.currentTimeMillis() <= pressBack + 1000) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.finish_title).setMessage(R.string.finish_content);
            builder.setPositiveButton(R.string.finish_disagree, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton(R.string.finish_agree, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        MobileAds.initialize(this, getString(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        GlobalVariable globalVariable = GlobalVariable.getInstance();
        globalVariable.clearSelectedTags();

        mainSingleton.mainActivity = this;
        mainSingleton.bigLnearLayout = findViewById(R.id.big_linearVisibility);
        mainSingleton.textView = findViewById(R.id.linearVisibility_text);
//        mainSingleton.linearLayout = findViewById(R.id.linearVisibility);
        mainSingleton.linearLayoutButton = findViewById(R.id.copy_button);
        mainSingleton.linearLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable globalVariable = GlobalVariable.getInstance();
                CopyTags copyTags = new CopyTags(globalVariable.getSelectedTags());
                copyTags.copyTagsCilpboard();
                Toast.makeText(getApplicationContext(), R.string.copyDone, Toast.LENGTH_SHORT).show();
            }
        });

        bottomNaviFrame = new BottomNaviFrame();
        popularSubject = new PopularSubject();
        subjectRecyclerview = new SubjectRecyclerview();
        mine = new Mine();

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
                //PopularSubject
                Log.d("setFrag()", "0");
                fragmentTransaction.replace(R.id.mainFrame, popularSubject);
                fragmentManager.popBackStack();
//                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 1:
                //Mine
                Log.d("setFrag()", "1");
                fragmentTransaction.replace(R.id.mainFrame, mine);
                fragmentManager.popBackStack();
//                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }

    public void indexPopularSubject(int n) {
        Bundle bundle = new Bundle();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        bundle.putInt("num", n);
        subjectRecyclerview.setArguments(bundle);
        fragmentTransaction.replace(R.id.mainFrame, subjectRecyclerview);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}