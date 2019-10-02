package com.AddHashtags.MyHashtags;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.AddHashtags.GlobalVariable;
import com.AddHashtags.MainSingleton;
import com.example.addhashtags.R;

import java.util.ArrayList;

public class MyTagsRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.mine_item_text);
            this.checkBox = view.findViewById(R.id.mine_item_checkBox);
        }
    }

    private ArrayList<MyTagsItem> myTagsItems;

    MyTagsRecyclerviewAdapter(ArrayList<MyTagsItem> myTagsItems) {
        this.myTagsItems = myTagsItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        final TextView[] temp = new TextView[1];
        final MainSingleton mainSingleton = MainSingleton.getInstance();

        myViewHolder.textView.setText(myTagsItems.get(position).tagName);
        myViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                Log.d("onLongClick", "onLongClick");
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(R.string.deleteTag);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.disagree, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setNegativeButton(R.string.agree, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String databaseName =  "MYTAGS";
                        final String tableName = "MYTAGLIST";
                        DatabaseHelper helper;
                        SQLiteDatabase database;

                        helper = new DatabaseHelper(v.getContext(), databaseName, null,3);
                        database = helper.getWritableDatabase();
                        helper.createTable(database, tableName);

                        helper.deleteData(database,myViewHolder.textView.getText().toString());
                        MineSingleton mineSingleton = MineSingleton.getInstance();
                        FragmentTransaction fragmentTransaction = mineSingleton.adpterFragmentTransaction;
                        fragmentTransaction.detach(mainSingleton.mainActivity.mine).attach(mainSingleton.mainActivity.mine).commit();
                        Toast.makeText(v.getContext(), R.string.deleteSuccess, Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });

        final GlobalVariable globalVariable = GlobalVariable.getInstance();
        myViewHolder.checkBox.setChecked(myTagsItems.get(position).check);
        myViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String temp = "#" + myViewHolder.textView.getText().toString() + " ";
                String str = "";
                if (myViewHolder.checkBox.isChecked() == true) {
                    mainSingleton.bigLnearLayout.setVisibility(View.VISIBLE);
                    globalVariable.addSelectedTags(temp);
                    for(int i = 0 ; i < globalVariable.sizeSelectedTags() ; i++){
                        str += globalVariable.getSelectedTags(i);
                        mainSingleton.textView.setText(str);
                    }
                    for(int i = 0 ; i < globalVariable.sizeSelectedTags() ; i++){
                        Log.d("mainSingleton.textView", globalVariable.getSelectedTags(i));
                    }
                } else if (myViewHolder.checkBox.isChecked() == false) {
                    globalVariable.removeSelectedTags(temp);
                    if(globalVariable.sizeSelectedTags() == 0){
                        mainSingleton.textView.setText(null);
                    }
                    for(int i = 0 ; i < globalVariable.sizeSelectedTags() ; i++){
                        str += globalVariable.getSelectedTags(i);
                        mainSingleton.textView.setText(str);
                    }
                    for(int i = 0 ; i < globalVariable.sizeSelectedTags() ; i++){
                        Log.d("mainSingleton.textView", globalVariable.getSelectedTags(i));
                    }
                }
            }
        });

//        final MainSingleton mainSingleton = MainSingleton.getInstance();
//        myViewHolder.checkBox.setChecked(myTagsItems.get(position).check);
//        myViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                checkAllCheckbox(myViewHolder, myTagsItems);
//                if (myViewHolder.checkBox.isChecked() == true) {
//                    temp[0] = addTextview(mainSingleton.linearLayout, myViewHolder.textView.getText().toString());
//                } else if (myViewHolder.checkBox.isChecked() == false) {
//                    removeTextview(temp[0]);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return myTagsItems.size();
    }

    //체크박스 선택시 레이아웃 GONE → VISIBLE
    //TODO 체크박스 선택시 레이아웃 VISIBLE → GONE
    public void checkAllCheckbox(MyTagsRecyclerviewAdapter.MyViewHolder myViewHolder, ArrayList<MyTagsItem> myTagsItems) {
        Log.d("SubjectRecyclerviewAdapter", "checkAllCheckbox");
        ArrayList<Boolean> booleans = new ArrayList<>();
        MainSingleton mainSingleton = MainSingleton.getInstance();
        for (int i = 0; i < myTagsItems.size(); i++) {
            booleans.add(myViewHolder.checkBox.isChecked());
        }

        if (booleans.contains(true)) {
            Log.d("SubjectRecyclerviewAdapter", "checkAllCheckbox : VISIBLE");
            mainSingleton.bigLnearLayout.setVisibility(View.VISIBLE);
        }
//        else if (!booleans.contains(true)) {
//            Log.d("SubjectRecyclerviewAdapter", "checkAllCheckbox : GONE");
//            subjectRecyclerviewSingleton.linearLayout.setVisibility(View.GONE);
//        }
    }

    //레이아웃에 선택한 태그 추가
    public TextView addTextview(View view, String string) {
        MainSingleton mainSingleton = MainSingleton.getInstance();
        GlobalVariable globalVariable = GlobalVariable.getInstance();

        TextView textView = new TextView(view.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText("#" + string);
        textView.setTextSize(13);
        textView.setPadding(20, 10, 10, 10);
        textView.setTextColor(Color.parseColor("#000000"));

        mainSingleton.linearLayout.addView(textView);

        globalVariable.addSelectedTags("#" + string);

        for (int i = 0; i < globalVariable.getSelectedTags().size(); i++) {
            Log.d("Textview", globalVariable.getSelectedTags().get(i));
        }

        return textView;
    }

    //레이아웃에 선택한 태그 취소
    public void removeTextview(TextView textView) {
        GlobalVariable globalVariable = GlobalVariable.getInstance();

        ((ViewManager) textView.getParent()).removeView(textView);

        globalVariable.removeSelectedTags(textView.getText().toString());

        for (int i = 0; i < globalVariable.getSelectedTags().size(); i++) {
            Log.d("Textview", globalVariable.getSelectedTags().get(i));
        }
    }
}
