package com.AddHashtags.PopularTags;

import android.graphics.Color;
import android.support.annotation.NonNull;
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
import com.AddHashtags.MyHashtags.MineSingleton;
import com.example.addhashtags.R;

import java.util.ArrayList;

public class SubjectRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.sub_recyclerview_item_text);
            this.checkBox = itemView.findViewById(R.id.sub_recyclerview_item_checkBox);
        }
    }

    private ArrayList<SubjectItem> subjectItems;

    SubjectRecyclerviewAdapter(ArrayList<SubjectItem> subjectItems) {
        this.subjectItems = subjectItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_recyclerview_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        final TextView[] temp = new TextView[1];

        myViewHolder.textView.setText(subjectItems.get(position).tagName);
        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flag = false;
            }
        });

        final MainSingleton mainSingleton = MainSingleton.getInstance();
        final GlobalVariable globalVariable = GlobalVariable.getInstance();
        myViewHolder.checkBox.setChecked(subjectItems.get(position).check);
        myViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String temp = "#" + myViewHolder.textView.getText().toString();
                String str = "";
                if (myViewHolder.checkBox.isChecked() == true) {
                    if (!globalVariable.getSelectedTags().contains(temp)) {
                        if(globalVariable.sizeSelectedTags() < 30){
                            mainSingleton.bigLnearLayout.setVisibility(View.VISIBLE);
                            globalVariable.addSelectedTags(temp);
                            for (int i = 0; i < globalVariable.sizeSelectedTags(); i++) {
                                str += globalVariable.getSelectedTags(i);
                                mainSingleton.textView.setText(str);
                            }
                        } else {
                            myViewHolder.checkBox.setChecked(false);
                            SubjectRecyclerviewSingleton subjectRecyclerviewSingleton = SubjectRecyclerviewSingleton.getInstence();
                            Toast.makeText(subjectRecyclerviewSingleton.subjectRecyclerviewSingletonContext, R.string.maxTag, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        myViewHolder.checkBox.setChecked(true);
                        SubjectRecyclerviewSingleton subjectRecyclerviewSingleton = SubjectRecyclerviewSingleton.getInstence();
                        Toast.makeText(subjectRecyclerviewSingleton.subjectRecyclerviewSingletonContext, R.string.alreadySelectde, Toast.LENGTH_SHORT).show();
                    }
                } else if (myViewHolder.checkBox.isChecked() == false) {
                    globalVariable.removeSelectedTags(temp);
                    if (globalVariable.sizeSelectedTags() < 1) {
                        mainSingleton.bigLnearLayout.setVisibility(View.GONE);
                    }
                    if (globalVariable.sizeSelectedTags() == 0) {
                        mainSingleton.textView.setText(null);
                    }
                    for (int i = 0; i < globalVariable.sizeSelectedTags(); i++) {
                        str += globalVariable.getSelectedTags(i);
                        mainSingleton.textView.setText(str);
                    }
                }
            }
        });

//        myViewHolder.checkBox.setChecked(subjectItems.get(position).check);
//        myViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                checkAllCheckbox(myViewHolder, subjectItems);
//                if (myViewHolder.checkBox.isChecked() == true) {
//                    temp[0] = addTextview(mainSingleton.linearLayout, myViewHolder.textView.getText().toString());
//                } else if (myViewHolder.checkBox.isChecked() == false) {
//                    removeTextview(temp[0]);
//                }
//            }
//        });

//        myViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("SubjectRecyclerviewAdapter", "checkBox.onClick : " + myViewHolder.checkBox.isChecked());
//                checkAllCheckbox(myViewHolder, subjectItems);
//                if (myViewHolder.checkBox.isChecked() == true) {
//                    temp[0] = addTextview(v, myViewHolder.textView.getText().toString());
//                } else if (myViewHolder.checkBox.isChecked() == false) {
//                    removeTextview(temp[0]);
//                }
//            }
//        });

        for (int i = 0; i < subjectItems.size(); i++) {
            Log.d("SubjectRecyclerviewAdapter", "onBindViewHolder() : " + subjectItems.get(i).tagName);
            Log.d("SubjectRecyclerviewAdapter", "onBindViewHolder() : " + subjectItems.get(i).check);
        }
    }

    @Override
    public int getItemCount() {
        Log.d("SubjectRecyclerviewAdapter", "getItemCount()");
        Log.d("SubjectRecyclerviewAdapter", "getItemCount() : " + subjectItems.size());
        return subjectItems.size();
    }

    //체크박스 선택시 레이아웃 GONE → VISIBLE
    //TODO 체크박스 선택시 레이아웃 VISIBLE → GONE
    public void checkAllCheckbox(MyViewHolder myViewHolder, ArrayList<SubjectItem> subjectItems) {
        Log.d("SubjectRecyclerviewAdapter", "checkAllCheckbox");
        ArrayList<Boolean> booleans = new ArrayList<>();
        MainSingleton mainSingleton = MainSingleton.getInstance();
        for (int i = 0; i < subjectItems.size(); i++) {
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
