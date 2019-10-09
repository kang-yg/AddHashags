package com.AddHashtags.PopularTags;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.addhashtags.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubjectRecyclerview extends Fragment  {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.subject_recyclerview, null);

        SubjectRecyclerviewSingleton subjectRecyclerviewSingleton = SubjectRecyclerviewSingleton.getInstence();
        subjectRecyclerviewSingleton.subjectRecyclerviewSingletonContext = getContext();
        subjectRecyclerviewSingleton.subjectRecyclerviewLayoutInflater = (LayoutInflater) getActivity().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);

        recyclerView = view.findViewById(R.id.sub_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<SubjectItem> subjectItems = new ArrayList<>();

        Bundle bundle = getArguments();
        int getIndex = bundle.getInt("num");

        switch (getIndex){
            case 0:
                List<String> HOTList = Arrays.asList(getResources().getStringArray(R.array.hot));
                for(int i = 0 ; i < HOTList.size() ; i++){
                    subjectItems.add(new SubjectItem(HOTList.get(i),false));
                }
                break;
            case 1:
                List<String> loveList = Arrays.asList(getResources().getStringArray(R.array.love));
                Collections.sort(loveList);
                Collections.reverse(loveList);
                for(int i = 0 ; i < loveList.size() ; i++){
                    subjectItems.add(new SubjectItem(loveList.get(i),false));
                }
                break;
            case 2:
                List<String> dailyList = Arrays.asList(getResources().getStringArray(R.array.daily));
                Collections.sort(dailyList);
                Collections.reverse(dailyList);
                for(int i = 0 ; i < dailyList.size() ; i++){
                    subjectItems.add(new SubjectItem(dailyList.get(i),false));
                }
                break;
            case 3:
                List<String> photoList = Arrays.asList(getResources().getStringArray(R.array.photo));
                Collections.sort(photoList);
                Collections.reverse(photoList);
                for(int i = 0 ; i < photoList.size() ; i++){
                    subjectItems.add(new SubjectItem(photoList.get(i),false));
                }
                break;
            case 4:
                List<String> foodList = Arrays.asList(getResources().getStringArray(R.array.food));
                Collections.sort(foodList);
                Collections.reverse(foodList);
                for(int i = 0 ; i < foodList.size() ; i++){
                    subjectItems.add(new SubjectItem(foodList.get(i),false));
                }
                break;
            case 5:
                List<String> tripList = Arrays.asList(getResources().getStringArray(R.array.trip));
                Collections.sort(tripList);
                Collections.reverse(tripList);
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
