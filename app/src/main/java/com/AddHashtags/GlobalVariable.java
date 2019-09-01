package com.AddHashtags;

import java.util.ArrayList;

public class GlobalVariable {
    private static GlobalVariable instance;

    private static ArrayList<String> selectedTags = new ArrayList<>();

    public static GlobalVariable getInstance() {
        if (instance == null) {
            instance = new GlobalVariable();
        }

        return instance;
    }

    public ArrayList<String> getSelectedTags(){
        return this.selectedTags;
    }

    public void addSelectedTags(String str) {
        this.selectedTags.add(str);
    }

    public void removeSelectedTags(String str){
        this.selectedTags.remove(str);
    }
}
