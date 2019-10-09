package com.AddHashtags;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import java.util.ArrayList;

public class CopyTags {
    private ArrayList<String> strings;

    public CopyTags(ArrayList<String> strings) {
        this.strings = strings;
    }

    public CopyTags(String str){
        String[] strings = str.split(" ");
        this.strings = new ArrayList<>();
        for(int i = 0 ; i < strings.length ; i++){
            this.strings.add(strings[i] + " ");
        }
    }

    public String allTags() {
        String temp ="";
        for (String str : this.strings) {
            temp += str;
        }
        return temp;
    }

    public void copyTagsCilpboard() {
        MainSingleton mainSingleton = MainSingleton.getInstance();
        ClipboardManager clipboardManager = (ClipboardManager) mainSingleton.mainActivity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("copyTagsCilpboard", this.allTags());
        clipboardManager.setPrimaryClip(clipData);
    }
}
