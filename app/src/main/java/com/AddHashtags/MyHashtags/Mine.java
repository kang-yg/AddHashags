package com.AddHashtags.MyHashtags;

import android.content.Context;
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
import android.widget.Toast;

import com.AddHashtags.MainSingleton;
import com.example.addhashtags.R;

import java.util.ArrayList;

public class Mine extends Fragment  {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    EditText mineEditText;
    Button mineButton;

    DatabaseHelper helper;
    SQLiteDatabase database;

    final String databaseName = "MYTAGS";
    final String tableName = "MYTAGLIST";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.mine, null);

        getAdapterFragmentTransaction();
        setMineContext(getContext());
        setMineLayoutInflater();

        mineEditText = view.findViewById(R.id.mine_editText);
        mineButton = view.findViewById(R.id.mine_button);

        helper = new DatabaseHelper(getContext(), databaseName, null, 3);
        database = helper.getWritableDatabase();
        helper.createTable(database, tableName);

        ArrayList<String> myTagsName = new ArrayList<>();
        myTagsName = helper.selectData(database, tableName);

        final MainSingleton mainSingleton = MainSingleton.getInstance();
        final ArrayList<String> finalMyTagsName = myTagsName;
        mineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = mineEditText.getText().toString().trim();
                temp = temp.replaceAll(" ", "");

                if (!finalMyTagsName.contains(temp)) {
                    helper.insertData(database, temp);
                    mineEditText.setText("");
                    Toast.makeText(getContext(), getString(R.string.successInsert), Toast.LENGTH_SHORT).show();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.detach(mainSingleton.mainActivity.mine).attach(mainSingleton.mainActivity.mine).commit();
                } else {
                    mineEditText.setText("");
                    Toast.makeText(getContext(), getString(R.string.alreadyExist), Toast.LENGTH_SHORT).show();
                }
            }
        });

        recyclerView = view.findViewById(R.id.mine_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<MyTagsItem> myTagsItems = new ArrayList<>();

        for (int i = 0; i < myTagsName.size(); i++) {
            myTagsItems.add(new MyTagsItem(myTagsName.get(i), false));
        }

        MyTagsRecyclerviewAdapter myTagsRecyclerviewAdapter = new MyTagsRecyclerviewAdapter(myTagsItems);

        recyclerView.setAdapter(myTagsRecyclerviewAdapter);

        return view;
    }

    protected void getAdapterFragmentTransaction() {
        MineSingleton mineSingleton = MineSingleton.getInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        mineSingleton.adpterFragmentTransaction = fragmentTransaction;
    }

    protected void setMineContext(Context mineContext) {
        MineSingleton mineSingleton = MineSingleton.getInstance();
        mineSingleton.mineContext = mineContext;
    }

    protected void setMineLayoutInflater(){
        MineSingleton mineSingleton = MineSingleton.getInstance();
        mineSingleton.mineLayoutInflater = (LayoutInflater) getActivity().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
    }
}
