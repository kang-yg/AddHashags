package com.AddHashtags.MyHashtags;

public class MyTagsItem {
    public String tagName;
    public boolean check;

    public MyTagsItem(String tagName, boolean check) {
        this.tagName = tagName;
        this.check = check;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
