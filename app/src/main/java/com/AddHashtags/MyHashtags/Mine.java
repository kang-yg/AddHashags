package com.AddHashtags.MyHashtags;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.AddHashtags.MainSingleton;
import com.example.addhashtags.R;

import java.util.ArrayList;

public class Mine extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    EditText mineEditText;
    Button mineButton;

    DatabaseHelper helper;
    SQLiteDatabase database;

    final String databaseName =  "MYTAGS";
    final String tableName = "MYTAGLIST";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.mine, null);

        mineEditText = view.findViewById(R.id.mine_editText);
        mineButton = view.findViewById(R.id.mine_button);

        helper = new DatabaseHelper(getContext(), databaseName, null,3);
        database = helper.getWritableDatabase();
        helper.createTable(database, tableName);

        final MainSingleton mainSingleton = MainSingleton.getInstance();
        mineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String temp = mineEditText.getText().toString().trim();

                helper.insertData(database, temp);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.detach(mainSingleton.mainActivity.mine).attach(mainSingleton.mainActivity.mine).commit();
            }
        });

        recyclerView = view.findViewById(R.id.mine_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<MyTagsItem> myTagsItems = new ArrayList<>();
        ArrayList<String> myTagsName = new ArrayList<>();

        myTagsName = helper.selectData(database, tableName);

        for(int i = 0 ; i<myTagsName.size() ; i++){
            myTagsItems.add(new MyTagsItem(myTagsName.get(i), false));
        }

        MyTagsRecyclerviewAdapter myTagsRecyclerviewAdapter = new MyTagsRecyclerviewAdapter(myTagsItems);

        recyclerView.setAdapter(myTagsRecyclerviewAdapter);

        return view;
    }
}
