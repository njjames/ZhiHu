package com.nj.zhihu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018-07-05.
 */

public class BeforeStories implements IBaseItem{

    private String date;
    private List<Stories> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }
}

