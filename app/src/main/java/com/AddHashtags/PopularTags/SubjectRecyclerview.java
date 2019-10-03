package com.AddHashtags.PopularTags;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.addhashtags.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubjectRecyclerview extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.subject_recyclerview, null);

        SubjectRecyclerviewSingleton subjectRecyclerviewSingleton = SubjectRecyclerviewSingleton.getInstence();
        subjectRecyclerviewSingleton.subjectRecyclerviewSingletonContext = getContext();

        recyclerView = view.findViewById(R.id.sub_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<SubjectItem> subjectItems = new ArrayList<>();

        Bundle bundle = getArguments();
        int getIndex = bundle.getInt("num");

        switch (getIndex){
            case 0:
                List<String> TOPList = Arrays.asList(getResources().getStringArray(R.array.TOP100));
                for(int i = 0 ; i < TOPList.size() ; i++){
                    subjectItems.add(new SubjectItem(TOPList.get(i),false));
                }
                break;
            case 1:
                List<String> loveList = Arrays.asList(getResources().getStringArray(R.array.love));
                for(int i = 0 ; i < loveList.size() ; i++){
                    subjectItems.add(new SubjectItem(loveList.get(i),false));
                }
                break;
            case 2:
                List<String> dailyList = Arrays.asList(getResources().getStringArray(R.array.daily));
                for(int i = 0 ; i < dailyList.size() ; i++){
                    subjectItems.add(new SubjectItem(dailyList.get(i),false));
                }
                break;
            case 3:
                List<String> selfieList = Arrays.asList(getResources().getStringArray(R.array.selfie));
                for(int i = 0 ; i < selfieList.size() ; i++){
                    subjectItems.add(new SubjectItem(selfieList.get(i),false));
                }
                break;
            case 4:
                List<String> foodList = Arrays.asList(getResources().getStringArray(R.array.food));
                for(int i = 0 ; i < foodList.size() ; i++){
                    subjectItems.add(new SubjectItem(foodList.get(i),false));
                }
                break;
            case 5:
                List<String> tripList = Arrays.asList(getResources().getStringArray(R.array.trip));
                for(int i = 0 ; i < tripList.size() ; i++){
                    subjectItems.add(new SubjectItem(tripList.get(i),false));
                }
                break;
        }

        SubjectRecyclerviewAdapter subjectRecyclerviewAdapter = new SubjectRecyclerviewAdapter(subjectItems);

        recyclerView.setAdapter(subjectRecyclerviewAdapter);

        return view;
    }
}
