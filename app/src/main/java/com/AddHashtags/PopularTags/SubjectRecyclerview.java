package com.AddHashtags.PopularTags;

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
import android.widget.LinearLayout;

import com.example.addhashtags.R;

import java.util.ArrayList;

public class SubjectRecyclerview extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    public SubjectRecyclerview() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SubjectRecyclerview", "SubjectRecyclerview");

        View view = inflater.inflate(R.layout.subject_recyclerview, null);

        recyclerView = view.findViewById(R.id.sub_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<SubjectItem> subjectItems = new ArrayList<>();

        subjectItems.add(new SubjectItem("오늘", false));
        subjectItems.add(new SubjectItem("내일", false));

        SubjectRecyclerviewAdapter subjectRecyclerviewAdapter = new SubjectRecyclerviewAdapter(subjectItems);

        recyclerView.setAdapter(subjectRecyclerviewAdapter);

        return view;
    }
}
