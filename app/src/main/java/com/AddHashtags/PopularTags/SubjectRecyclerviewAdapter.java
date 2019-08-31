package com.AddHashtags.PopularTags;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

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

        myViewHolder.textView.setText(subjectItems.get(position).tagName);

        myViewHolder.checkBox.setChecked(subjectItems.get(position).check);
        myViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewHolder.checkBox.isChecked() == true) {
                    Log.d("SubjectRecyclerviewAdapter", "checkBox.setOnClickListener : " + myViewHolder.textView.getText());
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
}
