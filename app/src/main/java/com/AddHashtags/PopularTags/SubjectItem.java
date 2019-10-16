package com.AddHashtags.PopularTags;

public class SubjectItem {
    public String tagName;
    public boolean check;

    public SubjectItem(String tagName, boolean check) {
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
