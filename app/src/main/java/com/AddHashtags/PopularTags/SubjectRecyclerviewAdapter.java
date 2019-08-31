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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.AddHashtags.MainSingleton;
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
        Log.d("SubjectRecyclerviewAdapter", "onCreateViewHolder()");

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_recyclerview_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("SubjectRecyclerviewAdapter", "onBindViewHolder()");

        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        final TextView[] temp = new TextView[1];

        myViewHolder.textView.setText(subjectItems.get(position).tagName);
        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flag = false;
            }
        });

        myViewHolder.checkBox.setChecked(subjectItems.get(position).check);
        myViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SubjectRecyclerviewAdapter", "checkBox.onClick : " + myViewHolder.checkBox.isChecked());
                checkAllCheckbox(myViewHolder, subjectItems);
                if (myViewHolder.checkBox.isChecked() == true) {
                    temp[0] = addTextview(v, myViewHolder.textView.getText().toString());
                } else if (myViewHolder.checkBox.isChecked() == false) {
                    removeTextview(temp[0]);
                }
            }
        });

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

    public void checkAllCheckbox(MyViewHolder myViewHolder, ArrayList<SubjectItem> subjectItems) {
        Log.d("SubjectRecyclerviewAdapter", "checkAllCheckbox");
        ArrayList<Boolean> booleans = new ArrayList<>();
        MainSingleton mainSingleton = MainSingleton.getInstance();
        for (int i = 0; i < subjectItems.size(); i++) {
            booleans.add(myViewHolder.checkBox.isChecked());
        }

        if (booleans.contains(true)) {
            Log.d("SubjectRecyclerviewAdapter", "checkAllCheckbox : VISIBLE");
            mainSingleton.linearLayout.setVisibility(View.VISIBLE);
        }
//        else if (!booleans.contains(true)) {
//            Log.d("SubjectRecyclerviewAdapter", "checkAllCheckbox : GONE");
//            subjectRecyclerviewSingleton.linearLayout.setVisibility(View.GONE);
//        }
    }

    public TextView addTextview(View view, String string) {
        MainSingleton mainSingleton = MainSingleton.getInstance();
        TextView textView = new TextView(view.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText("#" + string);
        textView.setTextSize(13);
        textView.setPadding(20, 10, 10, 10);
        textView.setTextColor(Color.parseColor("#000000"));
        mainSingleton.linearLayout.addView(textView);

        return textView;
    }

    public void removeTextview(TextView textView) {
        ((ViewManager)textView.getParent()).removeView(textView);
    }
}
